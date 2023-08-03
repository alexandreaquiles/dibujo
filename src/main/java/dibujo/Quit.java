package dibujo;

import java.io.PrintStream;

public class Quit implements Command {
  public Quit() {
  }

  public Canvas execute(PrintStream out, String line, Canvas canvas) {
    out.println("Bye bye!");
    System.exit(0);
    return canvas;
  }

  @Override
  public boolean accept(String line) {
    return line.startsWith("Q");
  }
}