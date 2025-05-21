public class Boss extends Enemy {
    public Boss(String name, int level) {
        super(name, level);
        this.maxHp = 200 + level * 50;
        this.hp = this.maxHp;
    }

    @Override
    public int attack() {
        // Boss deals more damage
        return (int)(Math.random() * (15 + level * 6)) + 10;
    }
}
