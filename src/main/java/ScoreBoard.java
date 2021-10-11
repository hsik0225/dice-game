import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private final Map<Integer, RollResult> scores;

    public ScoreBoard() {
        this(new HashMap<>());
    }

    public ScoreBoard(Map<Integer, RollResult> scores) {
        this.scores = scores;
    }

    public void record(int turn, RollResult result) {
        this.scores.put(turn, result);
    }

    public int getCurrentScore(int currentTurn) {
        return scores.get(currentTurn).getScore();
    }

    public RollResult getRollResult(int currentTurn) {
        return scores.get(currentTurn);
    }
}
