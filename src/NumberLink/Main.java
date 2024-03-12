package NumberLink;

import NumberLink.consoleUI.ConsoleUI;
import NumberLink.core.Field;

import NumberLink.core.Grid;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Field object with 5 rows and 5 columns

        Field field = new Field(5, 5);
        var ui = new ConsoleUI(field);
        Grid[][]board = new Grid[5][5];

        System.out.println("+++ Initializing board...\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Grid();
            }
        }


        while (field.addPath(board,5));

        System.out.println("\n+++ Assigning path numbers...\n");
        field.assignPathNumbers(board, 5);

        System.out.println("+++ The puzzle...\n");
        field.printBoard(board, 5,false);

        System.out.println("++The solution...\n");
        field.printBoard(board,5,true);





    }
}

