package devoxx.microframeworks.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class ReferenceRoute {

    private WineDao dao = WineDao.getInstance();
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Logger LOG = LoggerFactory.getLogger(ReferenceRoute.class);

    public Object handleFindById(Request request, Response response) {
        String id = request.params(":wid");
        LOG.info("handleFindById({})", id);
        return GSON.toJson(dao.findById(id));
    }

    public Object handleSearch(Request request, Response response) {
        String q = request.queryParams("q");
        if (q != null) {
            LOG.info("findByCriteria({})", q);
            return GSON.toJson(dao.findByCriteria(q));
        } else {
            LOG.info("findAll()");
            return GSON.toJson(dao.findAll());
        }
    }
}
