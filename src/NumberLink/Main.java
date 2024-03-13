package NumberLink;

import NumberLink.consoleUI.ConsoleUI;
import NumberLink.core.Field;

import NumberLink.core.Grid;
import NumberLink.core.LevelGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the size of the board");
            int n = scanner.nextInt();

            Grid[][] board = new Grid[n][n];
            LevelGenerator level = new LevelGenerator();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = new Grid();
                }
            }
            Field field = new Field(board, n, n);
            var ui = new ConsoleUI(field);

            while (level.addPath(board, n)) ;
            level.assignPathNumbers(board, n);

            ui.play(board, n);
            if (field.isSolved(board)) {
                System.out.println("If you want to play again press 'P', if not press 'L'");
                Scanner scanner1 = new Scanner(System.in);
                input = scanner1.nextLine();
                if (input.equalsIgnoreCase("L")) {
                    break;
                }
            }
        } while (true);

        System.out.println("CONGRATS YOU WON!!");
    }
}



