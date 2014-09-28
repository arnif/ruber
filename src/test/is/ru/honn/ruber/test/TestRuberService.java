package is.ru.honn.ruber.test;

import is.ru.honn.ruber.service.RuberService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestRuberService extends TestCase
{

  Logger log = Logger.getLogger(TestRuberService.class.getName());

  @Autowired
  private RuberService service;

  @Before
  public void setUp() throws Exception
  {

  }

  @Test
  public void testUser()
  {
    log.info("testUser");
    fail();
  }

  @Test
  public void testActivity()
  {
    log.info("testActivity");
    fail();
  }
}
