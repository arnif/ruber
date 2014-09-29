package is.ru.honn.ruber.feeds;

/**
 * Created by arnif on 9/29/14.
 */
public interface FeedReader {
    public void read(String url) throws FeedException;
    public void setFeedHandler(FeedHandler handler);
}