import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int DEFAULT_START_CURSOR = 0;

    private final List<Player> players;
    private int cursor;

    public Players(List<Player> players, int cursor) {
        this.players = players;
        this.cursor = cursor;
    }

    public static Players from(String[] playerNames) {
        return new Players(mapToPlayer(playerNames), DEFAULT_START_CURSOR);
    }

    private static List<Player> mapToPlayer(String[] playerNames) {
        return Arrays.stream(playerNames)
                     .map(Player::from)
                     .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player findCurrentPlayer() {
        return players.get(cursor);
    }

    public void moveCursorToNextPlayer() {
        cursor++;
        cursor = cursor % players.size();
    }

    public boolean allRolled() {
        return cursor == DEFAULT_START_CURSOR;
    }
}
