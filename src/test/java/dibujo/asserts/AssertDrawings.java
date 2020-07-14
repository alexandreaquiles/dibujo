package dibujo.asserts;

import dibujo.Canvas;
import dibujo.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertDrawings {

    public static void assertPositions(Position[][] expectedPositions, Canvas canvas) {
        for (int i = 0; i < expectedPositions.length; i++) {
            for (int j = 0; j < expectedPositions[i].length; j++) {

                int x = j + 1;
                int y = i + 1;
                Position position = canvas.getPosition(x, y);

                assertNotNull(position);
                assertEquals(expectedPositions[i][j], position, "x=" + x + ",y=" + y);
            }
        }
    }

}