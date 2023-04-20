package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private int canvasWidth;
  private int canvasHeight;
  private Position[][] canvasPositions;

  public void run(InputStream in, PrintStream out, PrintStream err) {
    try (Scanner scanner = new Scanner(in)) {
      out.print("\nenter command: ");
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        try {

          if (line.startsWith("C")) {
            Matcher matcher = Pattern.compile("^C (\\d+) (\\d+)$").matcher(line);
            if (matcher.find()) {
              int width = Integer.parseInt(matcher.group(1));
              int height = Integer.parseInt(matcher.group(2));

              this.canvasWidth = width;
              this.canvasHeight = height;

              if (width <= 0 || height <= 0) {
                throw new RuntimeException("Invalid parameters: the width and height of the canvas should be greater than zero. Given parameters: " + this);
              }

              this.canvasPositions = new Position[this.canvasHeight][this.canvasWidth];

              for (int y = 0; y < this.canvasHeight; y++) {
                for (int x = 0; x < this.canvasWidth; x++) {
                  this.canvasPositions[y][x] = new Position(x + 1, y + 1);
                }
              }
            } else {
              throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
            }
          } else if (line.startsWith("L")) {
            if (canvasPositions == null) {
              throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
            }
            Matcher matcher = Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
            if (matcher.find()) {
              int startingX = Integer.parseInt(matcher.group(1));
              int startingY = Integer.parseInt(matcher.group(2));
              int endingX = Integer.parseInt(matcher.group(3));
              int endingY = Integer.parseInt(matcher.group(4));

              if (startingX <= 0 || startingY <= 0 || endingX <= 0 || endingY <= 0) {
                throw new RuntimeException("Invalid parameters: the starting and ending coordinates should be greater than zero. Given parameters: " + this);
              }

              if (startingX != endingX && startingY != endingY) {
                throw new RuntimeException("Invalid parameters: currently only horizontal or vertical lines are supported. Given parameters: " + this);
              }

              if (startingX > canvasWidth || endingX > canvasWidth || startingY > canvasHeight || endingY > canvasHeight) {
                throw new RuntimeException("Invalid parameters: the line coordinates should not be off limits. Given parameters: " + this + " starting(X=" + startingX + ", Y=" + startingY + ") ending(X=" + endingX + ", Y=" + endingY + ")");
              }

              for (int y = startingY - 1; y <= endingY - 1; y++) {
                for (int x = startingX - 1; x <= endingX - 1; x++) {
                  this.canvasPositions[y][x].fill();
                }
              }
            } else {
              throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
            }

          } else if (line.startsWith("R")) {
            if (canvasPositions == null) {
              throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
            }

            Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
            if (matcher.find()) {
              int upperLeftCornerX = Integer.parseInt(matcher.group(1));
              int upperLeftCornerY = Integer.parseInt(matcher.group(2));
              int lowerRightCornerX = Integer.parseInt(matcher.group(3));
              int lowerRightCornerY = Integer.parseInt(matcher.group(4));

              if (upperLeftCornerX <= 0 || upperLeftCornerY <= 0 || lowerRightCornerX <= 0 || lowerRightCornerY <= 0) {
                throw new RuntimeException("Invalid parameters: the upper left corner and lower right corner coordinates should be greater than zero. Given parameters: " + this);
              }

              if (upperLeftCornerX > canvasWidth || lowerRightCornerX > canvasWidth || upperLeftCornerY > canvasHeight || lowerRightCornerY > canvasHeight) {
                throw new RuntimeException("Invalid parameters: the rectangle coordinates should not be off limits. Given parameters: " + this + " upperLeftCorner(X=" + upperLeftCornerX + ", Y=" + upperLeftCornerY + " lowerRightCorner(X=" + lowerRightCornerX + ",Y=" + lowerRightCornerY + ")");
              }

              for (int y = upperLeftCornerY - 1; y <= lowerRightCornerY - 1; y++) {
                for (int x = upperLeftCornerX - 1; x <= lowerRightCornerX - 1; x++) {
                  int otherX = x + 1;
                  int otherY = y + 1;

                  if ((otherY == upperLeftCornerY && (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
                      (otherY == lowerRightCornerY && (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
                      (otherX == upperLeftCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY)) ||
                      (otherX == lowerRightCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY))) {
                    this.canvasPositions[y][x].fill();
                  }
                }
              }
            } else {
              throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
            }

          } else if (line.startsWith("B")) {
            if (canvasPositions == null) {
              throw new RuntimeException("No canvas. You should create a canvas before filling it.");
            }

            Matcher matcher = Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line);
            if (matcher.find()) {
              int startingX = Integer.parseInt(matcher.group(1));
              int startingY = Integer.parseInt(matcher.group(2));
              String colorCharacter = matcher.group(3);

              Position startingPositionToFill = new Position(startingX, startingY);
              fill(this.canvasWidth, this.canvasHeight, this.canvasPositions, startingPositionToFill, colorCharacter);
            } else {
              throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
            }

          } else if (line.startsWith("Q")) {
            out.println("Bye bye!");
            System.exit(0);
          } else {
            err.println("Invalid command: " + line + "\n");
          }

          out.println();

          for (int i = 0; i <= canvasWidth; i++) {
            out.print("-");
          }
          out.print("-");

          out.println();

          int x = 1, y = 1;
          while (x >= 1 && x <= canvasWidth && y >= 1 && y <= canvasHeight) {
            Position position = canvasPositions[y - 1][x - 1];
            if (position.getX() == 1) {
              if (position.getY() > 1) {
                out.println("|");
              }
              out.print("|");
            }

            if (position.getColor() != null) {
              out.print(position.getColor());
            } else if (position.isFilled()) {
              out.print("x");
            } else {
              out.print(" ");
            }
            x++;
            if (x >= canvasWidth + 1) {
              x = 1;
              y++;
            }
          }

          out.println("|");
          out.print("-");

          for (int i = 0; i < canvasWidth; i++) {
            out.print("-");
          }
          out.print("-");

          out.println();

          out.print("\nenter command: ");

        } catch (Exception ex) {
          err.println(ex.getMessage() + "\n");
          out.print("\nenter command: ");
        }
      }
    }
  }

  private static void fill(int canvasWidth, int canvasHeight, Position[][] canvasPositions, Position startingPositionToFill, String colorCharacter) {
    fill(canvasWidth, canvasHeight, canvasPositions, startingPositionToFill, colorCharacter, new HashSet<>());
  }

  private static void fill(int canvasWidth, int canvasHeight, Position[][] canvasPositions, Position positionToFill, String fillColor, Set<Position> visited) {
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

  public static void main(String[] args) {
    new Main().run(System.in, System.out, System.err);
  }
}

