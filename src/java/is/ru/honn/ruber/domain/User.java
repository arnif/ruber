package is.ru.honn.ruber.domain;

/**
 * Created by arnif on 9/24/14.
 */
public class User {

    private String uuid;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String picture;
    private String promoCode;

    public User() { }

    /**
     * Create new user
     * @param uuid unique identifier of the user
     * @param userName user name of the user
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param password password of the user
     * @param email email of the user
     * @param picture picture of the user
     * @param promoCode promo code of the user
     */
    public User(String uuid, String userName, String firstName, String lastName, String password, String email, String picture, String promoCode) {
        this.uuid = uuid;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.promoCode = promoCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
