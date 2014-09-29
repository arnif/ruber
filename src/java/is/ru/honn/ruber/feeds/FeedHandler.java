package is.ru.honn.ruber.feeds;


import is.ru.honn.ruber.domain.History;

/**
 * Created by arnif on 9/29/14.
 */
public interface FeedHandler
{
    public void processContent(History history);
}