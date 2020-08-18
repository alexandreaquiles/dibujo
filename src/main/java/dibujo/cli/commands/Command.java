package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.io.PrintStream;

public interface Command {
    boolean accept(String line);

    Canvas execute(PrintStream out, PrintStream err, String line, Canvas canvas);
}
