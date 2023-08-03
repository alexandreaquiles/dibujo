package dibujo;

import java.io.PrintStream;
import java.util.List;

public interface Command {

  List<Command> COMMANDS = List.of(
      new CreateCanvas(),
      new CreateLine(),
      new CreateRectangle(),
      new BucketFill(),
      new Quit()
  );


  Canvas execute(PrintStream out, String line, Canvas canvas);
  boolean accept(String line);
}
