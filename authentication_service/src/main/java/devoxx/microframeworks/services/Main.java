package devoxx.microframeworks.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getPort());

        AuthenticationRoute authenticationRoute = new AuthenticationRoute();
        get("/api/user/:token", authenticationRoute::handleGetUser);
        post("/api/auth/login", authenticationRoute::handleAuthenticate);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
        exception(SecurityException.class, errorRoute::handleForbidden);

        // CORS
        options("/*", (request, response) -> "");
        after((request, response) -> {
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
            response.header("Access-Control-Allow-Credentials", "true");
        });
    }

    private static int getPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        return Optional.ofNullable(processBuilder.environment().get("PORT"))
                .map(Integer::parseInt)
                .orElse(8093);
    }

}
