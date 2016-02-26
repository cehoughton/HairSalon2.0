import org.sql2o.*;
import java.util.List;

public class Clients {
  private int mId;
  private String mName;
  private int mStylistsId;

  public Clients (String name, int stylists_id) {
    this.mName = name;
    this.mStylistsId = stylists_id;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public int getStylistsId() {
    return mStylistsId;
  }

  @Override
  public boolean equals(Object otherClients){
    if (!(otherClients instanceof Clients)) {
      return false;
    } else {
      Clients newClients = (Clients) otherClients;
      return this.getName().equals(newClients.getName()) &&
             this.getId() == newClients.getId() &&
             this.getStylistsId() == newClients.getStylistsId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, stylists_id) VALUES (:name, :stylists_id)";
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .addParameter("stylists_id", this.mStylistsId)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Clients> all() {
    String sql = "SELECT id AS mId, name AS mName, stylists_id AS mStylistsId FROM clients";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Clients.class);
    }
  }
}
