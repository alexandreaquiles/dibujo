package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.io.PrintStream;

public class Quit {
    private static final String QUIT = "Q";

    public boolean accept(String line) {
        return line.startsWith(QUIT);
    }

    public void execute(PrintStream out, PrintStream err, String line, Canvas canvas) {
        out.println("Bye bye!");
        System.exit(0);
    }
}