package dibujo;

public class Position {

    private int x;
    private int y;
    private boolean filled = false;
    private String color;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, String color) {
        this(x, y);
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (filled != other.filled)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + (filled ? 1231 : 1237);
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + ", filled=" + filled + ", color=" + color + "]";
    }

    public Position fill() {
        filled = true;
        return this;
    }

    public boolean isFilled() {
        return filled;
    }

    public String getColor() {
        return color;
    }

    public void changeColor(String newColor) {
        this.color = newColor;
    }

    public Position leftUp() {
        return new Position(x-1, y-1);
    }

    public Position centerUp() {
        return new Position(x, y-1);
    }

    public Position rightUp() {
        return new Position(x+1, y-1);
    }

    public Position right() {
        return new Position(x+1, y);
    }

    public Position left() {
        return new Position(x-1, y);
    }

    public Position rightDown() {
        return new Position(x+1, y+1);
    }

    public Position centerDown() {
        return new Position(x, y+1);
    }

    public Position leftDown() {
        return new Position(x-1, y+1);
    }

}

