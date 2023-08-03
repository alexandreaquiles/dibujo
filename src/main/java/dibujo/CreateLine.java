package dibujo;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateLine implements Command {
  public CreateLine() {
  }

  @Override
  public Canvas execute(PrintStream out, String line, Canvas canvas) {
    if (canvas.getPositions() == null) {
      throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
    }
    Matcher matcher = Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
    if (matcher.find()) {
      int startingX = Integer.parseInt(matcher.group(1));
      int startingY = Integer.parseInt(matcher.group(2));
      int endingX = Integer.parseInt(matcher.group(3));
      int endingY = Integer.parseInt(matcher.group(4));

      if (startingX <= 0 || startingY <= 0 || endingX <= 0 || endingY <= 0) {
        throw new RuntimeException("Invalid parameters: the starting and ending coordinates should be greater than zero. Given parameters: " + null);
      }

      if (startingX != endingX && startingY != endingY) {
        throw new RuntimeException("Invalid parameters: currently only horizontal or vertical lines are supported. Given parameters: " + null);
      }

      if (startingX > canvas.getWidth() || endingX > canvas.getWidth() || startingY > canvas.getHeight() || endingY > canvas.getHeight()) {
        throw new RuntimeException("Invalid parameters: the line coordinates should not be off limits. Given parameters: " + null + " starting(X=" + startingX + ", Y=" + startingY + ") ending(X=" + endingX + ", Y=" + endingY + ")");
      }

      for (int y = startingY - 1; y <= endingY - 1; y++) {
        for (int x = startingX - 1; x <= endingX - 1; x++) {
          canvas.getPositions()[y][x].fill();
        }
      }
    } else {
      throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
    }
    return canvas;
  }

  @Override
  public boolean accept(String line) {
    return line.startsWith("L");
  }
}