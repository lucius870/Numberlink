package NumberLink.consoleUI;

import NumberLink.core.Field;
import NumberLink.core.GameState;

import java.util.Scanner;

public class ConsoleUI {
    private Field field;
    private final Scanner scanner = new Scanner(System.in);
    private int line;
    private int rows;
    private  int cols;

    public ConsoleUI(Field field) {
        this.field = field;
    }

    public void printField(){
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
        for (int Colnum = 1; Colnum <= field.getCollumnCount()+2; Colnum++){
            System.out.print("   " + Colnum + "  ");
        }
        System.out.println();
        for(int row = 0; row < field.board.length; row++){
            System.out.println("+-----+-----+-----+-----+-----+-----+-----+");
            for (int col = 0; col < field.board[row].length; col++){
                System.out.print("|  ");
                if (field.board[row][col].rank>0){
                    System.out.print(field.board[row][col].pathNumber+ "  ");


                }else{
                    System.out.print("X  ");
                }

            }
            int rowNum = row+1;
            System.out.print("| " +rowNum );

            System.out.println();
        }
        System.out.println("+-----+-----+-----+-----+-----+-----+-----+");
    }

    public void processInput(){
        System.out.println("To remove path put 0");

        do {
            System.out.print("Enter number you want to connect: ");
            line = scanner.nextInt();
            System.out.print("Enter row where to connect: ");
            rows = scanner.nextInt();
            System.out.print("Enter collumn where to connect: ");
            cols = scanner.nextInt();
        }
        while (line < -1 || line > 9 || rows - 1 < 0 || rows - 1 > field.getRowCount()+1 || cols - 1 < 0 || cols - 1 > field.getCollumnCount()+1);
        if (field.board[rows-1][cols-1].rank == 0 || line == 0  ) {
            field.markPath(rows, cols, line);
        }
        else{
            System.out.println("wrong move, cant put the path there");
        }
    }
    public void play(){
        printField();
        /*do{
            processInput();
            printField();
        }
        while (field.getState() == GameState.PLAYING);*/
    }
}
