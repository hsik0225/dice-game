public class DiceGame {

    public static final int DIE_COUNT = 2;

    private final Players players;
    private final Turn turn;
    private final ScoreBoards scoreBoards;

    public DiceGame(Players players, Turn turn, ScoreBoards scoreBoards) {
        this.players = players;
        this.turn = turn;
        this.scoreBoards = scoreBoards;
    }

    public static DiceGame of(Players players, int totalTurn) {
        return new DiceGame(players, new Turn(totalTurn), ScoreBoards.from(players));
    }

    public ScoreBoards run() {
        while (turn.isLessOrEqualThanTotal()) {
            final Player currentPlayer = players.findCurrentPlayer();
            final RollResult rollResult = currentPlayer.roll(DIE_COUNT);
            final int currentTurn = turn.getCurrentTurn();
            scoreBoards.record(currentPlayer, currentTurn, rollResult);

            players.moveCursorToNextPlayer();
            if (players.allRolled()) {
                turn.increase();
            }
        }

        return scoreBoards;
    }
}
