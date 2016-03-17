package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public enum CommentDao {
    INSTANCE;

    private static Logger LOG = LoggerFactory.getLogger(CommentDao.class);

    private static Map<String, List<Comment>> allComments;

    static {
        InputStream stream = CommentDao.class.getResourceAsStream("/comments.json");
        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            Gson GSON = new GsonBuilder().setPrettyPrinting().create();

            allComments = GSON.fromJson(reader, new TypeToken<Map<String, List<Comment>>>() {
            }.getType());

            LOG.info("Parse wines comments database : {} wines comments read.", allComments.keySet().size());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<Comment> findAll(String wid) {
        return Optional.ofNullable(allComments.get(wid))
                .orElseThrow(() -> new NoSuchElementException("Wine not found :" + wid));
    }

    public Comment findById(String wid, String id) {
        List<Comment> commentsByWid = findAll(wid);
        return Optional.ofNullable(commentsByWid.stream().filter(c -> c.getId().equals(id)).findFirst().get())
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));
    }

    public Comment create(String wid, Comment comment) {
        comment.setId(UUID.randomUUID().toString());
        List<Comment> commentsByWid = findAll(wid);
        commentsByWid.add(comment);
        return comment;
    }
}
