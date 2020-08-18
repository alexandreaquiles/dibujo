package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateLine implements Command {
    private static final String CREATE_LINE = "L";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CREATE_LINE);
    }

    @Override
    public void execute(String line, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
        }
        Matcher matcher = Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            int endingX = Integer.parseInt(matcher.group(3));
            int endingY = Integer.parseInt(matcher.group(4));

            canvas.createNewLine(startingX, startingY, endingX, endingY);
        } else {
            throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
        }
    }
}