package socket.commands;

import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

/**
 * Created by fabianrieger on 27.07.17.
 */
public class loginCommand implements command {
    private JSONObject request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public loginCommand(ClientListener clientListener, JSONObject request){
        this.clientListener=clientListener;
        this.request =request;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }
    public void exec(){
        JSONObject response = this.clientListener.getClientAPI().login(request, this.clientListener.getServer().getLoggedUsers());
        if ((boolean) response.get("success")) {
            this.clientListener.setUser(this.clientAPI.getUser((String) request.get("username")));
            this.server.sendToLoggedIn(this.server.getLoggedUsers());
        }
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
