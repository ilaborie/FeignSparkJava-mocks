package devoxx.microframeworks.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getPort());

        CommentRoute commentRoute = new CommentRoute();
        get("/api/wines/:wid/comments", commentRoute::handleFindAll);
        get("/api/wines/:wid/comments/:id", commentRoute::handleFindById);
        post("/api/wines/:wid/comments", commentRoute::handleCreate);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
    }

    private static int getPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        return Optional.ofNullable(processBuilder.environment().get("PORT"))
                .map(Integer::parseInt)
                .orElse(8091);
    }
}
