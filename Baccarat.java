import java.util.Random;
import java.util.Scanner;

public class Baccarat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {
            System.out.println("Welcome to Baccarat!");

            // Deal cards
            int playerCard1 = drawCard(random);
            int playerCard2 = drawCard(random);
            int bankerCard1 = drawCard(random);
            int bankerCard2 = drawCard(random);

            int playerTotal = calculateBaccaratValue(playerCard1, playerCard2);
            int bankerTotal = calculateBaccaratValue(bankerCard1, bankerCard2);

            // Display initial cards and totals
            System.out.println("Player's cards: " + cardToString(playerCard1) + " and " + cardToString(playerCard2));
            System.out.println("Player's total: " + playerTotal);
            System.out.println("Banker's cards: " + cardToString(bankerCard1) + " and " + cardToString(bankerCard2));
            System.out.println("Banker's total: " + bankerTotal);

            // Determine if a third card is needed for the player
            if (playerTotal <= 5) {
                int playerThirdCard = drawCard(random);
                playerTotal = calculateBaccaratValue(playerTotal, playerThirdCard);
                System.out.println("Player draws a third card: " + cardToString(playerThirdCard));
                System.out.println("Player's new total: " + playerTotal);
            }

            // Determine if a third card is needed for the banker
            if (bankerTotal <= 5) {
                int bankerThirdCard = drawCard(random);
                bankerTotal = calculateBaccaratValue(bankerTotal, bankerThirdCard);
                System.out.println("Banker draws a third card: " + cardToString(bankerThirdCard));
                System.out.println("Banker's new total: " + bankerTotal);
            }

            // Determine winner
            if (playerTotal > bankerTotal) {
                System.out.println("Player wins!");
            } else if (bankerTotal > playerTotal) {
                System.out.println("Banker wins!");
            } else {
                System.out.println("It's a tie!");
            }

            System.out.print("Do you want to play again? (yes or no): ");
            playAgain = scanner.nextLine();
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    private static int drawCard(Random random) {
        return random.nextInt(13) + 1; // 1 to 13 representing Ace to King
    }

    private static int calculateBaccaratValue(int card1, int card2) {
        return (cardValue(card1) + cardValue(card2)) % 10; // Baccarat values range from 0 to 9
    }

    private static int cardValue(int card) {
        if (card >= 10) {
            return 0; // 10, Jack, Queen, and King are worth 0
        }
        return card; // Ace is worth 1, 2-9 are worth their face value
    }

    private static String cardToString(int card) {
        switch (card) {
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return String.valueOf(card);
        }
    }
}
