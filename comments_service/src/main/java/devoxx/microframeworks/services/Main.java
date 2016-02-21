package devoxx.microframeworks.services;

import java.util.NoSuchElementException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(8091);

        CommentRoute commentRoute = new CommentRoute();
        get("/api/wines/:wid/comments", commentRoute::handleFindAll);
        get("/api/wines/:wid/comments/:id", commentRoute::handleFindById);
        post("/api/wines/:wid/comments", commentRoute::handleCreate);

        ErrorRoute errorRoute = new ErrorRoute();
        exception(NoSuchElementException.class, errorRoute::handleNotFound);
        exception(IllegalArgumentException.class, errorRoute::handleBadRequest);
    }
}
