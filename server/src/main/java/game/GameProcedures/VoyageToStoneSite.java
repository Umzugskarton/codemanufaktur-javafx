package game.GameProcedures;

import GameEvents.DockingShipError;
import GameEvents.ShipAlreadyDockedError;
import GameEvents.ShipDockedEvent;
import GameMoves.Move;
import GameEvents.SiteAlreadyDockedError;
import GameMoves.VoyageToStoneSiteMove;
import SRVevents.Event;
import game.Game;
import game.board.Ship;
import game.board.StoneSite;

import java.lang.reflect.Method;

public class VoyageToStoneSite implements Procedure {
  private VoyageToStoneSiteMove move;
  private Game game;
  private int playerId;

  VoyageToStoneSite(Game game, int playerId) {
    this.game = game;
    this.playerId = playerId;
  }

  public void put(Move move) {
    this.move = (VoyageToStoneSiteMove) move;
  }

  public Event exec() {
    Ship ship = game.getShipByID(move.getShipId());
    StoneSite site;
    Method method;

    try {
      method = game.getClass().getMethod("get" + move.getStonesite());
      site = (StoneSite) method.invoke(game);
    } catch (Exception e) {
      return new DockingShipError();
    }

    if (!ship.isDocked()) {
      if (site.dockShip(ship)){
        ship.setDocked(true);

        return new ShipDockedEvent(move.getShipId(), move.getStonesite(), game.getPointsSum());
      }
      else {
        return new SiteAlreadyDockedError(move.getStonesite());
      }

    } else {
      return new ShipAlreadyDockedError(move.getShipId());
    }
  }
}
