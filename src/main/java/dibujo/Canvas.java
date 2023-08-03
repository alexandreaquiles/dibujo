package dibujo;

public class Canvas {

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
        this.positions[y][x] = new Position(x + 1, y + 1);
      }
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Position[][] getPositions() {
    return positions;
  }
}
