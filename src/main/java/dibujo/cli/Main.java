package dibujo.cli;

import dibujo.core.Canvas;
import dibujo.core.Position;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final Quit quit = new Quit();
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
                        createLine(line);
                    } else if (line.startsWith("R")) {
                        createRectangle(line);
                    } else if (line.startsWith("B")) {
                        bucketFill(line);
                    } else if (line.startsWith("Q")) {
                        quit.quit(out);
                    } else {
                        err.println("Invalid command: " + line + "\n");
                    }

                    canvas.draw(out, this);

                    out.print("\nenter command: ");

                } catch (Exception ex) {
                    err.println(ex.getMessage()+"\n");
                    out.print("\nenter command: ");
                }
            }
        }
    }

    private void bucketFill(String line) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before filling it.");
        }

        Matcher matcher = Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            String colorCharacter = matcher.group(3);

            Position startingPositionToFill = new Position(startingX, startingY);
            canvas.fill(startingPositionToFill, colorCharacter);
        } else {
            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
        }
    }

    private void createRectangle(String line) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }

        Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int upperLeftCornerX = Integer.parseInt(matcher.group(1));
            int upperLeftCornerY = Integer.parseInt(matcher.group(2));
            int lowerRightCornerX = Integer.parseInt(matcher.group(3));
            int lowerRightCornerY = Integer.parseInt(matcher.group(4));

            canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
        } else {
            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
        }
    }

    private void createLine(String line) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
        }
        Matcher matcher = Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            int endingX = Integer.parseInt(matcher.group(3));
            int endingY = Integer.parseInt(matcher.group(4));

            canvas.createNewLine(startingX, startingY, endingX, endingY);
        } else {
            throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
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

