package is.ru.honn.ruber.feeds;

import is.ru.honn.ruber.domain.Trip;
import is.ruframework.http.SimpleHttpRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by arnif on 9/29/14.
 */
public class JsonReader extends AbstractFeedReader {

    /**
     * use this for logging stuff
     */
    Logger log = Logger.getLogger(this.getClass().getName());

    public JsonReader() { }

    public void setFeedHandler(FeedHandler handler)
    {
        this.handler = handler;
    }

    /**
     * Read content from the source
     * @param source source of the content (url)
     * @throws FeedException if any errors come up
     */
    public void read(String source) throws FeedException {

        if (handler == null) {
            throw new FeedException("Handler unspecified");
        }

        URL feedUrl = null;

        try {
            feedUrl = new URL(source);

        } catch (MalformedURLException murlex) {
            throw new FeedException ("URL is not correct", murlex);
        }

        //Create the parser to parse the json
        JSONParser parser = new JSONParser();

        try {
            String s = SimpleHttpRequest.sendGetRequest(String.valueOf(feedUrl));

            ArrayList<Trip> trips = new ArrayList<Trip>();
            JSONObject json = (JSONObject) parser.parse(s);
            JSONArray historyArr = (JSONArray) json.get("history");

            String uuid = "";

            for (int i = 0; i < historyArr.size(); i++) {
                JSONObject jsonProduct = (JSONObject) historyArr.get(i);
                Trip trip = new Trip();

                long startTime = (Long) jsonProduct.get("start_time");
                trip.setStart_time((int) startTime);
                long requestTime = (Long) jsonProduct.get("request_time");
                trip.setRequest_time((int) requestTime);
                double distance = (Double) jsonProduct.get("distance");
                trip.setDistance((float) distance);
                trip.setProduct_id((String) jsonProduct.get("product_id"));
                long endTime = (Long) jsonProduct.get("end_time");
                trip.setEnd_time((int) endTime);
                uuid = (String) jsonProduct.get("uuid");
                trip.setUuid(uuid);
                trip.setTripStatus(Trip.TripStatus.completed.toString());

                trips.add(trip);
            }

            //handle the content we got from the source
            handler.processContent(uuid, trips);
        } catch (Exception e) {
            String tmp = "Unable to read json.";
            log.severe(tmp);
        }
    }
}

