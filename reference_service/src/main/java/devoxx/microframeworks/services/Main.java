package devoxx.microframeworks.services;

import spark.Request;
import spark.Response;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
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

        // CORS
        options("/*", (request, response) -> "");
        after(Main::cors);
    }

    private static void cors(Request request, Response response) {
        response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        response.header("Access-Control-Allow-Credentials", "true");
    }
}
