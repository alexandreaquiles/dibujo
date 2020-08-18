package dibujo.cli.commands;

import java.io.PrintStream;

public class Quit {
    private static final String QUIT = "Q";

    public boolean accept(String line) {
        return line.startsWith(QUIT);
    }

    public void quit(PrintStream out) {
        out.println("Bye bye!");
        System.exit(0);
    }
}