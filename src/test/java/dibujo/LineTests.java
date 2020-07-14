package dibujo;

import dibujo.asserts.AssertDrawings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LineTests {

    private Canvas fourByThreeCanvas;

    @BeforeEach
    void setUp() {
        fourByThreeCanvas = new Canvas(4, 3);
    }

    @Test
    void shouldCreateNewHorizontalLine() {
        fourByThreeCanvas.createNewLine(1, 2, 3, 2);

        Position[][] expectedPositions = new Position[][]{
                {new Position(1, 1), new Position(2, 1), new Position(3, 1), new Position(4, 1)},
                {new Position(1, 2).fill(), new Position(2, 2).fill(), new Position(3, 2).fill(), new Position(4, 2)},
                {new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(4, 3)},
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

    @Test
    void shouldCreateNewVerticalLine() {
        fourByThreeCanvas.createNewLine(2, 1, 2, 2);

        Position[][] expectedPositions = new Position[][]{
                {new Position(1, 1), new Position(2, 1).fill(), new Position(3, 1), new Position(4, 1)},
                {new Position(1, 2), new Position(2, 2).fill(), new Position(3, 2), new Position(4, 2)},
                {new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(4, 3)},
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

    @Test
    void shouldCreateNewHorizontallLineInTheLastColumn() {
        fourByThreeCanvas.createNewLine(4, 2, 4, 3);

        Position[][] expectedPositions = new Position[][]{
                {new Position(1, 1), new Position(2, 1), new Position(3, 1), new Position(4, 1)},
                {new Position(1, 2), new Position(2, 2), new Position(3, 2), new Position(4, 2).fill()},
                {new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(4, 3).fill()},
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

    @Test
    void shouldCreateNewVerticalLineInTheLastRow() {
        fourByThreeCanvas.createNewLine(1, 3, 2, 3);

        Position[][] expectedPositions = new Position[][]{
                {new Position(1, 1), new Position(2, 1), new Position(3, 1), new Position(4, 1)},
                {new Position(1, 2), new Position(2, 2), new Position(3, 2), new Position(4, 2)},
                {new Position(1, 3).fill(), new Position(2, 3).fill(), new Position(3, 3), new Position(4, 3)},
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

    @Test
    void startingHorizontalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(999, 1, 2, 2);
        });
    }

    @Test
    void startingVerticalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 999, 2, 2);
        });
    }

    @Test
    void endingHorizontalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {

            fourByThreeCanvas.createNewLine(2, 1, 999, 2);
        });
    }

    @Test
    void endingVerticalCoordinateShouldNotBeOffLimits() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 1, 2, 999);
        });
    }

    @Test
    void startingHorizontalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(0, 1, 2, 2);
        });
    }

    @Test
    void startingVerticalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 0, 2, 2);
        });
    }

    @Test
    void endingHorizontalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 1, 0, 2);
        });
    }

    @Test
    void endingVerticalCoordinateShouldNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 1, 2, 0);
        });
    }

    @Test
    void startingHorizontalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(-2, 1, 2, 2);
        });
    }

    @Test
    void startingVerticalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, -1, 2, 2);
        });
    }

    @Test
    void endingHorizontalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 1, -2, 2);
        });
    }

    @Test
    void endingVerticalCoordinateShouldNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(2, 1, 2, -2);
        });
    }

    @Test
    void diagonalLinesCurrentlyNotSupported() {
        assertThrows(RuntimeException.class, () -> {
            fourByThreeCanvas.createNewLine(1, 1, 3, 3);
        });
    }
}
