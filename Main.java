import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text RPG!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Player player = new Player(name);

        // Create 10 random enemies
        Enemy[] enemies = new Enemy[10];
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int lvl = rand.nextInt(3) + 1;
            enemies[i] = new Enemy("Enemy" + (i+1), lvl);
        }
        // Create boss
        Boss boss = new Boss("Boss", 5);

        // Create map
        GameMap map = new GameMap(player, enemies, boss);

        while (!map.isGameOver()) {
            map.display();
            System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp() + " | Level: " + player.getLevel() + " | EXP: " + player.getExp() + " | Potions: " + player.getPotions());
            System.out.print("Move (WASD), P=Potion, Q=Quit: ");
            String cmd = scanner.nextLine().trim().toUpperCase();
            if (cmd.equals("Q")) break;
            if (cmd.equals("P")) {
                player.usePotion();
                continue;
            }
            int dx = 0, dy = 0;
            switch (cmd) {
                case "W": dy = -1; break;
                case "S": dy = 1; break;
                case "A": dx = -1; break;
                case "D": dx = 1; break;
                default:
                    System.out.println("Invalid command.");
                    continue;
            }
            map.movePlayer(dx, dy);
        }
        System.out.println("Game ended. Thanks for playing!");
    }
}
