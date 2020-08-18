package dibujo.cli.commands;

import dibujo.cli.out.CanvasAsImage;
import dibujo.core.Canvas;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DumpCanvasToImageCommand implements Command {
    private static final String DUMP_TO_IMAGE = "I";

    @Override
    public boolean accept(String line) {
        return line.startsWith(DUMP_TO_IMAGE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        Canvas canvas = commandParameters.getCanvas();
        String line = commandParameters.getLine();

        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before dumping it.");
        }
        Matcher matcher = Pattern.compile("^" + DUMP_TO_IMAGE + " ([\\w.]+\\.(\\w+))$").matcher(line);
        if (matcher.find()) {
            String filename = matcher.group(1);
            String extension = matcher.group(2);
            CanvasAsImage canvasAsImage = new CanvasAsImage();
            canvasAsImage.draw(filename, extension, canvas);

        } else {
            throw new RuntimeException("Invalid parameters for the dump to image command. Should be: " + DUMP_TO_IMAGE + " <filename>.<png|jpg|jpeg|gif|bmp>");
        }

        return canvas;
    }
}
