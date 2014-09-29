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
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by arnif on 9/27/14.
 */
public class TripImportProcess extends RuAbstractProcess implements FeedHandler {

    Logger log = Logger.getLogger(this.getClass().getName());
    RuberService ruberService;
    MessageSource messageSource;
    FeedReader reader;

    private Locale locale = new Locale("IS"); //IS = icelandic
    private String userId;

    @Override
    public void startProcess() {
        log.info(messageSource.getMessage("processstart",
                new Object[] { getProcessContext().getProcessName() },
                locale));
        try {
            reader.read(getProcessContext().getImportURL());
        }
        catch (FeedException e) {
            log.severe(messageSource.getMessage("processreaderror",
                    new Object[]{getProcessContext().getImportFile()},
                    locale));
        }
    }

    @Override
    public void beforeProcess() {

        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/app.xml");
        ruberService = (RuberService) ctx.getBean("RuberService");
        reader = (FeedReader) ctx.getBean("feedReader");
        messageSource = (MessageSource) ctx.getBean("messageSource");

        log.info(messageSource.getMessage("processbefore",
                new Object[] { getProcessContext().getProcessName() } ,
                locale));

        reader.setFeedHandler(this);
    }

    @Override
    public void afterProcess() {
        log.info(messageSource.getMessage("processstartdone",
                new Object[]{ruberService.getHistory(userId).getTrips().size()},
                locale));
    }

    @Override
    public void processContent(String uuid, ArrayList<Trip> trips) {
        userId = uuid;
        //User should exists but does not so we create the user
        User u = new User(uuid, "foobar", "foo", "bar", "1234", "foo@bar.is", "none", "213");
        ruberService.signup(u);
        for (Trip trip : trips) {
            ruberService.addTrip(uuid, trip);
        }
    }
}
