package game.board.Cards;

public class StatueCard extends Card {

  private static final int additionalStatuePoints = 2;

  private int[] points = {1, 3, 6, 10, 15};

  public int calc(int numberOfCards) {

    int additional = 0;
    if (numberOfCards > points.length) {
      additional = numberOfCards - points.length;
    }
    return points[numberOfCards - 1 - additional] + additional * additionalStatuePoints;
  }
}
