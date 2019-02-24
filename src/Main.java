import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
		int maxEnemyHealth = 75;
		int enemyAttackDamage = 12;
		int playerHealth = 100;
		int attackDamage = 50;
		int potionCount = 2;
		int healthPotionHealAmount = 45;
		int healthPotionDropChance = 20; //percent
		
		boolean running = true;
		
		System.out.println("Welcome to the fight");
		
		GAME:
		while(running) {
			boolean fled = false;
			System.out.println("~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+");
			int enemyHealth = rand.nextInt(maxEnemyHealth);
			String enemy = enemies[rand.nextInt(enemies.length)];
			System.out.println("\t#A " + enemy + " has appeared!\n");
			
			while (!fled && enemyHealth > 0) {
				System.out.println("\tYou have " + playerHealth + " HP");
				System.out.println("\tThe " + enemy + " has " + enemyHealth + " HP");
				System.out.println("\tWhat will you do?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Use potion (" + potionCount +" remaining)");
				System.out.println("\t3. Run away");
				
				String input = in.nextLine();
				if (input.equals("1")) {
					int damageDealt = rand.nextInt(attackDamage);
					int damageTaken = rand.nextInt(enemyAttackDamage) + 15;
					enemyHealth -= damageDealt;
					playerHealth -= damageTaken;
					if (playerHealth < 1) {
						System.out.println("You have fallen.");
						running = false;
						break;
					}
					if (enemyHealth < 1) {
						System.out.println("You have defeated the " + enemy + "!\n");
					}
				}
				else if (input.equals("2")) {
					if (potionCount > 0) {
						playerHealth += healthPotionHealAmount;
						potionCount -= 1;
						System.out.println("You have been healed for " + healthPotionHealAmount + " HP");
					} else {
						System.out.println("You have none left!");
					}
				}
				else if (input.equals("3")) {
					System.out.println("You have fled!");
					fled = true;
				}
				else {
					System.out.println("Please enter a number 1 through 4.");
				}
				Main.sleep();
			}
			
			if (running && !fled && rand.nextInt(100) + 1 > healthPotionDropChance) {
				System.out.println("You have discovered a health potion on the corpse.\n");
				
				Main.sleep();
				
				potionCount += 1;
			}
		}
		
		System.out.println("GAME OVER");
		
	}
	
	public static void sleep() {
		try {
			Thread.sleep(2000);
		} catch(Exception e) {
			System.out.println("Some kind of error occurred.");
		}
	}
}
