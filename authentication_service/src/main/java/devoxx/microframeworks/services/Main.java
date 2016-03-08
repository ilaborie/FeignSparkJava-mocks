package devoxx.microframeworks.services;

import spark.Request;
import spark.Response;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(8093);

        AuthenticationRoute authenticationRoute = new AuthenticationRoute();
        get("/api/user/:token", authenticationRoute::handleGetUser);
        post("/api/auth/login", authenticationRoute::handleAuthenticate);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
        exception(SecurityException.class, errorRoute::handleForbidden);

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
