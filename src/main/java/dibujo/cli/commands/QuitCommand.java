package dibujo.cli.commands;

import dibujo.core.Canvas;

public class QuitCommand implements Command {
    private static final String CODE = "Q";

    @Override
    public boolean accept(String line) {
        return line.startsWith(CODE);
    }

    @Override
    public Canvas execute(CommandParameters commandParameters) {
        commandParameters.getOut().println("Bye bye!");
        System.exit(0);
        return commandParameters.getCanvas();
    }
}