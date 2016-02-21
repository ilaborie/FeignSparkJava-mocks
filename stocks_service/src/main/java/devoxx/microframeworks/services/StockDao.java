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
import java.util.Map;
import java.util.NoSuchElementException;

public class StockDao {

    private static Logger LOG = LoggerFactory.getLogger(StockDao.class);

    private static StockDao instance = new StockDao();
    private static Map<String, Integer> allStocks;

    public static StockDao getInstance() {
        return instance;
    }

    static {
        try {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = new BufferedReader(new FileReader("stocks_service/src/main/resources/stocks.json"));
            allStocks = GSON.fromJson(reader, new TypeToken<Map<String, Integer>>() {
            }.getType());

            LOG.info("Parse wines stocks database : " + allStocks.keySet().size() + " wines stocks read.");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private StockDao() {
    }

    public Map<String, Integer> findAll() {
        return allStocks;
    }

    public Integer findById(String wid) {
        Integer qty = allStocks.get(wid);
        if (qty == null) {
            throw new NoSuchElementException("Wine not found");
        } else {
            return qty;
        }
    }

    public void order(String wid, String qty) {
        Integer stock = allStocks.get(wid);
        if (stock == null) {
            throw new NoSuchElementException("Wine not found");
        } else {
            int q = Integer.parseInt(qty);
            if (q < 0) {
                throw new IllegalArgumentException("Invalid quantity");
            }
            if (stock < q) {
                throw new IllegalArgumentException("Not enough stock");
            }
            allStocks.put(wid, stock - q);
        }
    }
}
