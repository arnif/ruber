package is.ru.honn.ruber.feeds;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.service.RuberService;
import is.ruframework.http.SimpleHttpRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 9/29/14.
 */
public class RssReader extends AbstractFeedReader
{
    public RssReader()
    {
    }

    public void setFeedHandler(FeedHandler handler)
    {
        this.handler = handler;
    }

    public void read(String source) throws FeedException
    {
        if (handler == null)
        {
            throw new FeedException("Handler unspecified");
        }

        URL feedUrl = null;
        try
        {
            feedUrl = new URL(source);
        }
        catch (MalformedURLException murlex)
        {
            throw new FeedException ("URL is not correct", murlex);
        }

        // Open the feed
        JSONParser parser = new JSONParser();
        List productList = new ArrayList();

        try
        {
            String s = SimpleHttpRequest.sendGetRequest(String.valueOf(feedUrl));

            //System.out.println(s);

            JSONObject json = (JSONObject) parser.parse(s);

            System.out.println(json.toJSONString());


            JSONArray historyArr = (JSONArray) json.get("history");

            System.out.println(historyArr.toJSONString());

            List<Trip> trips = new ArrayList<Trip>();

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
                trip.setUuid((String) jsonProduct.get("uuid"));

                trips.add(trip);

                uuid = (String) jsonProduct.get("uuid");

            }

            RuberService ruberService;

            //System.out.println(offset.toString());


        }
        catch (Exception e)
        {
            String tmp = "Unable to read products.json file.";
            System.out.println(e.getMessage());
            //log.severe(tmp);
            //throw new ServiceException(tmp, e);
        }


    }
}

