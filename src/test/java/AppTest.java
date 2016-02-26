import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.junit.ClassRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("");
  }

  @Test
   public void stylistAddedSuccessfully() {
     goTo("http://localhost:4567/");
     fill("#stylistname").with("Russ");
     submit(".Next");
     assertThat(pageSource()).contains("Russ");
   }

   @Test
    public void stylistRemoved() {
      Stylists myStylist = new Stylists("Susan");
      myStylist.save();
      goTo("http://localhost:4567/");
      click("option", withText("Susan"));
      submit(".delete-stylist");
      assertThat(!(pageSource()).contains(""));
  }

  @Test
   public void stylistUpdatedSuccessfully() {
     Stylists myStylist = new Stylists("Susan");
     myStylist.save();
     goTo("http://localhost:4567/");
     click("option", withText("Susan"));
     fill("#newName-stylist").with("George");
     submit(".update-stylist");
     assertThat(pageSource()).contains("George");
   }

   @Test
    public void clientAddedSuccessfully() {
      Stylists myStylist = new Stylists("Susan");
      myStylist.save();
      goTo("http://localhost:4567/");
      click("a", withText("Susan"));
      fill("#newClientName").with("George");

      submit(".Add-client");
      assertThat(pageSource()).contains("George");
    }

}
