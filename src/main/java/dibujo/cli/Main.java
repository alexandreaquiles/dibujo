package dibujo.cli;

import dibujo.cli.commands.*;
import dibujo.cli.out.CanvasAsText;
import dibujo.core.Canvas;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    private final CanvasAsText canvasAsText = new CanvasAsText();

    private final Command quit = new Quit();
    private final Command createLine = new CreateLine();
    private final Command createRectangle = new CreateRectangle();
    private final Command bucketFill = new BucketFill();
    private final Command createCanvas = new CreateCanvas();

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {

                    if (createCanvas.accept(line)) {

                        canvas = createCanvas.execute(out, err, line, this.canvas);

                    } else if (createLine.accept(line)) {

                        createLine.execute(out, err, line, this.canvas);

                    } else if (createRectangle.accept(line)) {

                        createRectangle.execute(out, err, line, this.canvas);

                    } else if (bucketFill.accept(line)) {

                        bucketFill.execute(out, err, line, this.canvas);

                    } else if (quit.accept(line)) {

                        quit.execute(out, err, line, this.canvas);

                    } else {
                        err.println("Invalid command: " + line + "\n");
                    }

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

