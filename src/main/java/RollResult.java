import java.util.List;

public class RollResult {

    private final List<Integer> results;
    private final int playerScore;

    public RollResult(List<Integer> results, int playerScore) {
        this.results = results;
        this.playerScore = playerScore;
    }

    public List<Integer> getResults() {
        return results;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int calculateRoundScore() {
        return results.stream()
                      .mapToInt(Integer::intValue)
                      .sum();
    }

    public boolean isSameNumber() {
        final Integer any = results.get(0);
        return results.stream().allMatch(result -> result.equals(any));
    }
}
