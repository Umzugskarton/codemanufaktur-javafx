package commands.start;

import commands.Command;
import commands.lobby.LobbylistCommand;
import data.user.User;
import events.start.login.LoginEvent;
import events.start.login.LoginFailedEvent;
import events.start.login.LoginSuccessfulEvent;
import requests.IRequest;
import requests.start.login.LoginRequest;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class LoginCommand implements Command {

  private LoginRequest request;
  private ClientListener clientListener;
  private Server server;
  private ClientAPI clientAPI;

  public LoginCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (LoginRequest) r;
  }

  public void exec() {
    LoginEvent response = this.clientListener.getClientAPI()
        .login(request, this.server.getLoggedUsers().getUserList());
    if (response.getSuccess()) {
      User user = this.clientAPI.getUser(request.getUsername());
      this.clientListener.setUser(user);
      this.clientListener.send(new LoginSuccessfulEvent(user));

      // SOllte der Client über UserListRequest oder LobbyListRequest
      // selber anfordern.
      //this.server.sendToLoggedIn(this.server.getLoggedUsers());
      //LobbylistCommand command = new LobbylistCommand(this.clientListener);
      //command.exec();
    } else {
      this.clientListener.send(new LoginFailedEvent(response.getReason()));
    }
  }
}
