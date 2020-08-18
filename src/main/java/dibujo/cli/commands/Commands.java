package dibujo.cli.commands;

import java.util.List;

public class Commands {

   private static final List<Command> COMMANDS = List.of(
            new CreateCanvasCommand(),
            new CreateLineCommand(),
            new CreateRectangleCommand(),
            new BucketFillCommand(),
            new DumpCanvasToFileCommand(),
            new DumpCanvasToImageCommand(),
            new QuitCommand());

    public static Command getCommandByLine(String line) {
        return COMMANDS.stream().filter(c -> c.accept(line))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + line));
    }
}
