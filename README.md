# Java Text-Based RPG Game

This is a simple text-based RPG game implemented in Java. The game features:
- Character class hierarchy: Player, Enemy (with Boss subclass)
- Attributes: name, hp, level, exp (with max HP based on level)
- 2D battlefield interface (tiles)
- Random enemy placement, boss at bottom-right
- Player can use potions to heal
- 10 enemies, exp gained after defeating enemies
- Random damage based on level

## How to Run
1. Compile all `.java` files:
   ```powershell
   javac *.java
   ```
2. Run the game:
   ```powershell
   java Main
   ```

## Project Structure
- `Character.java`: Base class for all characters
- `Player.java`: Player class
- `Enemy.java`: Enemy class
- `Boss.java`: Boss class
- `BattleField.java`: Battlefield interface
- `GameMap.java`: 2D implementation of battlefield
- `Main.java`: Game entry point

---

Enjoy the adventure!
