package CLTrequests;

import java.util.Date;


public class changeColorRequest implements Request {

  private String request = "changeColor";
  private Date date;

  public changeColorRequest() {
  }


  public String getType() {
    return this.request;
  }

  public Date getDate() {
    return this.date;
  }

}
