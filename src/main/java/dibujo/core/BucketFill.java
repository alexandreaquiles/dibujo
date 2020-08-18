package dibujo.core;

import java.util.HashSet;
import java.util.Set;

public class BucketFill {
    private int startingX;
    private int startingY;
    private String colorCharacter;

    public BucketFill(int startingX, int startingY, String colorCharacter) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.colorCharacter = colorCharacter;
    }

    public void fillIn(Canvas canvas) {
        Position positionToFill = new Position(startingX, startingY);
        fill(canvas, positionToFill, colorCharacter, new HashSet<>());
    }

    void fill(Canvas canvas, Position positionToFill, String fillColor, Set<Position> visited) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (visited.contains(positionToFill) || positionToFill.getX() <= 0 || positionToFill.getX() > width || positionToFill.getY() <= 0 || positionToFill.getY() > height) {
            return;
        }

        Position canvasPosition = canvas.getPosition(positionToFill.getX(), positionToFill.getY());
        if (canvasPosition.isFilled()) {
            return;
        }
        canvasPosition.changeColor(fillColor);
        visited.add(positionToFill);

        fill(canvas, positionToFill.leftUp(), fillColor, visited);
        fill(canvas, positionToFill.centerUp(), fillColor, visited);
        fill(canvas, positionToFill.rightUp(), fillColor, visited);

        fill(canvas, positionToFill.right(), fillColor, visited);
        fill(canvas, positionToFill.rightDown(), fillColor, visited);

        fill(canvas, positionToFill.centerDown(), fillColor, visited);

        fill(canvas, positionToFill.leftDown(), fillColor, visited);
        fill(canvas, positionToFill.left(), fillColor, visited);
    }
}
