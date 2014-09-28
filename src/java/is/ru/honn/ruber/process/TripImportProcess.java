package is.ru.honn.ruber.process;

import is.ru.honn.ruber.service.RuberService;
import is.ruframework.process.RuProcess;
import is.ruframework.process.RuProcessContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

/**
 * Created by arnif on 9/27/14.
 */
public class TripImportProcess implements RuProcess {

    Logger log = Logger.getLogger(this.getClass().getName());
    RuberService ruberService;
    MessageSource messageSource;

    @Override
    public void setProcessContext(RuProcessContext processContext) {

    }

    @Override
    public void setParameters(String[] params) {

    }

    @Override
    public void startProcess() {

    }

    @Override
    public void beforeProcess() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("app.xml");
        ruberService = (RuberService) ctx.getBean("RuberService");

    }

    @Override
    public void afterProcess() {

    }
}
