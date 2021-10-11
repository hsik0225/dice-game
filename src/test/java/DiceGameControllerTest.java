import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;

import com.woowahan.techcourse.utils.Console;
import com.woowahan.techcourse.utils.Randoms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

class DiceGameControllerTest {

    private static final Duration SIMPLE_TEST_TIMEOUT = Duration.ofSeconds(10000L);

    private OutputStream captor;

    @BeforeEach
    void setUp() {
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    private void assertSimpleTest(final Executable executable) {
        Assertions.assertTimeoutPreemptively(SIMPLE_TEST_TIMEOUT, executable);
    }

    @Test
    void acceptanceTest() {

        // when
        final Executable executable = () -> {
            try (MockedStatic<Randoms> randoms = mockStatic(Randoms.class);
                 MockedStatic<Console> console = mockStatic(Console.class)) {
                console.when(Console::readLine).thenReturn("포츈,조이,시드", "2");

                randoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt())).thenReturn(

                        // 1 Turn
                        1, 2,
                        2, 3,
                        6, 6,

                        // 2 Turn
                        1, 1,
                        5, 6,
                        1, 2
                );

                DiceGameController.main(new String[]{});
            }
        };

        // then
        assertSimpleTest(executable);
        assertThat(getOutput()).isEqualTo(
                "플레이어 이름을 입력해주세요.\n" +
                        "\n" +
                        "턴을 입력해주세요.\n" +
                        "\n" +
                        "포츈의 차례입니다.\n" +
                        "주사위 결과 : 1, 2\n" +
                        "현재까지 얻은 점수 : 3\n" +
                        "\n" +
                        "조이의 차례입니다.\n" +
                        "주사위 결과 : 2, 3\n" +
                        "현재까지 얻은 점수 : 5\n" +
                        "\n" +
                        "시드의 차례입니다.\n" +
                        "주사위 결과 : 6, 6\n" +
                        "현재까지 얻은 점수 : 0\n" +
                        "\n" +
                        "포츈의 차례입니다.\n" +
                        "주사위 결과 : 1, 1\n" +
                        "현재까지 얻은 점수 : 3\n" +
                        "\n" +
                        "조이의 차례입니다.\n" +
                        "주사위 결과 : 5, 6\n" +
                        "현재까지 얻은 점수 : 16\n" +
                        "\n" +
                        "시드의 차례입니다.\n" +
                        "주사위 결과 : 1, 2\n" +
                        "현재까지 얻은 점수 : 3\n" +
                        "\n" +
                        "총 점수\n" +
                        "포츈 : 3 점\n" +
                        "조이 : 16 점\n" +
                        "시드 : 3 점"
        );
    }

    private String getOutput() {
        return captor.toString().trim();
    }
}
