package dibujo.support;

import dibujo.cli.Main;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class DibujoTests {

    private ByteArrayOutputStream errOS;
    private ByteArrayOutputStream outOS;

    private PrintStream out;
    private PrintStream err;
    private Main main;

    @BeforeEach
    void dibujoSetUp() {
        main = new Main();

        outOS = new ByteArrayOutputStream();
        out = new PrintStream(outOS);

        errOS = new ByteArrayOutputStream();
        err = new PrintStream(errOS);
    }

    protected void run(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        main.run(in, out, err);
    }

    protected String result() {
        return new String(outOS.toByteArray());
    }

    protected String errors() {
        return new String(errOS.toByteArray());
    }

}
