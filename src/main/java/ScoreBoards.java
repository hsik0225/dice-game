import java.util.LinkedHashMap;
import java.util.Map;

public class ScoreBoards {

    private final Map<Player, ScoreBoard> scoreBoards;

    public ScoreBoards(Map<Player, ScoreBoard> scoreBoards) {
        this.scoreBoards = scoreBoards;
    }

    public static ScoreBoards from(Players players) {
        final Map<Player, ScoreBoard> scoreBoards = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            scoreBoards.put(player, new ScoreBoard());
        }
        return new ScoreBoards(scoreBoards);
    }

    public void record(Player player, int currentTurn, RollResult rollResult) {
        this.scoreBoards.get(player).record(currentTurn, rollResult);
    }

    public int getCurrentScore(Player player, int currentTurn) {
        return scoreBoards.get(player).getCurrentScore(currentTurn);
    }

    public RollResult getRollResult(Player player, int currentTurn) {
        return scoreBoards.get(player).getRollResult(currentTurn);
    }
}
