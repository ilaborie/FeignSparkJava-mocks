package devoxx.microframeworks.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getPort());

        StockRoute stockRoute = new StockRoute();
        get("/api/wines/qty", stockRoute::handleFindAll);
        get("/api/wines/:wid/qty", stockRoute::handleFindById);
        post("/api/wines/:wid/order", stockRoute::handleOrder);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
        exception(NumberFormatException.class, errorRoute::handleBadRequest);
    }

    private static int getPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        return Optional.ofNullable(processBuilder.environment().get("PORT"))
                .map(Integer::parseInt)
                .orElse(8092);
    }
}
