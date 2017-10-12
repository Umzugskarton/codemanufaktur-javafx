package socket.commands;

import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class chatCommand implements Command {
    private JSONObject request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public chatCommand(ClientListener clientListener, JSONObject request){
        this.clientListener=clientListener;
        this.request =request;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }
    public void exec(){
        String chatMessage = this.clientAPI.chat(request, this.clientListener.getUser());
        this.server.sendToLoggedIn(chatMessage);
    }
}
