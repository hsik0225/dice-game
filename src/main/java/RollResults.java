import java.util.ArrayList;
import java.util.List;

public class RollResults {

    private final List<RollResult> rollResults;

    public RollResults() {
        this(new ArrayList<>());
    }

    public RollResults(List<RollResult> rollResults) {
        this.rollResults = rollResults;
    }

    public List<RollResult> getRollResults() {
        return rollResults;
    }

    public void add(RollResult result) {
        rollResults.add(result);
    }

    public int calculateRoundScore() {
        return rollResults.stream()
                          .mapToInt(RollResult::calculateRoundScore)
                          .sum();
    }
}
