package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum WineDao {
    INSTANCE;

    private static Logger LOG = LoggerFactory.getLogger(WineDao.class);

    private static Map<String, Wine> allWinesById = new HashMap<>();

    static {
        try (Reader reader = new InputStreamReader(WineDao.class.getResourceAsStream("/wines.json"))) {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();

            List<Wine> allWines = GSON.fromJson(reader, new TypeToken<List<Wine>>() {
            }.getType());

            // Build index by Id
            allWines.forEach(wine -> allWinesById.put(wine.getId(), wine));

            LOG.info("Parse wines database :{} wines read.", allWinesById.values().size());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    /**
     * @return Return all the wines
     */
    public List<Wine> findAll() {
        return new ArrayList<>(allWinesById.values());
    }

    /**
     * @param id wine identifier
     * @return Return the wine with matching id or throw a NoSuchElementException exception should the id not match any wine.
     */
    public Wine findById(String id) {
        return Optional.ofNullable(allWinesById.get(id))
                .orElseThrow(() -> new NoSuchElementException("Wine not found: " + id));
    }

    /**
     * Find all matching wines for a given criteria.
     * Important note : only one word can be provided here... no criteria expression.
     *
     * @param q criteria
     * @return List of all matching wines for a given criteria.
     */
    public List<Wine> findByCriteria(String q) {
        return allWinesById.values().stream().filter(wine -> wineMatch(wine, q)).collect(Collectors.toList());
    }

    /**
     * Try to find if a wine match a criteria by searching in any of its fields (case insensitive).
     *
     * @param wine wine to match
     * @param q    criteria
     * @return true if at least one field contains the q and false otherwise
     */
    private boolean wineMatch(Wine wine, String q) {

        Predicate<String> matcher = str -> str != null && str.toLowerCase().contains(q.toLowerCase());

        return Arrays.stream(Wine.class.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .map(field -> safeGet(field, wine))
                .anyMatch(matcher);
    }

    /**
     * Safe method in order to be able to use it in lambda
     *
     * @param field field used to retrieve the value from the instance
     * @param wine wine instance
     * @return String value of the attribute
     */
    private String safeGet(final Field field, Wine wine) {
        try {
            return (String) field.get(wine);
        } catch (IllegalAccessException e) {
            // Should never happen...
            throw new RuntimeException();
        }
    }
}
