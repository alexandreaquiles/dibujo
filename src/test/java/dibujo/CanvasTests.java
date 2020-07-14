package dibujo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CanvasTests {

    @Test
    public void shouldCreateNewCanvasWithGivenWidthAndHeight() {
        int width = 20;
        int height = 4;
        Canvas canvas = new Canvas(width, height);

        assertEquals(20, canvas.getWidth());
        assertEquals(4, canvas.getHeight());
    }

    @Test
    void canvasWidthShoulNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            int width = -20;
            int height = 4;
            new Canvas(width, height);
        });
    }

    @Test
    void canvasWidthShoulNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            int width = 0;
            int height = 4;
            new Canvas(width, height);
        });
    }

    @Test
    void canvasHeightShoulNotBeNegative() {
        assertThrows(RuntimeException.class, () -> {
            int width = 20;
            int height = -4;
            new Canvas(width, height);
        });
    }

    @Test
    void canvasHeightShoulNotBeZero() {
        assertThrows(RuntimeException.class, () -> {
            int width = 20;
            int height = 0;
            new Canvas(width, height);
        });
    }

}
