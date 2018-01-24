package GameMoves;


/**
 * Created on 16.12.2017.
 */
public class VoyageToMarketMove implements Move{
  private int shipId;
  private String stonesite;
  private int[] dumpOrder;

  public VoyageToMarketMove(){}

  public VoyageToMarketMove(int shipId, String stonesite, int[] dumpOrder){
    this.shipId = shipId;
    this.stonesite =stonesite;
    this.dumpOrder=dumpOrder;
  }


  public int[] getDumpOrder() {
    return dumpOrder;
  }

  public int getShipId() {
    return shipId;
  }

  @Override
  public String getType() {
    return "voyageToStoneSite";
  }

}
