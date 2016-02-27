package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public enum StockDao {
    INSTANCE;

    private static Logger LOG = LoggerFactory.getLogger(StockDao.class);

    private static Map<String, Stock> allStocks;

    static {
        try (Reader reader = new InputStreamReader(StockDao.class.getResourceAsStream("/stocks.json"))) {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();

            allStocks = GSON.fromJson(reader, new TypeToken<Map<String, Stock>>() {
            }.getType());

            LOG.info("Parse wines stocks database :{} wines stocks read.", allStocks.keySet().size());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    public Map<String, Stock> findAll() {
        return allStocks;
    }

    public Stock findById(String wid) {
        return Optional.ofNullable(allStocks.get(wid))
                .orElseThrow(() -> new NoSuchElementException("Wine not found : " + wid));
    }

    public synchronized void order(String wid, String qty) {
        Stock stock = findById(wid);
        int q = Integer.parseInt(qty);
        if (q < 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }
        if (stock.getStock() < q) {
            throw new IllegalArgumentException("Not enough stock");
        }
        stock.setStock(stock.getStock() - q);
    }
}
