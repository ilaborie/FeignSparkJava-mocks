package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class CommentRoute {

    private static Logger LOG = LoggerFactory.getLogger(CommentRoute.class);
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private CommentDao dao = CommentDao.getInstance();

    public Object handleFindAll(Request request, Response response) {
        String wid = request.params(":wid");
        LOG.info("handleFindAll(" + wid + ")");
        return GSON.toJson(dao.findAll(wid));
    }

    public Object handleFindById(Request request, Response response) {
        String wid = request.params(":wid");
        String id = request.params(":id");
        LOG.info("handleFindById(" + wid + "," + id + ")");
        return GSON.toJson(dao.findById(wid, id));
    }

    public Object handleCreate(Request request, Response response) {
        String wid = request.params(":wid");
        Comment comment = parseComment(request.bodyAsBytes());
        LOG.info("handleCreate(" + wid + ") with body=" + GSON.toJson(comment));
        String created = GSON.toJson(dao.create(wid, comment));
        response.status(201); // 201 Created
        return created;
    }

    private Comment parseComment(byte[] bytes) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes); Reader reader = new InputStreamReader(input)) {
            return GSON.fromJson(reader, Comment.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Not a comment");
        }
    }
}
