public abstract class Character {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int level;
    protected int exp;

    public Character(String name, int level) {
        this.name = name;
        this.level = level;
        this.exp = 0;
        this.maxHp = calculateMaxHp();
        this.hp = this.maxHp;
    }

    protected int calculateMaxHp() {
        return 50 + level * 20;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public void addExp(int amount) {
        exp += amount;
        while (exp >= expToLevelUp()) {
            exp -= expToLevelUp();
            level++;
            maxHp = calculateMaxHp();
            hp = maxHp;
        }
    }
    protected int expToLevelUp() {
        return 100 + (level - 1) * 50;
    }
}
