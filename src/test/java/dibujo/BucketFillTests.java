package dibujo;

import dibujo.asserts.AssertDrawings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BucketFillTests {

    private Canvas fourByThreeCanvas;

    @BeforeEach
    public void before() {
        fourByThreeCanvas = new Canvas(4, 3);
    }

    @Test
    public void whenThereAreNoBarriersEverythingShouldBePainted() {
        fourByThreeCanvas.fill(new Position(2,2), "o");

        Position[][] expectedPositions = new Position[][] {
                { new Position(1,1,"o"), new Position(2,1,"o"), new Position(3,1,"o"), new Position(4,1,"o") },
                { new Position(1,2,"o"), new Position(2,2,"o"), new Position(3,2,"o"), new Position(4,2,"o") },
                { new Position(1,3,"o"), new Position(2,3,"o"), new Position(3,3,"o"), new Position(4,3,"o") }
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

    @Test
    public void shouldNotPaintFilledPositions() {
        fourByThreeCanvas.createNewLine(1, 3, 2, 3);
        fourByThreeCanvas.createNewLine(4, 2, 4, 3);

        fourByThreeCanvas.fill(new Position(2,2), "o");

        Position[][] expectedPositions = new Position[][] {
                { new Position(1,1,"o") , new Position(2,1,"o") , new Position(3,1,"o"), new Position(4,1,"o")  },
                { new Position(1,2,"o") , new Position(2,2,"o") , new Position(3,2,"o"), new Position(4,2).fill() },
                { new Position(1,3).fill(), new Position(2,3).fill(), new Position(3,3,"o"), new Position(4,3).fill() }
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

    @Test
    public void shouldNotPaintInsideRectangles() {
        fourByThreeCanvas.createNewRectangle(1, 1, 3, 3);

        fourByThreeCanvas.fill(new Position(4,1), "o");

        Position[][] expectedPositions = new Position[][] {
                { new Position(1,1).fill(), new Position(2,1).fill(), new Position(3,1).fill(), new Position(4,1,"o") },
                { new Position(1,2).fill(), new Position(2,2)       , new Position(3,2).fill(), new Position(4,2,"o") },
                { new Position(1,3).fill(), new Position(2,3).fill(), new Position(3,3).fill(), new Position(4,3,"o") }
        };

        AssertDrawings.assertPositions(expectedPositions, fourByThreeCanvas);
    }

}
