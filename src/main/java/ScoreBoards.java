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

    public void record(Player player, int currentTurn, RollResults rollResults) {
        this.scoreBoards.get(player).record(currentTurn, rollResults);
    }

    public RollResults getRollResults(Player player, int currentTurn) {
        return this.scoreBoards.get(player).getRollResults(currentTurn);
    }

    public int getRoundScore(int currentTurn, Player player) {
        return this.scoreBoards.get(player).calculateRoundScore(currentTurn);
    }
}
