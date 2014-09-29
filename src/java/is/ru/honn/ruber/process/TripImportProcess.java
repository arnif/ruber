package is.ru.honn.ruber.process;

import is.ru.honn.ruber.service.RuberService;
import is.ruframework.process.RuAbstractProcess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

/**
 * Created by arnif on 9/27/14.
 */
public class TripImportProcess extends RuAbstractProcess {

    Logger log = Logger.getLogger(this.getClass().getName());
    RuberService ruberService;
    MessageSource messageSource;

    @Override
    public void startProcess() {
        log.info("start process");

    }

    @Override
    public void beforeProcess() {
        log.info("before process");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("app.xml");
        ruberService = (RuberService) ctx.getBean("RuberService");

    }

    @Override
    public void afterProcess() {
        log.info("after process");

    }
}
