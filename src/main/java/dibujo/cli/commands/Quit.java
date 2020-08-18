package dibujo.cli.commands;

import java.io.PrintStream;

public class Quit {
    public static final String QUIT = "Q";

    public void quit(PrintStream out) {
        out.println("Bye bye!");
        System.exit(0);
    }
}