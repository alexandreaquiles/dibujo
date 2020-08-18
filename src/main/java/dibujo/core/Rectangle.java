package dibujo.core;

public class Rectangle {
    private int upperLeftCornerX;
    private int upperLeftCornerY;
    private int lowerRightCornerX;
    private int lowerRightCornerY;

    public Rectangle(int upperLeftCornerX, int upperLeftCornerY, int lowerRightCornerX, int lowerRightCornerY) {
        if (upperLeftCornerX <= 0 || upperLeftCornerY <= 0 || lowerRightCornerX <= 0 || lowerRightCornerY <= 0) {
            throw new RuntimeException("Invalid parameters: the upper left corner and lower right corner coordinates should be greater than zero. Given parameters: " + this);
        }

        this.upperLeftCornerX = upperLeftCornerX;
        this.upperLeftCornerY = upperLeftCornerY;
        this.lowerRightCornerX = lowerRightCornerX;
        this.lowerRightCornerY = lowerRightCornerY;
    }

    public void createIn(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (upperLeftCornerX > width || lowerRightCornerX > width || upperLeftCornerY > height || lowerRightCornerY > height) {
            throw new RuntimeException("Invalid parameters: the rectangle coordinates should not be off limits. Given parameters: " + canvas + " upperLeftCorner(X=" + upperLeftCornerX + ", Y=" + upperLeftCornerY + " lowerRightCorner(X=" + lowerRightCornerX + ",Y=" + lowerRightCornerY + ")");
        }

        for (int y = upperLeftCornerY-1; y <= lowerRightCornerY-1; y++) {
            for (int x = upperLeftCornerX-1; x <= lowerRightCornerX-1; x++) {
                int otherX = x+1;
                int otherY = y+1;

                if ((otherY == upperLeftCornerY&& (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
                            (otherY == lowerRightCornerY && (otherX >= upperLeftCornerX && otherX <= lowerRightCornerX)) ||
                            (otherX == upperLeftCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY)) ||
                            (otherX == lowerRightCornerX && (otherY >= upperLeftCornerY && otherY <= lowerRightCornerY))) {
                    canvas.fillPosition(x, y);
                }
            }
        }


    }
}