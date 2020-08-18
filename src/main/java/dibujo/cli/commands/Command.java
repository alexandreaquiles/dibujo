package dibujo.cli.commands;

import dibujo.core.Canvas;

import java.io.PrintStream;
import java.util.List;

public interface Command {

    List<Command> commands = List.of(
            new CreateCanvas(),
            new CreateLine(),
            new CreateRectangle(),
            new BucketFill(),
            new Quit());

    static Command getCommandByLine(String line) {
        return commands.stream().filter(c -> c.accept(line))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + line));
    }

    boolean accept(String line);

    Canvas execute(PrintStream out, PrintStream err, String line, Canvas canvas);

}
