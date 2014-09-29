package is.ru.honn.ruber.process;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.feeds.FeedException;
import is.ru.honn.ruber.feeds.FeedHandler;
import is.ru.honn.ruber.feeds.FeedReader;
import is.ru.honn.ruber.service.RuberService;
import is.ruframework.process.RuAbstractProcess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by arnif on 9/27/14.
 */
public class TripImportProcess extends RuAbstractProcess implements FeedHandler {

    Logger log = Logger.getLogger(this.getClass().getName());
    RuberService ruberService;
    MessageSource messageSource;
    FeedReader reader;


    @Override
    public void startProcess() {
        log.info("start process");

        /*
        log.info(messageSource.getMessage("processstart",
                new Object[] { getProcessContext().getProcessName() },
                Locale.getDefault()));

                */
        try
        {
            reader.read(getProcessContext().getImportURL());
        }
        catch (FeedException e)
        {

            /*
            log.info(messageSource.getMessage("processreaderror",
                    new Object[] { getProcessContext().getImportFile() },
                    Locale.getDefault()));
            log.info(e.getMessage());
            */


        }

        /*
           log.info(messageSource.getMessage("processstartdone",
                new Object[] {ruberService.getContents().size()},
                Locale.getDefault()));  */


    }

    @Override
    public void beforeProcess() {
        log.info("before process");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/app.xml");
        ruberService = (RuberService) ctx.getBean("RuberService");
        reader = (FeedReader) ctx.getBean("feedReader");

        reader.setFeedHandler(this);

    }

    @Override
    public void afterProcess() {
        log.info("after process");
    }

    @Override
    public void processContent(String uuid, ArrayList<Trip> trips) {
        log.info("processing content");
        User u = new User(uuid, "foobar", "foo", "bar", "1234", "foo@bar.is", "none", "213");
        ruberService.signup(u);
        for (Trip trip : trips) {
            ruberService.addTrip(uuid, trip);
        }
    }
}
