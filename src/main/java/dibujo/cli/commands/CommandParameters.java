package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.io.PrintStream;

public class CommandParameters {
    private final PrintStream out;
    private final PrintStream err;
    private final String line;
    private final Canvas canvas;

    public CommandParameters(PrintStream out, PrintStream err, String line, Canvas canvas) {
        this.out = out;
        this.err = err;
        this.line = line;
        this.canvas = canvas;
    }

    public PrintStream getOut() {
        return out;
    }

    public PrintStream getErr() {
        return err;
    }

    public String getLine() {
        return line;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
