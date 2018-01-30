package SRVevents;

public class createEvent implements Event {

  private String event = "create";
  private int id;
  private String msg;
  private boolean success;

  public createEvent(boolean success, int lobbyID, String msg) {
    this.success = success;
    this.id = lobbyID;
    this.msg = msg;
  }

  public boolean getSuccess() {
    return this.success;
  }

  public int getId() {
    return this.id;
  }

  public String getEvent() {
    return event;
  }

  public String getMsg() {
    return msg;
  }
}
