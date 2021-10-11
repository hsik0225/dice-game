public class Turn {

    private static final int DEFAULT_START_TURN = 1;

    private final int totalTurn;
    private int currentTurn;

    public Turn(int totalTurn) {
        this(totalTurn, DEFAULT_START_TURN);
    }

    public Turn(int totalTurn, int currentTurn) {
        this.totalTurn = totalTurn;
        this.currentTurn = currentTurn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isLessOrEqualThanTotal() {
        return currentTurn <= totalTurn;
    }

    public void increase() {
        currentTurn++;
    }
}
