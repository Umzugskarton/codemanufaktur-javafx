package SRVevents;

import java.util.Date;

/**
 * Created by Slothan on 23.10.2017.
 */
public class changeCredentialEvent implements Event {
    private String event = "changeCredential";
    private Date date;
    private String msg;
    private boolean validate;
    private String credential;
    private Integer type;

    public changeCredentialEvent() { this.date = new Date(); }
    public void setMsg(String msg) { this.msg = msg; }
    public void setSuccess(boolean validate) { this.validate = validate; }
    public void setCredential(String credential) { this.credential = credential; }
    public void setType(Integer type) { this.type = type; }
    public String getMsg() { return this.msg; }
    public Date getDate() { return this.date; }
    public boolean getSuccess() { return this.validate; }
    public String getCredential() { return this.credential; }
    public Integer getType() { return this.type; }

}
