package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.io.PrintStream;

public class Quit implements Command {
    private static final String CODE = "Q";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(PrintStream out, PrintStream err, String line, Canvas canvas) {
        out.println("Bye bye!");
        System.exit(0);
        return canvas;
    }
}