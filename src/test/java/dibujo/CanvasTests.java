package dibujo;

import dibujo.support.DibujoTests;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CanvasTests extends DibujoTests {

    @Test
    void shouldCreateNewCanvasWithGivenWidthAndHeight() {
        run("C 20 4");
        assertThat(result()).contains("----------------------\n" +
                                      "|                    |\n" +
                                      "|                    |\n" +
                                      "|                    |\n" +
                                      "|                    |\n" +
                                      "----------------------\n");
        assertThat(errors()).isEmpty();
    }

    @Test
    void dimensionsAreRequired() {
        run("C");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void heightIsRequired() {
        run("C 20");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void widthShouldNotBeZero() {
        run("C 0 4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void heightShouldNotBeZero() {
        run("C 20 0");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void widthShouldNotBeNegative() {
        run("C -20 4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
     }

    @Test
    void heightShouldNotBeNegative() {
        run("C 20 -4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
     }

    @Test
    void widthShouldBeNumeric() {
        run("C w 4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void heightShouldBeNumeric() {
        run("C 20 w");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void shouldNotHaveAnythingAfterTheHeight() {
        run("C 20 4 hello");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

}
