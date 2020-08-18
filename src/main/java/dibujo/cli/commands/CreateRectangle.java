package dibujo.cli.commands;

import dibujo.core.Canvas;
import dibujo.core.Rectangle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateRectangle implements Command {
    private static final String CODE = "R";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        Canvas canvas = commandParameters.getCanvas();
        String line = commandParameters.getLine();

        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }

        Matcher matcher = Pattern.compile("^"+ CODE +" (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int upperLeftCornerX = Integer.parseInt(matcher.group(1));
            int upperLeftCornerY = Integer.parseInt(matcher.group(2));
            int lowerRightCornerX = Integer.parseInt(matcher.group(3));
            int lowerRightCornerY = Integer.parseInt(matcher.group(4));

            Rectangle rectangle = new Rectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
            rectangle.createIn(canvas);
        } else {
            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: "+ CODE +" <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
        }
        return canvas;
    }
}