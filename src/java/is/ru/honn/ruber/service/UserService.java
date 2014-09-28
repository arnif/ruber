package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by arnif on 9/27/14.
 */
public class UserService {

    private int MAX_USERS = 100;

    Logger log = Logger.getLogger(this.getClass().getName());


    //path /ruber/api/v1/user/{uuid}/add/trip
    public void addTrips(String uuid, ArrayList<Trip> tripArrayList) {
        //user.getUserHistory(uuid).addToHistory(tripArrayList);
    }

    //path /ruber/api/v1/user/{uuid}/history
    public void getHistory(String uuid) {
        //user.getHistories(uuid);
    }

    //path /ruber/api/v1/user/add param User obj
    public void signup(User u) {

        try {
            //userDB.addUser(u);
        } catch (UsernameExistsException uex) {
            log.severe(uex.getMessage());
        }
    }

    //path /ruber/api/v1/users
    public void getUsers(int offset) {
        // return userDb.getUsers(offset, MAX_USERS);
        //ArrayList<Integer> i = new ArrayList<Integer>();
        //i.subList(offset, MAX_USERS);
    }

    //path /ruber/api/v1/user/{uuid}
    public void getUser(String uuid) {
        try {
            //userDb.getUser(uuid)
        } catch(UserNotFoundException uex) {
            log.severe(uex.getMessage());
        }
    }
}
