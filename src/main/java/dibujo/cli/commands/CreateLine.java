package dibujo.cli.commands;

import dibujo.core.Canvas;
import dibujo.core.Line;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateLine implements Command {
    private static final String CODE = "L";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        Canvas canvas = commandParameters.getCanvas();
        String line = commandParameters.getLine();

        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
        }
        Matcher matcher = Pattern.compile("^"+ CODE +" (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            int endingX = Integer.parseInt(matcher.group(3));
            int endingY = Integer.parseInt(matcher.group(4));

            Line canvasLine = new Line(startingX, startingY, endingX, endingY);
            canvasLine.createIn(canvas);
        } else {
            throw new RuntimeException("Invalid parameters for the create new line command. Should be: "+ CODE +" <starting x> <starting y> <ending x> <ending y>");
        }
        return canvas;
    }
}