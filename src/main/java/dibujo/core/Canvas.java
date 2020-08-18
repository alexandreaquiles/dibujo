package dibujo.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Canvas implements Iterable<Position> {

    private int width;
    private int height;

    private Position[][] positions;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Invalid parameters: the width and height of the canvas should be greater than zero. Given parameters: " + this);
        }

        this.positions = new Position[this.height][this.width];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.positions[y][x] = new Position(x+1, y+1);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Canvas [width=" + width + ", height=" + height + "]";
    }

    public Position getPosition(int x, int y) {
        return positions[y-1][x-1];
    }

    public void fillPosition(int x, int y) {
        getPosition(x+1, y+1).fill();
    }

    public void fill(Position positionToFill, String fillColor) {
        fill(positionToFill, fillColor, new HashSet<>());
    }

    private void fill(Position positionToFill, String fillColor, Set<Position> visited) {
        if (visited.contains(positionToFill) || positionToFill.getX() <= 0 || positionToFill.getX() > this.width || positionToFill.getY() <= 0 || positionToFill.getY() > this.height) {
            return;
        }

        Position canvasPosition = positions[positionToFill.getY()-1][positionToFill.getX()-1];
        if (canvasPosition.isFilled()) {
            return;
        }
        canvasPosition.changeColor(fillColor);
        visited.add(positionToFill);

        fill(positionToFill.leftUp(), fillColor, visited);
        fill(positionToFill.centerUp(), fillColor, visited);
        fill(positionToFill.rightUp(), fillColor, visited);

        fill(positionToFill.right(), fillColor, visited);
        fill(positionToFill.rightDown(), fillColor, visited);

        fill(positionToFill.centerDown(), fillColor, visited);

        fill(positionToFill.leftDown(), fillColor, visited);
        fill(positionToFill.left(), fillColor, visited);
    }

    @Override
    public Iterator<Position> iterator() {
        return new Iterator<>() {

            private int x = 1;
            private int y = 1;

            @Override
            public boolean hasNext() {
                return x >= 1 && x <= width && y >= 1 && y <= height;
            }

            @Override
            public Position next() {
                Position position = getPosition(x, y);
                x++;
                if (x >= width + 1) {
                    x = 1;
                    y++;
                }
                return position;
            }
        };
    }

}
