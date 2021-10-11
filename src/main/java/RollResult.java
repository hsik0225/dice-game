import java.util.List;

public class RollResult {

    private final List<Integer> results;
    private final int score;

    public RollResult(List<Integer> results, int score) {
        this.results = results;
        this.score = score;
    }

    public List<Integer> getResults() {
        return results;
    }

    public int getScore() {
        return score;
    }
}
