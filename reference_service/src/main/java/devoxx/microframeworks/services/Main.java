package devoxx.microframeworks.services;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        externalStaticFileLocation("reference_service/src/main/public");
        port(8090);

        ReferenceRoute referenceRoute = new ReferenceRoute();
        get("/api/wines", referenceRoute::handleSearch);
        get("/api/wines/:wid", referenceRoute::handleFindById);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
    }
}
