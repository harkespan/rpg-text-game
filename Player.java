public class Player extends Character {
    private int potions;

    public Player(String name) {
        super(name, 1);
        this.potions = 3;
    }

    public int attack() {
        // Random damage based on level
        return (int)(Math.random() * (10 + level * 5)) + 5;
    }

    public void usePotion() {
        if (potions > 0) {
            heal(30 + level * 10);
            potions--;
            System.out.println(name + " used a potion! HP: " + hp + "/" + maxHp);
        } else {
            System.out.println("No potions left!");
        }
    }

    public int getPotions() {
        return potions;
    }

    public void addPotion() {
        potions++;
    }
}
