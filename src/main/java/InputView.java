import com.woowahan.techcourse.utils.Console;

public class InputView {

    public static String[] askPlayerNames() {
        System.out.println("플레이어 이름을 입력해주세요.");
        return readLineAndBreakLine().split(",");
    }

    public static Integer askTotalTurn() {
        System.out.println("턴을 입력해주세요.");
        return Integer.parseInt(readLineAndBreakLine());
    }

    public static String readLineAndBreakLine() {
        final String input = Console.readLine();
        System.out.println();
        return input;
    }
}
