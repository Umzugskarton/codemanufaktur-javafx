package ui.app.game.userinterface;

import static misc.language.TextBundle.getString;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.SiteType;
import helper.fxml.GenerateFXMLView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import org.omg.CORBA.OBJ_ADAPTER;
import ui.app.game.IGameView;


public class UserInterfaceView implements IUserInterfaceView {

  @FXML
  private GridPane userInterfacePane;

  @FXML
  public Label uiBannerLabel;

  @FXML
  public Label uiBannerSmallLabel;

  @FXML
  public Label currentPlayerLabel;

  @FXML
  public Rectangle playerColorRectangle;

  @FXML
  private GridPane holdingArea;

  @FXML
  private ComboBox<Integer> selectShipBox;

  @FXML
  private ComboBox<String> selectShipLocationBox;

  @FXML
  private ComboBox<Integer> selectShipToLocationBox;

  @FXML
  private ComboBox<Integer> selectStoneLocationBox;


  private final IGameView parentView;
  private final UserInterfacePresenter mainPresenter;
  private final EventBus eventBus;

  private final User user;
  private CommonLobby lobby;

  // Own Parent
  private Parent myParent;

  public UserInterfaceView(IGameView parentView, EventBus eventBus, Connection connection,
      User user, CommonLobby lobby) {
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.lobby = lobby;
    this.user = user;
    this.mainPresenter = new UserInterfacePresenter(this, eventBus, connection, user, lobby);
    bind();
    initOwnView();
  }

  private void bind() {
    eventBus.register(this);
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/game/board/UserInterfaceView.fxml", this, eventBus);
    }
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  public GridPane getUserInterface() {
    return userInterfacePane;
  }

  public GridPane getHoldingArea() {
    return holdingArea;
  }

  public Label getCurrentPlayerLabel() {
    return currentPlayerLabel;
  }

  public Rectangle getPlayerColorRectangle() {
    return this.playerColorRectangle;
  }

  public Label getUiBannerLabel() {
    return this.uiBannerLabel;
  }

  public Label getUiBannerSmallLabel() {
    return this.uiBannerSmallLabel;
  }

  public ComboBox<Integer> getSelectStoneLocationBox() {
    return selectStoneLocationBox;
  }

  public ComboBox<String> getSelectShipLocationBox() {
    return selectShipLocationBox;
  }

// TODO Moves richtig mit Parent ausführen

  @FXML
  public void sendFillUpStorageMove() {
    mainPresenter.sendFillUpStorageMove();
  }

  @FXML
  void setStoneLocationCBox(ActionEvent event) {
    if (selectShipToLocationBox.getValue() != null) {
      mainPresenter.setStoneLocationCBox(selectShipToLocationBox.getValue());
    } else {
      System.out.println("Null");
    }
  }

  public ArrayList<ComboBox<Integer>> getShipCBoxes() {
    ArrayList<ComboBox<Integer>> a = new ArrayList<>();
    Collections.addAll(a, selectShipBox, selectShipToLocationBox);
    return a;
  }

  @FXML
  void sendVoyageToStoneSiteMove() {
    HashMap<String, SiteType> findSiteType = new HashMap<>();
    System.out.println("USERINTERFACEVIEW:" + getString("site." + SiteType.OBELISKS.name()) + " "
        + SiteType.OBELISKS.name());
    findSiteType.put(getString("site." + SiteType.OBELISKS.name()), SiteType.OBELISKS);
    findSiteType.put(getString("site." + SiteType.MARKET.name()), SiteType.MARKET);
    findSiteType
        .put(getString("site." + SiteType.BURIALCHAMBER.name()), SiteType.BURIALCHAMBER);
    findSiteType.put(getString("site." + SiteType.PYRAMID.name()), SiteType.PYRAMID);
    findSiteType.put(getString("site." + SiteType.TEMPLE.name()), SiteType.TEMPLE);
    if (selectShipBox.getValue() != null && selectShipLocationBox.getValue() != null) {
      mainPresenter.sendVoyageToStoneSiteMove(selectShipBox.getValue(),
          findSiteType.get(selectShipLocationBox.getValue()));
    } else {
      System.out
          .println("A: " + selectShipBox.getValue() + " B: " + selectShipLocationBox.getValue());
    }
  }

  @FXML
  void sendLoadUpShipMove() {
    if (selectShipToLocationBox.getValue() != null && selectStoneLocationBox.getValue() != null) {
      System.out.println(
          "A: " + selectShipToLocationBox.getValue() + "B: " + selectStoneLocationBox.getValue());
      mainPresenter.sendLoadUpShipMove(selectShipToLocationBox.getValue(),
          selectStoneLocationBox.getValue());
    }
  }

}
