package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateCanvas implements Command {
    private static final String CODE = "C";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(PrintStream out, PrintStream err, String line, Canvas canvas) {
        Matcher matcher = Pattern.compile("^"+ CODE +" (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group(1));
            int height = Integer.parseInt(matcher.group(2));

            return new Canvas(width, height);
        } else {
            throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: "+ CODE +" <width> <height>");
        }
    }
}