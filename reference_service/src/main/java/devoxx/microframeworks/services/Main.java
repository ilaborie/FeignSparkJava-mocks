package devoxx.microframeworks.services;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        // FIXME in 201x we can use a logger with highlighting (logback in runtime) !
        // TODO herokuify (see https://sparktutorials.github.io/2015/08/24/spark-heroku.html)

        // Will serve all static file are under "/public" in classpath if the route isn't consumed by others routes.
        // When using Maven, the "/public" folder is assumed to be in "/main/resources"
        staticFileLocation("/public");
        port(8090);

        ReferenceRoute referenceRoute = new ReferenceRoute();
        get("/api/wines", referenceRoute::handleSearch);
        get("/api/wines/:wid", referenceRoute::handleFindById);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
    }
}
