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
