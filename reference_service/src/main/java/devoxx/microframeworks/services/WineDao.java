package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

// TODO use a real DB (Mongo / ElasticSearch)
// FIXME Args: Singleton => Enum since Java 5
public class WineDao {
    private static Logger LOG = LoggerFactory.getLogger(WineDao.class);

    private static WineDao instance = new WineDao();

    private static List<Wine> allWines; // FIXME Why, the map should be enough
    private static Map<String, Wine> allWinesById = new HashMap<>();

    private static ArrayList<Field> attributes = new ArrayList<>(Arrays.asList(Wine.class.getDeclaredFields())); // FIXME declare as List<Field>

    static {
        // FIXME Java 7 Try with Resources
        try {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            // FIXME WineDao.class.getResourceAsStream("/wines.json")
            Reader reader = new BufferedReader(new FileReader("reference_service/src/main/resources/wines.json"));
            allWines = GSON.fromJson(reader, new TypeToken<List<Wine>>() {
            }.getType());

            // Build index by Id
            // FIXME (forEach: evil, for loops: Evil) use a Collectors groupBy
            allWines.forEach(wine -> allWinesById.put(wine.getId(), wine));

            LOG.info("Parse wines database : " + allWines.size() + " wines read."); // FIXME LOG.info("Parse wines database :{} wines read.", allWines.size());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e); // FIXME Should stop the server ...
        }
    }

    /**
     * Private constructor
     */
    private WineDao() {
        // FIXME (see 'Effective Java') throw new AssertionError("private constructor");
    }

    /**
     * @return Return singleton instance
     */
    public static WineDao getInstance() {
        return instance;
    }

    /**
     * @return Return all the wines
     */
    public List<Wine> findAll() {
        return allWines;
    }

    /**
     * @param id wine identifier
     * @return Return the wine with matching id or throw a NoSuchElementException exception should the id not match any wine.
     */
    public Wine findById(String id) {
        Wine result = allWinesById.get(id); // TODO, use Optional.orElseThrow
        if (result == null) {
            throw new NoSuchElementException("Wine not found");
        } else {
            return result;
        }
    }

    /**
     * Find all matching wines for a given criteria.
     * Important note : only one word can be provided here... no criteria expression.
     *
     * @param q criteria
     * @return List of all matching wines for a given criteria.
     */
    public List<Wine> findByCriteria(String q) { // FIXME I prefer a Set
        return allWines.stream().filter(wine -> wineMatch(wine, q)).collect(Collectors.toList());
    }

    /**
     * Try to find if a wine match a criteria by searching in any of its fields (case insensitive).
     *
     * @param wine wine to match
     * @param q    criteria
     * @return true if at least one field contains the q and false otherwise
     */
    private boolean wineMatch(Wine wine, String q) { // TODO I would have preferred a Wine#match(q)
        for (Field att : attributes) { // FIXME Evil for use Stream#anyMatch
            try {
                String value = (String) att.get(wine); // TODO I prefer String.class.cast(att.get(wine))
                if (value != null) { // FIXME Ugly, Can we use a RegExp ?
                    value = value.toLowerCase();
                    if (value.contains(q.toLowerCase())) {
                        return true;
                    }
                }
            } catch (IllegalAccessException e) {
                LOG.warn(e.getMessage(), e); // TODO Should Not Happen => throw new AssertError
            }
        }
        return false;
    }
}
