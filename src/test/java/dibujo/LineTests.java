package dibujo;

import dibujo.support.DibujoTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LineTests extends DibujoTests {

    @BeforeEach
    void setUp() {
        run("C 4 3");
    }

    @Test
    void shouldCreateNewHorizontalLine() {
        run("L 1 2 3 2");
        assertThat(result()).contains("------\n" +
                                      "|    |\n" +
                                      "|xxx |\n" +
                                      "|    |\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

    @Test
    void shouldCreateNewVerticalLine() {
        run("L 2 1 2 2");
        assertThat(result()).contains("------\n" +
                                      "| x  |\n" +
                                      "| x  |\n" +
                                      "|    |\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

    @Test
    void shouldCreateNewHorizontalLineInTheLastColumn() {
        run("L 4 2 4 3");
        assertThat(result()).contains("------\n" +
                                      "|    |\n" +
                                      "|   x|\n" +
                                      "|   x|\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

    @Test
    void shouldCreateNewVerticalLineInTheLastRow() {
        run("L 1 3 2 3");
        assertThat(result()).contains("------\n" +
                                      "|    |\n" +
                                      "|    |\n" +
                                      "|xx  |\n" +
                                      "------");
        assertThat(errors()).isEmpty();
    }

    @Test
    void startingHorizontalCoordinateShouldNotBeOffLimits() {
        run("L 999 1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void startingVerticalCoordinateShouldNotBeOffLimits() {
        run("L 2 999 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void endingHorizontalCoordinateShouldNotBeOffLimits() {
        run("L 2 1 999 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void endingVerticalCoordinateShouldNotBeOffLimits() {
        run("L 2 1 2 999");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void startingHorizontalCoordinateShouldNotBeZero() {
        run("L 0 1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void startingVerticalCoordinateShouldNotBeZero() {
        run("L 2 0 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void endingHorizontalCoordinateShouldNotBeZero() {
        run("L 2 1 0 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void endingVerticalCoordinateShouldNotBeZero() {
        run("L 2 1 2 0");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void startingHorizontalCoordinateShouldNotBeNegative() {
        run("L -2 1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void startingVerticalCoordinateShouldNotBeNegative() {
        run("L 2 -1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void endingHorizontalCoordinateShouldNotBeNegative() {
        run("L 2 1 -2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void endingVerticalCoordinateShouldNotBeNegative() {
        run("L 2 1 2 -2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    void diagonalLinesCurrentlyNotSupported() {
        run("L 1 1 3 3");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }
}
