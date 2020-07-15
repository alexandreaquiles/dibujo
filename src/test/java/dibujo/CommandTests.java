package dibujo;

import dibujo.support.DibujoTests;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandTests extends DibujoTests {

    @Test
    public void whenThereAreNoBarriersEverythingShouldBePainted() {
        run("X");
        assertThat(errors()).contains("Invalid command");
    }

}
