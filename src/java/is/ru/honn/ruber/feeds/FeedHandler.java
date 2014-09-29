package is.ru.honn.ruber.feeds;


import is.ru.honn.ruber.domain.Trip;

import java.util.ArrayList;

/**
 * Created by arnif on 9/29/14.
 */
public interface FeedHandler
{
    public void processContent(String uuid, ArrayList<Trip> trips);
}