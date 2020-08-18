package dibujo.cli;

import dibujo.cli.commands.Command;
import dibujo.cli.out.CanvasAsText;
import dibujo.core.Canvas;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    private final CanvasAsText canvasAsText = new CanvasAsText();

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {

                    Command command = Command.getCommandByLine(line);

                    canvas = command.execute(out, err, line, this.canvas);

                    canvasAsText.draw(out, canvas);

                    out.print("\nenter command: ");

                } catch (Exception ex) {
                    err.println(ex.getMessage()+"\n");
                    out.print("\nenter command: ");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

