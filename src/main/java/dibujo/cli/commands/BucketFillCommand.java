package dibujo.cli.commands;

import dibujo.core.BucketFill;
import dibujo.core.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BucketFillCommand implements Command {
    private static final String CODE = "B";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        Canvas canvas = commandParameters.getCanvas();
        String line = commandParameters.getLine();

        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before filling it.");
        }

        Matcher matcher = Pattern.compile("^"+ CODE +" (\\d+) (\\d+) (\\w+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            String colorCharacter = matcher.group(3);

            BucketFill bucketFill = new BucketFill(startingX, startingY, colorCharacter);
            bucketFill.fillIn(canvas);
        } else {
            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: "+ CODE +" <starting x> <starting y> <color>");
        }
        return canvas;
    }
}