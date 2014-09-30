package is.ru.honn.ruber.test;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.service.RuberService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestRuberService extends TestCase
{
  Logger log = Logger.getLogger(TestRuberService.class.getName());

  private User user;

  @Autowired
  private RuberService service;

  @Before
  public void setUp() throws Exception
  {
        user = new User();
  }

  @Test
  public void testUser()
  {
      log.info("testUser");

      //Get existing users
      assertEquals("Foo", service.getUser("lol").getFirstName());

      //Correct exception handling if user already exists.
      try {
          //User(String uuid, String userName, String firstName, String lastName, String password, String email, String picture, String promoCode) {
          User u = new User("123", "foo", null, null, null, null, null, null);
          service.signup(u);
          fail("Should have failed user name already exists");
      } catch (Exception e) {
          assertEquals("Username exists", e.getMessage());
      }

      //Correct exception handling if user does not exists.
      try {
          service.getUser("NoUser");
          fail("Should have failed, user does not exists");
      } catch (Exception e) {
          assertEquals("User not found", e.getMessage());
      }
  }

  @Test
  public void testActivity()
  {
      log.info("testActivity");

      User u = new User("123", "sindris12", "Sindri", "Sig", "123456", "s@s.is", "none", "yolo");
      service.signup(u);

      //check if mock user is added correctly
      assertEquals(service.getUser("123"), u);

      //Add new Trip for a user
      Trip t = new Trip("123", 123, "abcd", 3.2f, 456, 789);
      service.addTrip("123", t);
      assertEquals(service.getHistory("123").getTrips().get(0), t);

      Trip t2 = new Trip("123", 444, "ddd", 4.5f, 888, 987);
      service.addTrip("123", t2);

      ArrayList<Trip> mockTrips = new ArrayList<Trip>();
      mockTrips.add(t);
      mockTrips.add(t2);

      //Get history of all trips for a user
      ArrayList<Trip> returnedTrips = service.getHistory("123").getTrips();

      assertEquals(mockTrips, returnedTrips);
      //Correct exception handling if user does not exist
      try {
          service.getHistory("NoUser");
          fail("Should have failed, user does not exists");
      } catch(Exception e) {
          assertEquals(e.getMessage(), "Could not get history, user not found");
      }
  }
}
