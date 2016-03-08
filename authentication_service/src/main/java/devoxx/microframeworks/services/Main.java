package devoxx.microframeworks.services;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(8093);

        AuthenticationRoute authenticationRoute = new AuthenticationRoute();
        get("/api/user/:token", authenticationRoute::handleGetUser);
        post("/api/authenticate", authenticationRoute::handleAuthenticate);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
        exception(SecurityException.class, errorRoute::handleForbidden);
    }
}
