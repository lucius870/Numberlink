package sk.tuke.gamestudio.game.NumberLink.consoleUI;


import sk.tuke.gamestudio.game.NumberLink.core.Field;
import sk.tuke.gamestudio.game.NumberLink.core.GameState;
import sk.tuke.gamestudio.game.NumberLink.core.Grid;

import java.util.Scanner;

public class ConsoleUI {
    private Field field;
    private final Scanner scanner = new Scanner(System.in);
    public String RESET = "\u001B[0m";

    public String[] colorSet = {"\u001b[38;5;33m",
            "\u001b[38;5;196m",
            "\u001b[38;5;51m",
            "\u001b[38;5;129m",
            "\u001b[38;5;220m",
            "\u001b[38;5;68m",
            "\u001b[38;5;201m",
            "\u001b[38;5;48m",
            "\u001b[38;5;108m",
            "\u001b[38;5;221m",
            "\u001b[38;5;160m",
            "\u001b[38;5;226m",
            "\u001b[38;5;94m",
            "\u001b[38;5;46m",
            "\u001b[38;5;198m",
            "\u001b[38;5;93m",
            "\u001b[38;5;129m",
            "\u001b[38;5;226m",
            "\u001b[38;5;172m",
            "\u001b[38;5;120m",
            "\u001b[38;5;196m",
            "\u001b[38;5;202m",
            "\u001b[38;5;84m",
            "\u001b[38;5;120m",
            "\u001b[38;5;196m",
            "\u001b[38;5;202m",
            "\u001b[38;5;84m",
            "\u001b[38;5;159m",
            "\u001b[38;5;222m" ,
            "\u001b[38;5;172m",
            "\u001b[38;5;201m",
            "\u001b[38;5;87m",
            "\u001b[38;5;131m"
    };

    public ConsoleUI( Field field) {

        this.field = field;
    }

    public void printBoard(Grid[][] board, int n ) {
        int startNum = 0;
        for (int Colnum = 1; Colnum <= field.getCollumnCount(); Colnum++){
            if (Colnum < 10){
                System.out.print("    " + Colnum + "   ");
            }
            else{
                System.out.print("   " + Colnum + "   ");
            }

        }
        System.out.println();
        for (Grid[] row : board) {
            for (Grid cell : row) {
                if (cell.pathNumber != 0&& cell.isEndpoint) {
                    if (startNum == 0) {
                        startNum = cell.pathNumber;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("+-------");
            }
            System.out.println("+");

            for (int j = 0; j < n; j++) {
                Grid cell = board[i][j];
                if (cell.pathNumber == 0) {
                    if (i == 0 && j == 0) {
                        System.out.printf("|       ");
                    } else {
                        String redColor = "\u001b[38;5;203m";
                        System.out.print("|"+redColor+"   X   "+RESET);
                    }
                } else if (!cell.isEndpoint) {
                    System.out.print("|       ");
                } else {
                    addColors(cell.pathNumber);
                }
            }
            System.out.println("|  " + (i+1));
        }

        for (int j = 0; j < n; j++) {
            System.out.print("+-------");
        }
        System.out.println("+\n");
    }
    public void addColors(int number) {
        if (number >= 1 && number <= field.getMaxPathNumber(field.board)) {
            if (number < 10){
                System.out.printf("|%s   %d   %s", colorSet[number], number, RESET);
            }
            else{
                System.out.printf("|%s  %d   %s", colorSet[number], number, RESET);
            }
        } else {
            System.out.print("|       ");
        }
    }


    public void processInput(Grid[][] board, int n, int maxNumber) {
        System.out.println();
        System.out.println("You have "+ field.getHints()+ " hints left");

        do {
            System.out.print("Enter number you want to connect (or 'H' for hint): ");
            String input = scanner.next();
            if (field.getHints() >=0){
                System.out.println("You have " + field.getHints()+ " hints left");
                if (input.equalsIgnoreCase("H")) {
                    field.makeHint(board);
                    field.isSolved(board);
                    if (field.getState() == GameState.SOLVED){
                        break;
                    }
                    printBoard(board,n);
                    continue;
                }
            }
            else {
                System.out.println("Sorry you ran out of hints.");
            }

            try {
                int line = Integer.parseInt(input);
                System.out.print("Enter row where to connect: ");
                int rows = scanner.nextInt();
                System.out.print("Enter column where to connect: ");
                int cols = scanner.nextInt();

                if (line > maxNumber || line < -1 || rows - 1 > field.getRowCount() + 1 || cols - 1 < 0 || cols - 1 > field.getCollumnCount() + 1) {
                    System.out.println("Wrong input must try again!!");
                } else {
                    if (board[rows - 1][cols - 1].pathNumber == line || line == 0) {
                        field.markPath(board, rows, cols, line);
                        break;
                    } else {
                        System.out.println("Upsss! That number doesn't belong there");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'H' for hint.");
            }
        } while (true);
    }
    public void play(Grid[][] board,int n){
        int maxNumber = field.getMaxPathNumber(board);
        System.out.println("Some basic information: ");
        System.out.println("There are " + maxNumber + " numbers that you need to connect" );
        System.out.println("If you want to remove path choose 0 and replace the number ");
        System.out.println("The X means that there is no path for that grid");
        System.out.println("If you don´t know what number to put and where press H");
        System.out.println("You have " + field.getHints()+ " hints left");
        System.out.println("Enjoy the game !");
        System.out.println("+++ The puzzle...\n");
        printBoard(board,n);
        do{
            processInput(board,n,maxNumber);
            printBoard(board,n);
        }
        while (!field.isSolved(board));

    }
}
