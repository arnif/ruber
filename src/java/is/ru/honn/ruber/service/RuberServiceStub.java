package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.*;
import is.ruframework.domain.RuObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuberServiceStub extends RuObject implements RuberService {

  private final int MAX_USERS = 100;
  private Map<String, User> users = new HashMap<String, User>();
  private Map<String, History> historyMap = new HashMap<String, History>();

  private ArrayList<Trip> trips = new ArrayList<Trip>(); //TODO frekar en mappið ?

  public RuberServiceStub() {}

  public RuberServiceStub(User user) {
      signup(user);
  }

  @Override
  public List getProducts(double latitude, double longitude) throws ServiceException {
    JSONParser parser = new JSONParser();
    List productList = new ArrayList();

    try {
      JSONObject json = (JSONObject) parser.parse(new FileReader("products.json"));
      JSONArray products = (JSONArray) json.get("products");

      for (int i = 0; i < products.size(); i++) {

        JSONObject jsonProduct = (JSONObject) products.get(i);
        Product product = new Product();
        product.setImage((String) jsonProduct.get("image"));
        product.setProduct_id((String) jsonProduct.get("product_id"));
        product.setDescription((String) jsonProduct.get("description"));
        product.setDisplay_name((String) jsonProduct.get("display_name"));
        product.setCapacity(((Long) jsonProduct.get("capacity")).intValue());
        productList.add(product);
      }

    } catch (Exception e) {
      String tmp = "Unable to read products.json file.";
      log.severe(tmp);
      throw new ServiceException(tmp, e);
    }
    return productList;
  }

  @Override
  public List<Price> getPriceEstimates(double start_latitude, double start_longitude,
                                       double end_latitude, double end_longitude) throws ServiceException {
    List<Price> priceList = new ArrayList<Price>();
    priceList.add(new Price("08f17084-23fd-4103-aa3e-9b660223934b", "USD", "UberBLACK", 23, 29, 1));
    priceList.add(new Price("9af0174c-8939-4ef6-8e91-1a43a0e7c6f6", "USD", "UberSUV", 36, 44, 1.25));
    priceList.add(new Price("aca52cea-9701-4903-9f34-9a2395253acb", null, "uberTAXI", -1, -1, 1));
    priceList.add(new Price("a27a867a-35f4-4253-8d04-61ae80a40df5", "USD", "uberX", 15, 15, 1));
    return priceList;
  }

    @Override
    public void addTrip(String uuid, Trip trip) {
        if (users.containsKey(uuid)) {

            //Kannski
            trips.add(trip);
            ///kannski

            if (historyMap.containsKey(uuid)) {
                historyMap.get(uuid).getTrips().add(trip);
            } else {
                History h = new History();
                h.setLimit(100);
                h.setCount(5);
                h.setOffset(0);
                ArrayList<Trip> t = new ArrayList<Trip>();
                t.add(trip);
                h.setTrips(t);
                historyMap.put(uuid, h);
            }
        } else {
            throw new UserNotFoundException("Could not add trip, user not found");
        }
    }

    @Override
    public History getHistory(String uuid) { //TODO skila lista af Trips ?
        if (users.containsKey(uuid)) {

            //KANNSKI
            ArrayList<Trip> returnTrips = new ArrayList<Trip>();
            for (Trip trip : trips) {
                if(trip.getUuid().equals(uuid)) {
                   returnTrips.add(trip);
                }
            }
            //return returnTrips; //KANNSKI!

            return historyMap.get(uuid);
        } else {
            throw new UserNotFoundException("Could not get history, user not found");
        }
    }

    @Override
    public void signup(User user) {
        for (Map.Entry<String, User> stringUserEntry : users.entrySet()) {
            if (stringUserEntry.getValue().getUserName().equals(user.getUserName())) {
                throw new UsernameExistsException("Username exists");
            }
        }

        users.put(user.getUuid(), user);
    }

    @Override
    public List<User> getUsers(int offset, int count) {
        ArrayList<User> userArrayList = new ArrayList<User>();
        userArrayList.addAll(users.values());

        if (count > MAX_USERS) {
            count = MAX_USERS;
        }
        return userArrayList.subList(offset, offset + count);
    }

    @Override
    public User getUser(String uuid) {
        User u = users.get(uuid);
        if (u != null) {
            return u;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

}
