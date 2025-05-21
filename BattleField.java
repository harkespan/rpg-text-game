public interface BattleField {
    void display();
    boolean movePlayer(int dx, int dy);
    boolean isGameOver();
}
