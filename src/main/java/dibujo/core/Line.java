package dibujo.core;

public class Line {
    private int startingX;
    private int startingY;
    private int endingX;
    private int endingY;

    public Line(int startingX, int startingY, int endingX, int endingY) {
        if (startingX <= 0 || startingY <= 0 || endingX <= 0 || endingY <= 0) {
            throw new RuntimeException("Invalid parameters: the starting and ending coordinates should be greater than zero. Given parameters: " + this);
        }

        if (startingX != endingX && startingY != endingY ) {
            throw new RuntimeException("Invalid parameters: currently only horizontal or vertical lines are supported. Given parameters: " + this);
        }

        this.startingX = startingX;
        this.startingY = startingY;
        this.endingX = endingX;
        this.endingY = endingY;
    }

    public void createIn(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (startingX > width ||  endingX > width || startingY > height || endingY > height) {
            throw new RuntimeException("Invalid parameters: the line coordinates should not be off limits. Given parameters: " + canvas + " starting(X=" + startingX + ", Y=" + startingY + ") ending(X="+endingX+ ", Y=" + endingY + ")");
        }

        for (int y = startingY-1; y <= endingY-1; y++) {
            for (int x = startingX-1; x <= endingX-1; x++) {
                canvas.fillPosition(x, y);
            }
        }
    }

}
