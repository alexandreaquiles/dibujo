package dibujo.cli.commands;

import dibujo.core.Canvas;
import dibujo.core.Position;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BucketFill implements Command {
    private static final String CODE = "B";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(PrintStream out, PrintStream err, String line, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before filling it.");
        }

        Matcher matcher = Pattern.compile("^"+ CODE +" (\\d+) (\\d+) (\\w+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            String colorCharacter = matcher.group(3);

            Position startingPositionToFill = new Position(startingX, startingY);
            canvas.fill(startingPositionToFill, colorCharacter);
        } else {
            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: "+ CODE +" <starting x> <starting y> <color>");
        }
        return canvas;
    }
}