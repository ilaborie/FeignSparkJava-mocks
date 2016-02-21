package devoxx.microframeworks.services;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(8092);

        StockRoute stockRoute = new StockRoute();
        get("/api/wines/qty", stockRoute::handleFindAll);
        get("/api/wines/:wid/qty", stockRoute::handleFindById);
        post("/api/wines/:wid/order", stockRoute::handleOrder);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
        exception(NumberFormatException.class, errorRoute::handleBadRequest);
    }
}
