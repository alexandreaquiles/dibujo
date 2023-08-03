package dibujo;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BucketFill implements Command {
  public BucketFill() {
  }

  public Canvas execute(PrintStream out, String line, Canvas canvas) {
    if (canvas.getPositions() == null) {
      throw new RuntimeException("No canvas. You should create a canvas before filling it.");
    }

    Matcher matcher = Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line);
    if (matcher.find()) {
      int startingX = Integer.parseInt(matcher.group(1));
      int startingY = Integer.parseInt(matcher.group(2));
      String colorCharacter = matcher.group(3);

      Position startingPositionToFill = new Position(startingX, startingY);
      fill(canvas.getWidth(), canvas.getHeight(), canvas.getPositions(), startingPositionToFill, colorCharacter);
    } else {
      throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
    }
    return canvas;
  }

  @Override
  public boolean accept(String line) {
    return line.startsWith("B");
  }

  private void fill(int canvasWidth, int canvasHeight, Position[][] canvasPositions, Position startingPositionToFill, String colorCharacter) {
    fill(canvasWidth, canvasHeight, canvasPositions, startingPositionToFill, colorCharacter, new HashSet<>());
  }

  private void fill(int canvasWidth, int canvasHeight, Position[][] canvasPositions, Position positionToFill, String fillColor, Set<Position> visited) {
    if (visited.contains(positionToFill) || positionToFill.getX() <= 0 || positionToFill.getX() > canvasWidth || positionToFill.getY() <= 0 || positionToFill.getY() > canvasHeight) {
      return;
    }

    Position canvasPosition = canvasPositions[positionToFill.getY() - 1][positionToFill.getX() - 1];
    if (canvasPosition.isFilled()) {
      return;
    }
    canvasPosition.changeColor(fillColor);
    visited.add(positionToFill);

    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.leftUp(), fillColor, visited);
    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.centerUp(), fillColor, visited);
    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.rightUp(), fillColor, visited);

    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.right(), fillColor, visited);
    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.rightDown(), fillColor, visited);

    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.centerDown(), fillColor, visited);

    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.leftDown(), fillColor, visited);
    fill(canvasWidth, canvasHeight, canvasPositions, positionToFill.left(), fillColor, visited);
  }
}