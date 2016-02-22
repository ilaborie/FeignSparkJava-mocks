package devoxx.microframeworks.services;

import spark.Request;
import spark.Response;

public class ErrorRoute {

    public void handleNotFound(Exception exception, Request request, Response response) {
        prepareResponse(response, 404, "Not Found", exception); // FIXME use HttpServletResponse.SC_NOT_FOUND

    }

    public void handleBadRequest(Exception exception, Request request, Response response) {
        prepareResponse(response, 400, "Bad Request", exception); // FIXME use HttpServletResponse.SC_BAD_REQUEST
    }

    private void prepareResponse(Response response, int statusCode, String defaultMsg, Exception exception) {
        String msg = exception.getMessage();
        msg = (msg == null || msg.isEmpty()) ? defaultMsg : msg;

        response.status(statusCode);
        response.body(msg);
        response.type("plain/text");
    }
}
