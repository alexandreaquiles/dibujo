package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  private final CanvasToText canvasToText = new CanvasToText();

  private Canvas canvas;

  public void run(InputStream in, PrintStream out, PrintStream err) {
    try (Scanner scanner = new Scanner(in)) {
      out.print("\nenter command: ");
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        try {

         Command command = Command.COMMANDS.stream()
              .filter(c -> c.accept(line))
              .findAny()
                  .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + line + "\n"));

         this.canvas = command.execute(out, line, canvas);

          canvasToText.draw(out, canvas);

          out.print("\nenter command: ");

        } catch (Exception ex) {
          err.println(ex.getMessage() + "\n");
          out.print("\nenter command: ");
        }
      }
    }
  }

  public static void main(String[] args) {
    new Main().run(System.in, System.out, System.err);
  }
}

