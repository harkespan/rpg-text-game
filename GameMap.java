import java.util.Random;
import java.util.Scanner;

public class GameMap implements BattleField {
    private final int width = 5;
    private final int height = 5;
    private Object[][] map = new Object[height][width];
    private Player player;
    private int playerX, playerY;
    private boolean gameOver = false;
    private Scanner scanner;

    public GameMap(Player player, Enemy[] enemies, Boss boss) {
        this.player = player;
        this.scanner = new Scanner(System.in);
        // Place player at (0,0)
        playerX = 0;
        playerY = 0;
        map[playerY][playerX] = player;
        // Place enemies randomly
        Random rand = new Random();
        for (Enemy e : enemies) {
            int x, y;
            do {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            } while (map[y][x] != null || (x == 0 && y == 0));
            map[y][x] = e;
        }
        // Place boss at bottom-right
        map[height-1][width-1] = boss;
    }

    public void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[y][x] instanceof Player) System.out.print("P ");
                else if (map[y][x] instanceof Boss) System.out.print("B ");
                else if (map[y][x] instanceof Enemy) System.out.print("E ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    public boolean movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            System.out.println("Can't move there!");
            return false;
        }
        Object obj = map[newY][newX];
        if (obj instanceof Enemy) {
            System.out.println("You encountered an enemy!");
            battle((Enemy)obj);
            if (!player.isAlive()) {
                gameOver = true;
                return false;
            }
            map[newY][newX] = null;
        } else if (obj instanceof Boss) {
            System.out.println("You encountered the Boss!");
            battle((Boss)obj);
            if (!player.isAlive()) {
                gameOver = true;
                return false;
            }
            map[newY][newX] = null;
            System.out.println("You defeated the Boss! You win!");
            gameOver = true;
        }
        map[playerY][playerX] = null;
        playerX = newX;
        playerY = newY;
        map[playerY][playerX] = player;
        return true;
    }

    private void battle(Enemy enemy) {
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("Choose action: [A]ttack, [D]efend, [P]otion");
            String action = scanner.nextLine().trim().toUpperCase();
            switch (action) {
                case "A": {
                    int dmg = player.attack();
                    enemy.takeDamage(dmg);
                    System.out.println("You hit " + enemy.getName() + " for " + dmg + " damage. (" + enemy.getHp() + "/" + enemy.getMaxHp() + ")");
                    break;
                }
                case "D": {
                    int edmg = Math.max(0, enemy.attack() / 2);
                    player.takeDamage(edmg);
                    System.out.println("You defend! " + enemy.getName() + " hits you for " + edmg + " damage. (" + player.getHp() + "/" + player.getMaxHp() + ")");
                    continue;
                }
                case "P": {
                    player.usePotion();
                    continue;
                }
                default:
                    System.out.println("Invalid action.");
                    continue;
            }
            if (!enemy.isAlive()) break;
            int edmg = enemy.attack();
            player.takeDamage(edmg);
            System.out.println(enemy.getName() + " hits you for " + edmg + " damage. (" + player.getHp() + "/" + player.getMaxHp() + ")");
        }
        if (player.isAlive()) {
            System.out.println("You defeated " + enemy.getName() + "!");
            player.addExp(50 + enemy.getLevel() * 20);
            if (Math.random() < 0.3) {
                player.addPotion();
                System.out.println("You found a potion!");
            }
        } else {
            System.out.println("You were defeated by " + enemy.getName() + ". Game Over.");
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
