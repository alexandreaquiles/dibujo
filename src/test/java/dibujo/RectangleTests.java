package dibujo;

import dibujo.support.DibujoTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RectangleTests extends DibujoTests {

    @BeforeEach
    void setUp() {
        run("C 6 5");
    }

    @Test
    public void shouldCreateNewRectangleWithEmptySpacesInside() {
        run("R 2 2 5 4");
        assertThat(result()).contains("--------\n" +
                                      "|      |\n" +
                                      "| xxxx |\n" +
                                      "| x  x |\n" +
                                      "| xxxx |\n" +
                                      "|      |\n" +
                                      "--------");
        assertThat(errors()).isEmpty();
    }

    @Test
    public void shouldCreateNewRectangleThatOcuppiesFirstTwoColumns() {
        run("R 1 1 2 5");
        assertThat(result()).contains("--------\n" +
                                      "|xx    |\n" +
                                      "|xx    |\n" +
                                      "|xx    |\n" +
                                      "|xx    |\n" +
                                      "|xx    |\n" +
                                      "--------");
        assertThat(errors()).isEmpty();
    }

    @Test
    public void upperLeftCornerHorizontalCoordinateShouldNotBeZero() {
        run("R 0 1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void upperLeftCornerVerticalCoordinateShouldNotBeZero() {
        run("R 2 0 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void lowerRightCornerHorizontalCoordinateShouldNotBeZero() {
        run("R 2 1 0 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void lowerRightCornerVerticalCoordinateShouldNotBeZero() {
        run("R 2 1 2 0");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void upperLeftCornerHorizontalCoordinateShouldNotBeNegative() {
        run("R -2 1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void upperLeftCornerVerticalCoordinateShouldNotBeNegative() {
        run("R 2 -1 2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void lowerRightCornerHorizontalCoordinateShouldNotBeNegative() {
        run("R 2 1 -2 2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void lowerRightCornerVerticalCoordinateShouldNotBeNegative() {
        run("R 2 1 2 -2");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void upperLeftCornerHorizontalCoordinateShouldNotBeOffLimits() {
        run("R 999 2 5 4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void upperLeftCornerVerticalCoordinateShouldNotBeOffLimits() {
        run("R 2 999 5 4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void lowerRightCornerHorizontalCoordinateShouldNotBeOffLimits() {
        run("R 2 2 999 4");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

    @Test
    public void lowerRightCornerVerticalCoordinateShouldNotBeOffLimits() {
        run("R 2 2 5 999");
        assertThat(errors()).containsIgnoringCase("Invalid parameters");
    }

}
