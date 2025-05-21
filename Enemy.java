public class Enemy extends Character {
    public Enemy(String name, int level) {
        super(name, level);
    }

    public int attack() {
        // Random damage based on level
        return (int)(Math.random() * (8 + level * 4)) + 3;
    }
}
