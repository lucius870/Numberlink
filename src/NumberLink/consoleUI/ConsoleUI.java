package NumberLink.consoleUI;

import NumberLink.Main;
import NumberLink.core.Field;
import NumberLink.core.GameState;
import NumberLink.core.Grid;

import java.util.Scanner;

public class ConsoleUI {
    private Field field;
    private final Scanner scanner = new Scanner(System.in);


    public ConsoleUI( Field field) {

        this.field = field;
    }

    public void printBoard(Grid[][] board, int n ) {
        int startNum = 0;

        for (int Colnum = 1; Colnum <= field.getCollumnCount(); Colnum++){
            System.out.print("    " + Colnum + "   ");
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
                            System.out.print("|   X   ");
                        }
                    } else if (!cell.isEndpoint) {
                        System.out.print("|       ");
                    } else {
                        if (cell.pathNumber < 10){
                            System.out.printf("|   %d   ", cell.pathNumber);
                        }
                        else{
                            System.out.printf("|  %d   ", cell.pathNumber);
                        }

                    }
            }

            System.out.println("|  " + (i+1));
        }

        for (int j = 0; j < n; j++) {
            System.out.print("+-------");
        }
        System.out.println("+\n");

    }


    public void processInput(Grid[][] board, int n, int maxNumber) {
        System.out.println();

        do {
            System.out.print("Enter number you want to connect (or 'H' for hint): ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("H")) {
                field.makeHint(board);
                field.isSolved(board);
                if (field.getState() == GameState.SOLVED){
                    System.out.println(field.getState());
                    break;
                }
                printBoard(board,n);
                continue;
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
        System.out.println("+++ The puzzle...\n");
        printBoard(board,n);
        System.out.println("Some basic information: ");
        System.out.println("There are " + maxNumber + " numbers that you need to connect" );
        System.out.println("If you want to remove path choose 0 and replace the number ");
        System.out.println("The X means that there is no path for that grid");
        System.out.println("If you don´t know what number to put and where press H");
        do{
            processInput(board,n,maxNumber);
            printBoard(board,n);


        }
        while (!field.isSolved(board));


    }
}
