public class DiceGameController {
    public static void main(String[] args) {
        final Players players = Players.from(InputView.askPlayerNames());
        final int totalTurn = InputView.askTotalTurn();
        final DiceGame diceGame = DiceGame.of(players, totalTurn);

        final ScoreBoards scoreBoards = diceGame.run();

        OutputView.printScores(totalTurn, players, scoreBoards);
        OutputView.printRoundResult(totalTurn, players, scoreBoards);
        OutputView.printTotalScore(players);
    }
}
