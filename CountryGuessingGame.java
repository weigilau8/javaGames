import java.util.Random;
import java.util.Scanner;

public class CountryGuessingGame {

    private static final String[][] COUNTRIES = {
        {"Argentina", "A country in South America known for its tango dance and music."},
        {"Australia", "A country and continent surrounded by the Indian and Pacific oceans."},
        {"Brazil", "The largest country in South America, famous for its Carnival festival."},
        {"Canada", "A North American country known for its vast landscapes and natural beauty."},
        {"China", "The most populous country in the world, known for its ancient history and culture."},
        {"France", "A European country famous for its wine, cuisine, and the Eiffel Tower."},
        {"Germany", "A European country known for its history, beer, and the Berlin Wall."},
        {"India", "A South Asian country known for its diverse culture, languages, and the Taj Mahal."},
        {"Italy", "A European country famous for its art, history, and the Colosseum."},
        {"Japan", "An East Asian country known for its technology, cuisine, and the cherry blossom season."},
        {"Mexico", "A North American country known for its rich cultural heritage and cuisine."},
        {"Russia", "The largest country in the world, spanning Eastern Europe and northern Asia."},
        {"South Africa", "A country at the southern tip of Africa known for its wildlife and Nelson Mandela."},
        {"Spain", "A European country known for its flamenco dance, bullfighting, and the Sagrada Familia."},
        {"United Kingdom", "A country in Europe made up of England, Scotland, Wales, and Northern Ireland."},
        {"United States", "A North American country known for its cultural influence and landmarks like the Statue of Liberty."}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {
            int selectedCountryIndex = random.nextInt(COUNTRIES.length);
            String selectedCountry = COUNTRIES[selectedCountryIndex][0];
            String countryDescription = COUNTRIES[selectedCountryIndex][1];
            String hiddenCountry = selectedCountry.replaceAll("[a-zA-Z]", "_");
            int attemptsLeft = 5;
            boolean hasWon = false;

            System.out.println("Welcome to the Country Guessing Game!");
            System.out.println("Try to guess the name of the country based on its description. You have " + attemptsLeft + " attempts.");
            System.out.println("Description: " + countryDescription);
            System.out.println("Country: " + hiddenCountry);

            while (attemptsLeft > 0 && !hasWon) {
                System.out.print("Enter your guess (single letter or full country name): ");
                String guess = scanner.nextLine().trim();

                if (guess.equalsIgnoreCase(selectedCountry)) {
                    hasWon = true;
                } else if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                    char guessedLetter = guess.charAt(0);
                    boolean isCorrectGuess = false;

                    StringBuilder newHiddenCountry = new StringBuilder(hiddenCountry);
                    for (int i = 0; i < selectedCountry.length(); i++) {
                        if (Character.toLowerCase(selectedCountry.charAt(i)) == Character.toLowerCase(guessedLetter)) {
                            newHiddenCountry.setCharAt(i, selectedCountry.charAt(i));
                            isCorrectGuess = true;
                        }
                    }

                    if (isCorrectGuess) {
                        hiddenCountry = newHiddenCountry.toString();
                        System.out.println("Good guess! Country: " + hiddenCountry);
                    } else {
                        attemptsLeft--;
                        System.out.println("Incorrect guess. Attempts left: " + attemptsLeft);
                    }

                    if (!hiddenCountry.contains("_")) {
                        hasWon = true;
                    }
                } else {
                    System.out.println("Invalid guess. Please enter a single letter or the full country name.");
                }
            }

            if (hasWon) {
                System.out.println("Congratulations! You've guessed the country: " + selectedCountry);
            } else {
                System.out.println("Game Over! The country was: " + selectedCountry);
            }

            System.out.print("Do you want to play again? (yes or no): ");
            playAgain = scanner.nextLine().trim();
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
