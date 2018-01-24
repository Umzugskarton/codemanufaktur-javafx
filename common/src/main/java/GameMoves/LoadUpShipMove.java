package GameMoves;


public class LoadUpShipMove implements Move{
  private String move = "LoadUpShip";
  private int shipId;
  private int position;

  public LoadUpShipMove(){

  }

  public LoadUpShipMove(int shipId, int position){
    this.shipId = shipId;
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public int getShipId() {
    return shipId;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setShipId(int shipId) {
    this.shipId = shipId;
  }

  @Override
  public String getType() {
    return move;
  }


}
