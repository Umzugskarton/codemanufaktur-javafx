package socket.commands;

import CLTrequests.Request;
import CLTrequests.leaveLobbyRequest;
import SRVevents.lobbyInfoEvent;
import commonLobby.CLTLobby;
import lobby.Lobby;
import socket.ClientListener;
import socket.Server;
import user.User;

import java.util.Date;

/**
 * Created by Slothan on 18.12.2017.
 */
public class leaveLobbyCommand implements Command {

    private ClientListener clientListener;
    private leaveLobbyRequest request;
    private Server server;

    public leaveLobbyCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.server = clientListener.getServer();
    }

    @Override
    public void put(Request r) {
        this.request = (leaveLobbyRequest) r;
    }

    @Override
    public void exec() {
        User user = this.clientListener.getUser();
        Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getId());
        clientListener.setLobby(lobby);
        lobby.leave(user);

        this.clientListener.setLobby(lobby);
        if(lobby.getUserCount() != 0) {
            this.clientListener.getServer()
                    .sendToLoggedIn(this.server.getLobbies(clientListener.getUser()));
            CLTLobby cltLobby = new CLTLobby(lobby.getLobbyID(), lobby.getName(),
                    lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
                    lobby.getHostName(), lobby.getReady(), lobby.getColors());
            lobbyInfoEvent lobbyInfo = new lobbyInfoEvent(cltLobby);
            server.sendToLobby(lobbyInfo, lobby);
        } else {

        }

    }
}
