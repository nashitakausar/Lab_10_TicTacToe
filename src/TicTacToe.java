import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final String EMPTY = " ";
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            String currentPlayer = "X";
            boolean gameWon = false;
            int moves = 0;

            while (!gameWon && moves < ROWS * COLS) {
                display();
                int row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter col (1-3): ", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    moves++;

                    if (isWin(currentPlayer)) {
                        display();
                        System.out.println(currentPlayer + " wins!");
                        gameWon = true;
                    } else if (isTie()) {
                        display();
                        System.out.println("It's a tie!");
                        break;
                    }
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                } else {
                    System.out.println("Invalid move, try again.");
                }
            }

            playAgain = SafeInput.getYNConfirm(console, "Play again? (Y/N): ");
        } while (playAgain);

        console.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + (j < COLS - 1 ? " | " : ""));
            }
            System.out.println();
            if (i < ROWS - 1) {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(EMPTY);
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}
