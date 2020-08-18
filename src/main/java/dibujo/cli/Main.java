package dibujo.cli;

import dibujo.cli.out.CanvasAsText;
import dibujo.core.Canvas;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final CanvasAsText canvasAsText = new CanvasAsText();

    private final Quit quit = new Quit();
    private final CreateLine createLine = new CreateLine();
    private final CreateRectangle createRectangle = new CreateRectangle();
    private final BucketFill bucketFill = new BucketFill();

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {

                    if (line.startsWith("C")) {
                        createCanvas(line);
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

    private void createCanvas(String line) {
        Matcher matcher = Pattern.compile("^C (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group(1));
            int height = Integer.parseInt(matcher.group(2));

            canvas = new Canvas(width, height);
        } else {
            throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
        }
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

