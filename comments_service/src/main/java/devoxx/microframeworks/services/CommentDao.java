package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class CommentDao {

    private static Logger LOG = LoggerFactory.getLogger(CommentDao.class);

    private static Map<String, List<Comment>> allComments;
    private static CommentDao instance = new CommentDao();

    public static CommentDao getInstance() {
        return instance;
    }

    static {
        try (Reader reader = new InputStreamReader(CommentDao.class.getResourceAsStream("/comments.json"))) {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();

            allComments = GSON.fromJson(reader, new TypeToken<Map<String, List<Comment>>>() {
            }.getType());

            LOG.info("Parse wines comments database : {} wines comments read.", allComments.keySet().size());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private CommentDao() {
    }

    public List<Comment> findAll(String wid) {
        List<Comment> result = allComments.get(wid);
        if (result == null) {
            throw new NoSuchElementException("Wine not found");
        } else {
            return result;
        }
    }

    public Comment findById(String wid, String id) {
        List<Comment> commentsByWid = allComments.get(wid);

        if (commentsByWid == null) {
            throw new NoSuchElementException("Wine not found");
        } else {

            Comment found = commentsByWid.stream().filter(c -> c.getId().equals(id)).findFirst().get();
            if (found == null) {
                throw new NoSuchElementException("Comment not found");
            }
            return found;
        }
    }

    public Comment create(String wid, Comment comment) {
        comment.setId(UUID.randomUUID().toString());

        List<Comment> commentsByWid = allComments.get(wid);
        if (commentsByWid == null) {
            throw new NoSuchElementException("Wine not found");
        }
        commentsByWid.add(comment);
        return comment;
    }
}
