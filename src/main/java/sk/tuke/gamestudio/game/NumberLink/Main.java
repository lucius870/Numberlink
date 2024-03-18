package sk.tuke.gamestudio.game.NumberLink;


import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.game.NumberLink.consoleUI.ConsoleUI;
import sk.tuke.gamestudio.game.NumberLink.core.Field;
import sk.tuke.gamestudio.game.NumberLink.core.Grid;
import sk.tuke.gamestudio.game.NumberLink.core.LevelGenerator;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;

import java.util.Date;
import java.util.Random;
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
                System.out.println("CONGRATS YOU WON!!");
                System.out.println("If you want to play again press 'P', if not press 'L'");
                Scanner scanner1 = new Scanner(System.in);
                input = scanner1.nextLine();
                if (input.equalsIgnoreCase("L")) {
                    ui.saveScore();
                    ui.printScores();
                    System.out.println("Please leave a comment how to improve the game: ");
                    input = scanner1.nextLine();
                    ui.saveComment(input);
                    ui.printComment();
                   /* ui.saveRating();
                    ui.printRating();*/
                    break;
                }
            }
        } while (true);







    }
}