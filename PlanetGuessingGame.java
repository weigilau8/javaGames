import java.util.Random;
import java.util.Scanner;

public class PlanetGuessingGame {

    private static final String[][] PLANETS = {
        {"Mercury", "The smallest and innermost planet in the Solar System."},
        {"Venus", "The second planet from the Sun, known for its thick, toxic atmosphere."},
        {"Earth", "The third planet from the Sun and the only astronomical object known to harbor life."},
        {"Mars", "The fourth planet from the Sun, often called the 'Red Planet'."},
        {"Jupiter", "The largest planet in the Solar System, known for its Great Red Spot."},
        {"Saturn", "The sixth planet from the Sun, famous for its extensive ring system."},
        {"Uranus", "The seventh planet from the Sun, known for its unique tilt and blue color."},
        {"Neptune", "The eighth planet from the Sun, known for its strong winds and blue color."}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {
            int selectedPlanetIndex = random.nextInt(PLANETS.length);
            String selectedPlanet = PLANETS[selectedPlanetIndex][0];
            String planetDescription = PLANETS[selectedPlanetIndex][1];
            String hiddenPlanet = selectedPlanet.replaceAll("[a-zA-Z]", "_");
            int attemptsLeft = 5;
            boolean hasWon = false;

            System.out.println("Welcome to the Planet Guessing Game!");
            System.out.println("Try to guess the name of the planet based on its description. You have " + attemptsLeft + " attempts.");
            System.out.println("Description: " + planetDescription);
            System.out.println("Planet: " + hiddenPlanet);

            while (attemptsLeft > 0 && !hasWon) {
                System.out.print("Enter your guess (single letter or full planet name): ");
                String guess = scanner.nextLine().trim();

                if (guess.equalsIgnoreCase(selectedPlanet)) {
                    hasWon = true;
                } else if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                    char guessedLetter = guess.charAt(0);
                    boolean isCorrectGuess = false;

                    StringBuilder newHiddenPlanet = new StringBuilder(hiddenPlanet);
                    for (int i = 0; i < selectedPlanet.length(); i++) {
                        if (Character.toLowerCase(selectedPlanet.charAt(i)) == Character.toLowerCase(guessedLetter)) {
                            newHiddenPlanet.setCharAt(i, selectedPlanet.charAt(i));
                            isCorrectGuess = true;
                        }
                    }

                    if (isCorrectGuess) {
                        hiddenPlanet = newHiddenPlanet.toString();
                        System.out.println("Good guess! Planet: " + hiddenPlanet);
                    } else {
                        attemptsLeft--;
                        System.out.println("Incorrect guess. Attempts left: " + attemptsLeft);
                    }

                    if (!hiddenPlanet.contains("_")) {
                        hasWon = true;
                    }
                } else {
                    System.out.println("Invalid guess. Please enter a single letter or the full planet name.");
                }
            }

            if (hasWon) {
                System.out.println("Congratulations! You've guessed the planet: " + selectedPlanet);
            } else {
                System.out.println("Game Over! The planet was: " + selectedPlanet);
            }

            System.out.print("Do you want to play again? (yes or no): ");
            playAgain = scanner.nextLine().trim();
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
