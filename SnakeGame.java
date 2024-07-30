import java.util.Random;
import java.util.Scanner;

public class SnakeGame {

    static final int WIDTH = 20;
    static final int HEIGHT = 20;
    static char[][] board = new char[HEIGHT][WIDTH];
    static int[] snakeX = new int[WIDTH * HEIGHT];
    static int[] snakeY = new int[WIDTH * HEIGHT];
    static int snakeLength = 1;
    static int foodX, foodY;
    static char direction = 'R'; // U: Up, D: Down, L: Left, R: Right
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeGame();
        while (true) {
            printBoard();
            getUserInput();
            moveSnake();
            if (checkCollision()) {
                System.out.println("Game Over! You collided with something.");
                break;
            }
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                growSnake();
                placeFood();
            }
        }
    }

    private static void initializeGame() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = ' ';
            }
        }
        snakeX[0] = WIDTH / 2;
        snakeY[0] = HEIGHT / 2;
        placeFood();
    }

    private static void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
                    System.out.print("#"); // Walls
                } else if (i == snakeY[0] && j == snakeX[0]) {
                    System.out.print("*"); // Snake head
                } else if (i == foodY && j == foodX) {
                    System.out.print("0"); // Food
                } else {
                    boolean isBody = false;
                    for (int k = 1; k < snakeLength; k++) {
                        if (i == snakeY[k] && j == snakeX[k]) {
                            System.out.print("*");
                            isBody = true;
                            break;
                        }
                    }
                    if (!isBody) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    private static void getUserInput() {
        System.out.print("Enter direction (WASD): ");
        char input = scanner.nextLine().toUpperCase().charAt(0);
        switch (input) {
            case 'W':
                if (direction != 'D') direction = 'U';
                break;
            case 'A':
                if (direction != 'R') direction = 'L';
                break;
            case 'S':
                if (direction != 'U') direction = 'D';
                break;
            case 'D':
                if (direction != 'L') direction = 'R';
                break;
        }
    }

    private static void moveSnake() {
        for (int i = snakeLength; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        switch (direction) {
            case 'U':
                snakeY[0]--;
                break;
            case 'D':
                snakeY[0]++;
                break;
            case 'L':
                snakeX[0]--;
                break;
            case 'R':
                snakeX[0]++;
                break;
        }
    }

    private static boolean checkCollision() {
        if (snakeX[0] == 0 || snakeX[0] == WIDTH - 1 || snakeY[0] == 0 || snakeY[0] == HEIGHT - 1) {
            return true;
        }
        for (int i = 1; i < snakeLength; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                return true;
            }
        }
        return false;
    }

    private static void growSnake() {
        snakeLength++;
    }

    private static void placeFood() {
        foodX = random.nextInt(WIDTH - 2) + 1;
        foodY = random.nextInt(HEIGHT - 2) + 1;
        for (int i = 0; i < snakeLength; i++) {
            if (foodX == snakeX[i] && foodY == snakeY[i]) {
                placeFood(); // Ensure food doesn't appear on the snake
            }
        }
    }
}
