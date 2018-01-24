package CLTrequests;

import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class createRequest implements IRequest {
    private String request = "create";
    private String name;
    private int size;
    private String pw;

    public createRequest() {

    }

    public createRequest(String name, int size, String password) {
        this.size = size;
        this.pw = password;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.request;
    }

    public int getSize() {
        return this.size;
    }

    public String getPassword() {
        return this.pw;
    }

}
