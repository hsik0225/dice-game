import java.util.stream.Collectors;

public class OutputView {

    public static void printScores(int totalTurn, Players players, ScoreBoards scoreBoards) {
        for (int currentTurn = 1; currentTurn <= totalTurn; currentTurn++) {
            for (Player player : players.getPlayers()) {
                System.out.println(player.getName() + "의 차례입니다.");
                System.out.printf("주사위 결과 : %s\n", joiningRollResult(scoreBoards.getRollResult(player, currentTurn)));
                System.out.printf("현재까지 얻은 점수 : %d\n\n", scoreBoards.getCurrentScore(player, currentTurn));
            }
        }

        System.out.println("총 점수");
        for (Player player : players.getPlayers()) {
            System.out.printf("%s : %d 점\n", player.getName(), player.getScore());
        }
    }

    private static String joiningRollResult(RollResult rollResult) {
        return rollResult.getResults()
                         .stream()
                         .map(String::valueOf)
                         .collect(Collectors.joining(", "));
    }
}
