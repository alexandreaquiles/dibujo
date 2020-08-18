package dibujo.cli;

import dibujo.cli.out.CanvasAsText;
import dibujo.core.Canvas;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    private final CanvasAsText canvasAsText = new CanvasAsText();

    private final Quit quit = new Quit();
    private final CreateLine createLine = new CreateLine();
    private final CreateRectangle createRectangle = new CreateRectangle();
    private final BucketFill bucketFill = new BucketFill();
    private final CreateCanvas createCanvas = new CreateCanvas();

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {

                    if (line.startsWith("C")) {
                        canvas = createCanvas.createCanvas(line);
                    } else if (line.startsWith("L")) {
                        createLine.createLine(line, this.canvas);
                    } else if (line.startsWith("R")) {
                        createRectangle.createRectangle(line, this.canvas);
                    } else if (line.startsWith("B")) {
                        bucketFill.bucketFill(line, this.canvas);
                    } else if (line.startsWith("Q")) {
                        quit.quit(out);
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

    private Canvas createCanvas(String line) {
        return createCanvas.createCanvas(line);
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

