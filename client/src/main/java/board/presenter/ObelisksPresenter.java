package board.presenter;

import board.view.ObelisksViewImplFx;
import data.lobby.CommonLobby;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObelisksPresenter implements StoneSitePresenter {

  private ObelisksViewImplFx obelisksController;
  private CommonLobby lobby;

  public ObelisksPresenter(CommonLobby lobby, ObelisksViewImplFx obelisksController) {
    this.lobby = lobby;
    this.obelisksController = obelisksController;
  }

  @Override
  public void setStones(ArrayList<Integer> stones) {
    int[] playerStones = new int[lobby.getUsers().size()];
    for (Integer stone : stones) {
      playerStones[stone]++;
    }
    int k = 0;
    for (int player : playerStones) {
      ArrayList<Group> stoneGroups = obelisksController.getStones();
      int playerroot = (player * 5);
      for (int i = 0; i < player; i++) {
        stoneGroups.get(playerroot + i).setVisible(true);
        Rectangle r = obelisksController.getColorStones(playerroot + i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(k).getColor()));
      }
      k++;
    }
  }
}
