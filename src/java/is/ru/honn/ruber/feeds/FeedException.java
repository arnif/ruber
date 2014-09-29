package is.ru.honn.ruber.feeds;

/**
 * Created by arnif on 9/29/14.
 */
public class FeedException extends Exception {
    public FeedException(String message)
    {
        super(message);
    }

    public FeedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
