package socket.commands;

import CLTrequests.Request;
import java.util.HashMap;
import socket.ClientListener;

public class CommandFactory {


  private HashMap<String, Command> Dict = new HashMap<>();

  public CommandFactory(ClientListener clientListener) {
    Dict.put("register", new registerCommand(clientListener));
    Dict.put("login", new loginCommand(clientListener));
    Dict.put("logout", new logoutCommand(clientListener));
    Dict.put("userlist", new userlistCommand(clientListener));
    Dict.put("whisper", new whisperCommand(clientListener));
    Dict.put("chat", new chatCommand(clientListener));
    Dict.put("create", new createCommand(clientListener));
    Dict.put("join", new joinCommand(clientListener));
    Dict.put("lobbylist", new lobbylistCommand(clientListener));
    Dict.put("changeCredential", new changeCredentialCommand(clientListener));
  }

  public Command getCommand(Request request) {
    Command c = Dict.get(request.getRequest());
    c.put(request);
    return c;
  }
}
