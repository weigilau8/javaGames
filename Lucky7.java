import java.util.Random;
import java.util.Scanner;

public class Lucky7 {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {
            System.out.println("Rolling the dice...");

            int die1 = random.nextInt(6) + 1;
            int die2 = random.nextInt(6) + 1;
            int sum = die1 + die2;

            System.out.println("You rolled a " + die1 + " and a " + die2);
            System.out.println("The sum is " + sum);

            if (sum == 7) {
                System.out.println("Congratulations, you win!");
            } else {
                System.out.println("Sorry, you lose.");
            }

            System.out.print("Do you want to play again? (yes or no): ");
            playAgain = scanner.nextLine();
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
