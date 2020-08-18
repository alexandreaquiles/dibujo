package dibujo.cli.commands;

import java.util.List;

public class Commands {

   private static final List<Command> COMMANDS = List.of(
            new CreateCanvas(),
            new CreateLine(),
            new CreateRectangle(),
            new BucketFill(),
            new Quit());

    public static Command getCommandByLine(String line) {
        return COMMANDS.stream().filter(c -> c.accept(line))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + line));
    }
}
