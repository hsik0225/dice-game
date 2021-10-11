import java.util.stream.Collectors;

public class OutputView {

    public static void printScores(int totalTurn, Players players, ScoreBoards scoreBoards) {
        for (int currentTurn = 1; currentTurn <= totalTurn; currentTurn++) {
            for (Player player : players.getPlayers()) {
                System.out.println(player.getName() + "의 차례입니다.");
                for (RollResult rollResult : scoreBoards.getRollResults(player, currentTurn).getRollResults()) {
                    System.out.printf("주사위 결과 : %s\n", joiningRollResult(rollResult));
                    System.out.printf("현재까지 얻은 점수 : %d\n\n", rollResult.getPlayerScore());
                }
                System.out.println("---");
                System.out.println();
            }
        }
    }

    private static String joiningRollResult(RollResult rollResult) {
        return rollResult.getResults()
                         .stream()
                         .map(String::valueOf)
                         .collect(Collectors.joining(", "));
    }

    public static void printRoundResult(int totalTurn, Players players, ScoreBoards scoreBoards) {
        System.out.println("결과");
        for (int currentTurn = 1; currentTurn <= totalTurn; currentTurn++) {
            System.out.println(currentTurn + "라운드");
            for (Player player : players.getPlayers()) {
                System.out.printf("%s : %d\n", player.getName(), scoreBoards.getRoundScore(currentTurn, player));
            }
            System.out.println();
        }
    }

    public static void printTotalScore(Players players) {
        System.out.println("총 점수");
        for (Player player : players.getPlayers()) {
            System.out.printf("%s : %d\n", player.getName(), player.getScore());
        }
    }
}
