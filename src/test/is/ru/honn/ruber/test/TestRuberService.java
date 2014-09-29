package is.ru.honn.ruber.test;

import is.ru.honn.ruber.domain.User;
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

  private User user = new User();

  @Autowired
  private RuberService service;

  @Before
  public void setUp() throws Exception
  {
//      user = service.getUser(user.getUserName());
  //    System.out.println(user.getFirstName());
  }

  @Test
  public void testUser()
  {
      log.info("testUser");

      //Sign up user

      //User user = new User("lol", "sindris12", "Sindri", "Sigurjonsson", "1234", "sindris12@ru.is", "pic", "promofreestuff");

      service.signup(user);

      //Get existing users

      assertEquals("Foo", service.getUser("foo").getFirstName());

      //Correct exception handling if user already exists.

      try {
          service.signup(user);
          fail();
      } catch (Exception e) {
          assertEquals("Username exists", e.getMessage());
      }

      //Correct exception handling if user does not exists.

      try {
          service.getUser("NoUser");
          fail();

      } catch (Exception e) {
          assertEquals("User not found", e.getMessage());
      }
  }

  @Test
  public void testActivity()
  {
      log.info("testActivity");

      //service.getUser("sindris12").getId();

      //Add new Trip for a user
      //Get history of all trips for a user
      //Correct exception handling if user does not exist

  }
}
