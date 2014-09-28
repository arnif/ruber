package is.ru.honn.ruber.domain;

import java.util.ArrayList;

/**
 * Created by arnif on 9/24/14.
 */
public class History {

    private int offset;
    private int limit;
    private int count;
    private ArrayList<Trip> trips;

    public History(int offset, int limit, int count, ArrayList<Trip> trips) {
        this.offset = offset;
        this.limit = limit;
        this.count = count;
        this.trips = trips;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }
}
