import java.util.List;
import org.sql2o.*;

public class Stylists {
  private int mId;
  private String mName;

  public Stylists(String name) {
    this.mName = name;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  @Override
  public boolean equals(Object otherStylists){
    if (!(otherStylists instanceof Stylists)) {
      return false;
    } else {
      Stylists newStylists = (Stylists) otherStylists;
      return this.getName().equals(newStylists.getName()) &&
        this.getId() == newStylists.getId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name) VALUES (:name)";
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Stylists> all() {
    String sql = "SELECT id AS mId, name AS mName FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylists.class);
    }
  }
}
