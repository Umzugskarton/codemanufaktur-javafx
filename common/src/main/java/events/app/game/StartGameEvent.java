package events.app.game;

public class StartGameEvent extends GameEvent {

  public StartGameEvent(int lobbyId) {
    setLobbyId(lobbyId);
  }

}
