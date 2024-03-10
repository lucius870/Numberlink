package NumberLink.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Field {
    public Grid[][] board;

    private Random random = new Random();
    /*public int[][] tryArray = {
            {0, 0,0,0,2,3,3},
            {0, 4,4,0,0,0,0},
            {0, 0,5,0,6,6,0},
            {'X', 1,5,1,2,0,0},
            {0, 0,0,0,0,7,'X'},
            {0, 8,0,0,0,8,7},
            {0, 0,0,0,0,0,0}

    };*/

    private GameState state = GameState.PLAYING;

    public int[] numbers = {};

    private int rowCount;
    private int collumnCount;

    public Field(int row, int col) {
        this.rowCount=row;
        this.collumnCount = col;
        this.board = new Grid[row][col];
        initBoard();
    }

    public void initBoard(){
        for (int row = 0; row < rowCount; row++){
            for (int col = 0; col < collumnCount; col++){
                board[row][col] = new Grid();
            }
        }
    }

    private Grid findSet(Grid grid){

        if (grid.parent != grid){
            grid.parent=grid;
        }
        return grid.parent;
    }

    private void setUnion(Grid grid1, Grid grid2){


        Grid root1 = findSet(grid1);
        Grid root2 = findSet(grid2);

        if (root1.parent != null && root2.parent!=null){
            if (root1.rank< root2.rank){
                root1.parent = root2;
            }
            else if (root1.rank > root2.rank){
                root2.parent = root1;
            }
            else{
                root2.parent = root1;
                root1.rank++;
            }

        }
    }
    public boolean addPath() {
        int row1 = 0;
        int col1 = 0;
        int row2=0, col2=0;

        // Finding the squares on the board
        outerloop:
        for (row1 = 0; row1 < rowCount; row1++) {
            for (col1 = 0; col1 < collumnCount; col1++) {
                if (board[row1][col1].rank == 0) {
                    break outerloop;
                }
            }
        }
        if (row1 == rowCount && col1 == collumnCount) {
            return false;
        }


        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean validDirectionFound = false;
        while (!validDirectionFound) {
            int[] direction = directions[random.nextInt(4)];
            row2 = row1 + direction[0];
            col2 = col1 + direction[1];
            if (row2 >= 0 && row2 < rowCount && col2 >= 0 && col2 < collumnCount && board[row2][col2].rank == 0) {
                validDirectionFound = true;
                System.out.println("Valid direction found!");
                break;
            }
        }
        System.out.println();
        System.out.println("R1 " + row1 + " C" + col1);
        System.out.println("R2 " + row2 + "C " + col1);
        setUnion(board[row1][col1], board[row2][col2]);
        board[row1][col1].isEndpoint = true;
        board[row2][col2].isEndpoint = true;

        while (true) {
            row1 = row2;
            col1 = col2;
            int[][] directions2 = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean neighborFound = false;
            for (int[] direction : directions2) {
                row2 = row1 + direction[0];
                col2 = col1 + direction[1];
                if (row2 >= 0 && row2 < rowCount && col2 >= 0 && col2 < collumnCount && board[row2][col2].rank == 0) {
                    neighborFound = true;
                    System.out.print(" (" + row2 + ", " + col2 + ")");
                    break;
                }
            }
            if (!neighborFound) {
                break;
            }
            setUnion(board[row1][col1], board[row2][col2]);
            board[row2][col2].isEndpoint = true;

        }System.out.println();


        return true;
    }


    public void addPathNumber(){
        int pathNum = 1;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < collumnCount; col++) {
                Grid root = findSet(board[row][col]);
                if (root.rank > 0 && root.pathNumber == 0) {
                    root.pathNumber = pathNum;
                    numbers[row] = pathNum;
                    System.out.println("You are here " + numbers[row]);
                    pathNum++;

                    // Propagate path number to all nodes in the tree
                    for (int rows = 0; rows < rowCount; rows++) {
                        for (int cols = 0; cols < collumnCount; cols++) {
                            if (findSet(board[rows][cols]) == root) {
                                board[rows][cols].pathNumber = root.pathNumber;
                            }
                        }
                    }
                }
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getCollumnCount() {
        return collumnCount;
    }

    public void setCollumnCount(int collumnCount) {
        this.collumnCount = collumnCount;
    }

    public GameState getState() {
        return state;
    }

    public void markPath(int row, int col, int number){
        for (int i = 1; i <= getRowCount()+2; i++) {
            for (int j = 1; j <= getCollumnCount() + 2; j++) {
                if (i == row && j == col) {
                        board[i - 1][j - 1].rank = number;
                }
            }
        }
    }


}
