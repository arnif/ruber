package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.*;

import java.util.List;

public interface RuberService {
  public List<Product> getProducts(double latitude, double longitude) throws ServiceException;
  public List<Price> getPriceEstimates(double startLatitude, double startLongitude,
                                       double endLatitude, double endLongitude) throws ServiceException;

    /**
     * add one trip to the user database
     * PUT /v1/ruber/trip/uuid
     * @param uuid unique identifier of the user
     * @param trip that will be added to the user
     * API returns status code 201 Created for successful PUT
     */
  public void addTrip(String uuid, Trip trip);

    /**
     * get history from one user
     * GET /v1/ruber/history/uuid
     * @param uuid unique identifier of the user
     * @return history object that contains array of tips.
     * Example:
     * {
        "offset": 0,
        "limit": 1,
        "count": 5,
        "history": [
          {
            "uuid": "7354db54-cc9b-4961-81f2-0094b8e2d215",
            "request_time": 1401884467,
            "product_id": "edf5e5eb-6ae6-44af-bec6-5bdcf1e3ed2c",
            "status": "completed",
            "distance": 0.0279562,
            "start_time": 1401884646,
            "end_time": 1401884732
          }
        ]
        }
     */
  public History getHistory(String uuid);

    /**
     * create new user
     * POST /v1/ruber/signup
     * @param user object
     * @return unique identifier of the user that was created
     * Example:
     * {
     *     "uuid": "7354db54-cc9b-4961-81f2-0094b8e2d215"
     * }
     */
  public String signup(User user);

    /**
     * get list of users with pagination support
     * GET /v1/ruber/users
     * @param offset position in pagination.
     * @param count total number of items to get (MAX 100).
     * @return list of users requested
     * Example:
     * {[
     *      "uuid": "7354db54-cc9b-4961-81f2-0094b8e2d215",
     *      "userName": "JohnM",
     *      "firstName": "John",
     *      "lastName": "McClane",
     *      "password": "213ijlkdfa92kljdsxsdf",
     *      "email": "john@badass.com",
     *      "picture": "/img/JohnM_1230.png",
     *      "promoCode" "BADASS"
     *  ],
     *  [
     *      "uuid": "998847b3-cc9b-4961-81f2-00823yuhbad55",
     *      "userName": "SteveMc",
     *      "firstName": "Steve",
     *      "lastName": "McQueen",
     *      "password": "9lkdjgaklsd9jgdk",
     *      "email": "steve@badass.com",
     *      "picture": "/img/SteveMc_1230.png",
     *      "promoCode" "BADASS"
     *  ]}
     */
  public List<User> getUsers(int offset, int count);

    /**
     * get one user
     * GET /v1/ruber/user/uuid
     * @param uuid unique identifier of the user
     * @return the user requested
     * Example:
     * {
     *      "uuid": "7354db54-cc9b-4961-81f2-0094b8e2d215",
     *      "userName": "JohnM",
     *      "firstName": "John",
     *      "lastName": "McClane",
     *      "password": "213ijlkdfa92kljdsxsdf",
     *      "email": "john@badass.com",
     *      "picture": "/img/JohnM_1230.png",
     *      "promoCode" "BADASS"
     *  }
     */
  public User getUser(String uuid);

}
