import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        // Create a Random object
        Random rand = new Random();
        // Generate a random number between 1 and 100
        int randomNumber = rand.nextInt(100) + 1;
        
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        int guess = 0;
        
        // Loop until the user guesses the correct number
        while (guess != randomNumber) {
            System.out.print("Enter your guess (1-100): ");
            guess = scanner.nextInt();
            
            if (guess < randomNumber) {
                System.out.println("Too low!");
            } else if (guess > randomNumber) {
                System.out.println("Too high!");
            } else {
                System.out.println("Correct! The number was " + randomNumber);
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}
