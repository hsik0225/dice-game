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
                console.when(Console::readLine).thenReturn("포츈,조이", "2");

                randoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt())).thenReturn(

                        // 포츈 - 1 Turn
                        1, 2,
                        4, 5,
                        6, 6,

                        // 조이 - 1 Turn
                        2, 3,
                        2, 2,

                        // 포츈 - 1 Turn
                        1, 1,

                        // 조이 - 1 Turn
                        4, 4
                );

                DiceGameController.main(new String[]{});
            }
        };

        // then
        assertSimpleTest(executable);
        assertThat(getOutput()).contains(
                "플레이어 이름을 입력해주세요.\n" +
                        "\n" +
                        "턴을 입력해주세요.\n" +
                        "\n" +
                        "포츈의 차례입니다.\n" +
                        "주사위 결과 : 1, 2\n" +
                        "현재까지 얻은 점수 : 3\n" +
                        "\n" +
                        "주사위 결과 : 4, 5\n" +
                        "현재까지 얻은 점수 : 12\n" +
                        "\n" +
                        "주사위 결과 : 6, 6\n" +
                        "현재까지 얻은 점수 : 24\n" +
                        "\n" +
                        "---\n" +
                        "\n" +
                        "조이의 차례입니다.\n" +
                        "주사위 결과 : 2, 3\n" +
                        "현재까지 얻은 점수 : 5\n" +
                        "\n" +
                        "주사위 결과 : 2, 2\n" +
                        "현재까지 얻은 점수 : 9\n" +
                        "\n" +
                        "---\n" +
                        "\n" +
                        "포츈의 차례입니다.\n" +
                        "주사위 결과 : 1, 1\n" +
                        "현재까지 얻은 점수 : 26\n" +
                        "\n" +
                        "---\n" +
                        "\n" +
                        "조이의 차례입니다.\n" +
                        "주사위 결과 : 4, 4\n" +
                        "현재까지 얻은 점수 : 17\n" +
                        "\n" +
                        "---\n" +
                        "\n" +
                        "결과\n" +
                        "1라운드\n" +
                        "포츈 : 24\n" +
                        "조이 : 9\n" +
                        "\n" +
                        "2라운드\n" +
                        "포츈 : 2\n" +
                        "조이 : 8\n" +
                        "\n" +
                        "총 점수\n" +
                        "포츈 : 26\n" +
                        "조이 : 17"
        );
    }

    private String getOutput() {
        return captor.toString().trim();
    }
}
