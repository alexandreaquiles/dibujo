package dibujo.cli.commands;

import dibujo.cli.output.CanvasAsText;
import dibujo.core.Canvas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DumpCanvasToFileCommand implements Command {

    public static final String DUMP_TO_FILE = "D";

    @Override
    public boolean accept(String line) {
        return line.startsWith(DUMP_TO_FILE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        Canvas canvas = commandParameters.getCanvas();
        String line = commandParameters.getLine();

        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before dumping it.");
        }
        Matcher matcher = Pattern.compile("^"+ DUMP_TO_FILE + " ([\\w.]+)$").matcher(line);
        if (matcher.find()) {
            String filename = matcher.group(1);
            CanvasAsText canvasAsText = new CanvasAsText();
            try(FileOutputStream fos = new FileOutputStream(filename);
                PrintStream out = new PrintStream(fos)) {
                String text = canvasAsText.draw(canvas);
                out.print(text);
            } catch (IOException e) {
                throw new RuntimeException("Error dumping to file...", e);
            }

        } else {
            throw new RuntimeException("Invalid parameters for the dump to file command. Should be: " + DUMP_TO_FILE +" <filename>");
        }

        return canvas;
    }
}
