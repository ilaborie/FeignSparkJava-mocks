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


public class WineDao {

    private static Logger LOG = LoggerFactory.getLogger(WineDao.class);

    private static WineDao instance = new WineDao();

    private static List<Wine> allWines;
    private static Map<String, Wine> allWinesById = new HashMap<>();

    private static ArrayList<Field> attributes = new ArrayList<>(Arrays.asList(Wine.class.getDeclaredFields()));

    static {
        try {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = new BufferedReader(new FileReader("reference_service/src/main/resources/wines.json"));
            allWines = GSON.fromJson(reader, new TypeToken<List<Wine>>() {
            }.getType());

            // Build index by Id
            allWines.forEach(wine -> allWinesById.put(wine.getId(), wine));

            LOG.info("Parse wines database : " + allWines.size() + " wines read.");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Private constructor
     */
    private WineDao() {
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
        Wine result = allWinesById.get(id);
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
    public List<Wine> findByCriteria(String q) {
        return allWines.stream().filter(wine -> wineMatch(wine, q)).collect(Collectors.toList());
    }

    /**
     * Try to find if a wine match a criteria by searching in any of its fields (case insensitive).
     *
     * @param wine wine to match
     * @param q    criteria
     * @return true if at least one field contains the q and false otherwise
     */
    private boolean wineMatch(Wine wine, String q) {
        for (Field att : attributes) {
            try {
                String value = (String) att.get(wine);
                if (value != null) {
                    value = value.toLowerCase();
                    if (value.contains(q.toLowerCase())) {
                        return true;
                    }
                }
            } catch (IllegalAccessException e) {
                LOG.warn(e.getMessage(), e);
            }
        }
        return false;
    }
}
