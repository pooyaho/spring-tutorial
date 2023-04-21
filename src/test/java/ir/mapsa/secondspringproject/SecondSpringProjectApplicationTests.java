package ir.mapsa.secondspringproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecondSpringProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test()
    public void testDivideReturnsCorrectAnswer() {

        Assertions.assertThat(divide(2, 4)).isEqualTo(0.5f);
        Assertions.assertThat(divide(100, 4)).isEqualTo(25f);

    }

    @Test
    public void testDivideThrowsCorrectException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            divide(100, 0);
        });
    }

    public float divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }
        return (float) a / b;
    }

}
