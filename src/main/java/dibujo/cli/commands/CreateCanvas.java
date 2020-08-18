package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateCanvas implements Command {
    private static final String CODE = "C";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        Matcher matcher = Pattern.compile("^"+ CODE +" (\\d+) (\\d+)$").matcher(commandParameters.getLine());
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group(1));
            int height = Integer.parseInt(matcher.group(2));

            return new Canvas(width, height);
        } else {
            throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: "+ CODE +" <width> <height>");
        }
    }
}