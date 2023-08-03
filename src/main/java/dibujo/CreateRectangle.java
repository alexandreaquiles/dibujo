package dibujo;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateRectangle implements Command {
  public CreateRectangle() {
  }

  public Canvas execute(PrintStream out, String line, Canvas canvas) {
    if (canvas.getPositions() == null) {
      throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
    }

    Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
    if (matcher.find()) {
      int upperLeftCornerX = Integer.parseInt(matcher.group(1));
      int upperLeftCornerY = Integer.parseInt(matcher.group(2));
      int lowerRightCornerX = Integer.parseInt(matcher.group(3));
      int lowerRightCornerY = Integer.parseInt(matcher.group(4));

      if (upperLeftCornerX <= 0 || upperLeftCornerY <= 0 || lowerRightCornerX <= 0 || lowerRightCornerY <= 0) {
        throw new RuntimeException("Invalid parameters: the upper left corner and lower right corner coordinates should be greater than zero. Given parameters: " + null);
      }

      if (upperLeftCornerX > canvas.getWidth() || lowerRightCornerX > canvas.getWidth() || upperLeftCornerY > canvas.getHeight() || lowerRightCornerY > canvas.getHeight()) {
        throw new RuntimeException("Invalid parameters: the rectangle coordinates should not be off limits. Given parameters: " + null + " upperLeftCorner(X=" + upperLeftCornerX + ", Y=" + upperLeftCornerY + " lowerRightCorner(X=" + lowerRightCornerX + ",Y=" + lowerRightCornerY + ")");
      }

      for (int y = upperLeftCornerY - 1; y <= lowerRightCornerY - 1; y++) {
        for (int x = upperLeftCornerX - 1; x <= lowerRightCornerX - 1; x++) {
          int otherX = x + 1;
          int otherY = y + 1;

          if ((otherY == upperLeftCornerY && (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
              (otherY == lowerRightCornerY && (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
              (otherX == upperLeftCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY)) ||
              (otherX == lowerRightCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY))) {
            canvas.getPositions()[y][x].fill();
          }
        }
      }
    } else {
      throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
    }
    return canvas;
  }

  @Override
  public boolean accept(String line) {
    return line.startsWith("R");
  }
}