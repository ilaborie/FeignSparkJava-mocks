package devoxx.microframeworks.services;

import spark.Request;
import spark.Response;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class ErrorRoute {

    public void handleNotFound(Exception exception, Request request, Response response) {
        prepareResponse(response, SC_NOT_FOUND, "Not Found", exception);
    }

    public void handleBadRequest(Exception exception, Request request, Response response) {
        prepareResponse(response, SC_BAD_REQUEST, "Bad Request", exception);
    }

    private void prepareResponse(Response response, int statusCode, String defaultMsg, Exception exception) {
        String msg = exception.getMessage();
        msg = (msg == null || msg.isEmpty()) ? defaultMsg : msg;

        response.status(statusCode);
        response.body(msg);
        response.type("plain/text");
    }
}
