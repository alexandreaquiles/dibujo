package dibujo;

import dibujo.support.DibujoTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BucketFillTests extends DibujoTests {

    @BeforeEach
    public void before() {
        run("C 4 3");
    }

    @Test
    public void whenThereAreNoBarriersEverythingShouldBePainted() {
        run("B 2 2 o");
        assertThat(result()).contains("------\n" +
                                      "|oooo|\n" +
                                      "|oooo|\n" +
                                      "|oooo|\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

    @Test
    public void shouldNotPaintFilledPositions() {
        run("L 1 3 2 3");
        run("L 4 2 4 3");
        run("B 2 2 o");
        assertThat(result()).contains("------\n" +
                                      "|oooo|\n" +
                                      "|ooox|\n" +
                                      "|xxox|\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

    @Test
    public void shouldNotPaintInsideRectangles() {
        run("R 1 1 3 3");
        run("B 4 1 o");
        assertThat(result()).contains("------\n" +
                                      "|xxxo|\n" +
                                      "|x xo|\n" +
                                      "|xxxo|\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

}
