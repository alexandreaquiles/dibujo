package dibujo.cli;

import dibujo.core.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateRectangle {
    public CreateRectangle() {
    }

    void createRectangle(String line, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }

        Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int upperLeftCornerX = Integer.parseInt(matcher.group(1));
            int upperLeftCornerY = Integer.parseInt(matcher.group(2));
            int lowerRightCornerX = Integer.parseInt(matcher.group(3));
            int lowerRightCornerY = Integer.parseInt(matcher.group(4));

            canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
        } else {
            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
        }
    }
}