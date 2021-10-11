import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.woowahan.techcourse.utils.Randoms;

public class Player {

    private static final int DEFAULT_SCORE = 0;

    private final String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static Player from(String name) {
        return new Player(name, DEFAULT_SCORE);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public RollResults rollDiceWhileSameNumber(int dieCount) {
        RollResults rollResults = new RollResults();
        while (true) {
            final RollResult result = roll(dieCount);
            rollResults.add(result);

            if (result.isSameNumber()) {
                break;
            }
        }
        return rollResults;
    }

    public RollResult roll(int dieCount) {
        final List<Integer> results = rollDiceCountTimes(dieCount);
        score += calculateScore(results);
        return new RollResult(results, score);
    }

    private List<Integer> rollDiceCountTimes(int dieCount) {
        return IntStream.rangeClosed(1, dieCount)
                        .boxed()
                        .map(i -> Randoms.pickNumberInRange(1, 6))
                        .collect(Collectors.toList());
    }

    private int calculateScore(List<Integer> results) {
        return results.stream()
                      .mapToInt(Integer::intValue)
                      .sum();
    }
}
