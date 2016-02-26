import org.junit.*;
import static org.junit.Assert.*;

public class ClientsTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Clients.all().size(), 0);
 }
}
