package is.ru.honn.ruber.domain;

/**
 * Created by arnif on 9/24/14.
 */
public class Trip {

    private String uuid;
    private int request_time;
    private String product_id;
    private float distance;
    private int start_time;
    private int end_time;
    private String tripStatus;

    public enum TripStatus {
        completed;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public Trip() { }

    /**
     * Create new trip
     * @param uuid unique user identifier.
     * @param request_time unix timestamp of trip request time.
     * @param product_id unique identifier representing a specific product for a given latitude & longitude.
     * @param distance length of trip in km.
     * @param start_time unix timestamp of trip start time.
     * @param end_time unix timestamp of trip end time.
     * @param tripStatus status of the trip. Only returns completed for now.
     */

    public Trip(String uuid, int request_time, String product_id, float distance, int start_time, int end_time, TripStatus tripStatus) {
        this.uuid = uuid;
        this.request_time = request_time;
        this.product_id = product_id;
        this.distance = distance;
        this.start_time = start_time;
        this.end_time = end_time;
        this.tripStatus = tripStatus.completed.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRequest_time() {
        return request_time;
    }

    public void setRequest_time(int request_time) {
        this.request_time = request_time;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }
}
