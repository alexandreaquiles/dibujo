package dibujo;

import dibujo.asserts.AssertDrawings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTests {

    private Canvas sixByFiveCanvas;

    @BeforeEach
    void setUp() {
        sixByFiveCanvas = new Canvas(6, 5);
    }

    @Test
    public void shouldCreateNewRectangleWithEmptySpacesInside() {
        sixByFiveCanvas.createNewRectangle(2, 2, 5, 4);

        Position[][] expectedPositions = new Position[][]{
                {new Position(1, 1), new Position(2, 1), new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6, 1)},
                {new Position(1, 2), new Position(2, 2).fill(), new Position(3, 2).fill(), new Position(4, 2).fill(), new Position(5, 2).fill(), new Position(6, 2)},
                {new Position(1, 3), new Position(2, 3).fill(), new Position(3, 3), new Position(4, 3), new Position(5, 3).fill(), new Position(6, 3)},
                {new Position(1, 4), new Position(2, 4).fill(), new Position(3, 4).fill(), new Position(4, 4).fill(), new Position(5, 4).fill(), new Position(6, 4)},
                {new Position(1, 5), new Position(2, 5), new Position(3, 5), new Position(4, 5), new Position(5, 5), new Position(6, 5)},
        };

        AssertDrawings.assertPositions(expectedPositions, sixByFiveCanvas);

    }

    @Test
    public void shouldCreateNewRectangleThatOcuppiesFirstTwoColumns() {
        sixByFiveCanvas.createNewRectangle(1, 1, 2, 5);

        Position[][] expectedPositions = new Position[][]{
                {new Position(1, 1).fill(), new Position(2, 1).fill(), new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6, 1)},
                {new Position(1, 2).fill(), new Position(2, 2).fill(), new Position(3, 2), new Position(4, 2), new Position(5, 2), new Position(6, 2)},
                {new Position(1, 3).fill(), new Position(2, 3).fill(), new Position(3, 3), new Position(4, 3), new Position(5, 3), new Position(6, 3)},
                {new Position(1, 4).fill(), new Position(2, 4).fill(), new Position(3, 4), new Position(4, 4), new Position(5, 4), new Position(6, 4)},
                {new Position(1, 5).fill(), new Position(2, 5).fill(), new Position(3, 5), new Position(4, 5), new Position(5, 5), new Position(6, 5)},
        };

        AssertDrawings.assertPositions(expectedPositions, sixByFiveCanvas);
    }

    @Test
    public void upperLeftCornerHorizontalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(0, 1, 2, 2);
        });
    }

    @Test
    public void upperLeftCornerVerticalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 0, 2, 2);
        });
    }

    @Test
    public void lowerRightCornerHorizontalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 1, 0, 2);
        });
    }

    @Test
    public void lowerRightCornerVerticalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 1, 2, 0);
        });
    }

    @Test
    public void upperLeftCornerHorizontalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(-2, 1, 2, 2);
        });
    }

    @Test
    public void upperLeftCornerVerticalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, -1, 2, 2);
        });
    }

    @Test
    public void lowerRightCornerHorizontalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 1, -2, 2);
        });
    }

    @Test
    public void lowerRightCornerVerticalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 1, 2, -2);
        });
    }

    @Test
    public void upperLeftCornerHorizontalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(999, 2, 5, 4);
        });
    }

    @Test
    public void upperLeftCornerVerticalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 999, 5, 4);
        });
    }

    @Test
    public void lowerRightCornerHorizontalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 2, 999, 4);
        });
    }

    @Test
    public void lowerRightCornerVerticalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            sixByFiveCanvas.createNewRectangle(2, 2, 5, 999);
        });
    }

}
