package dibujo.cli;

import java.io.PrintStream;

public class Quit {
    void quit(PrintStream out) {
        out.println("Bye bye!");
        System.exit(0);
    }
}