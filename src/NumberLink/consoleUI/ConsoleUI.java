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

        /*do{
            processInput();
            printField();
        }
        while (field.getState() == GameState.PLAYING);*/
    }
}
