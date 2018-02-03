package socket.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.IRequest;
import requests.RequestType;
import socket.ClientListener;

import java.util.EnumMap;

public class CommandFactory {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private EnumMap<RequestType, Command> commandMap = new EnumMap<>(RequestType.class);

    public CommandFactory(ClientListener clientListener){
        commandMap.put(RequestType.REGISTER, new RegisterCommand(clientListener));
        commandMap.put(RequestType.LOGIN, new LoginCommand(clientListener));
        commandMap.put(RequestType.LOGOUT, new LogoutCommand(clientListener));
        commandMap.put(RequestType.USERLIST, new UserlistCommand(clientListener));
        commandMap.put(RequestType.WHISPER, new WhisperCommand(clientListener));
        commandMap.put(RequestType.CHAT, new ChatCommand(clientListener));
        commandMap.put(RequestType.CREATE, new CreateCommand(clientListener));
        commandMap.put(RequestType.JOIN, new JoinCommand(clientListener));
        commandMap.put(RequestType.LOBBYLIST, new LobbylistCommand(clientListener));
        commandMap.put(RequestType.CHANGE_CREDENTIAL, new ChangeCredentialCommand(clientListener));
        commandMap.put(RequestType.CHANGE_COLOR, new ChangeColorCommand(clientListener));
        commandMap.put(RequestType.SET_READY, new SetReadyCommand(clientListener));
        commandMap.put(RequestType.START_GAME, new StartGameCommand(clientListener));
        commandMap.put(RequestType.LEAVE_LOBBY, new LeaveLobbyCommand(clientListener));
    }

    public Command getCommand(IRequest request){
        log.debug("BIN BEI: " +request.getClass().getSimpleName());
        Command c = commandMap.get(request.getType());
        c.put(request);
        return c;
    }
}
