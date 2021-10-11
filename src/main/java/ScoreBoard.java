import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private final Map<Integer, RollResults> scores;

    public ScoreBoard() {
        this(new HashMap<>());
    }

    public ScoreBoard(Map<Integer, RollResults> scores) {
        this.scores = scores;
    }

    public void record(int turn, RollResults results) {
        this.scores.put(turn, results);
    }

    public RollResults getRollResults(int turn) {
        return scores.get(turn);
    }

    public int calculateRoundScore(int turn) {
        return getRollResults(turn).calculateRoundScore();
    }
}
