package dibujo.cli.output;

import dibujo.core.Canvas;
import dibujo.core.Position;

public class CanvasAsText {

    public String draw(Canvas canvas) {
        StringBuilder text = new StringBuilder("\n");

        horizontalLine(text, canvas);

        for (Position position : canvas) {
            if (position.getX() == 1) {
                if (position.getY() > 1) {
                    text.append("|\n");
                }
                text.append("|");
            }

            if (position.getColor() != null) {
                text.append(position.getColor());
            } else if (position.isFilled()) {
                text.append("x");
            } else {
                text.append(" ");
            }
        }

        text.append("|\n");

        horizontalLine(text, canvas);

        return text.toString();
    }

    private void horizontalLine(StringBuilder text, Canvas canvas) {
        for (int i = 0; i <= canvas.getWidth(); i++) {
            text.append("-");
        }
        text.append("-");

        text.append("\n");
    }
}