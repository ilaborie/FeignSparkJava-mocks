package devoxx.microframeworks.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class StockRoute {

    private static Logger LOG = LoggerFactory.getLogger(StockRoute.class);
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private StockDao dao = StockDao.INSTANCE;

    public Object handleFindAll(Request request, Response response) {
        LOG.info("handleFindAll()");
        return GSON.toJson(dao.findAll());
    }

    public Object handleFindById(Request request, Response response) {
        String wid = request.params(":wid");
        LOG.info("handleFindById({})", wid);
        return GSON.toJson(dao.findById(wid));
    }

    public Object handleOrder(Request request, Response response) {
        String wid = request.params(":wid");
        String qty = request.queryParams("qty");
        LOG.info("handleOrder({},{})", wid, qty);
        dao.order(wid, qty);
        return GSON.toJson("Order accepted");
    }
}
