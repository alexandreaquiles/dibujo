package dibujo.cli.commands;

import java.io.PrintStream;

public class Quit {
    public void quit(PrintStream out) {
        out.println("Bye bye!");
        System.exit(0);
    }
}