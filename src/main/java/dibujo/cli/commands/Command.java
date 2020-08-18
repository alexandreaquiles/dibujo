package dibujo.cli.commands;

import dibujo.core.Canvas;

public interface Command {
    boolean accept(String line);

    void execute(String line, Canvas canvas);
}
