package NumberLink.core;

import java.util.*;

public class Field {
    public Grid[][] board;


    private GameState state = GameState.PLAYING;


    private int rowCount;
    private int columnCount;


    public Field(Grid[][] board,int row, int col) {
        this.rowCount=row;
        this.columnCount = col;
        this.board = board;

    }

    public int getMaxPathNumber(Grid[][]board){
        int max = 0;
        for (int row = 0; row < getRowCount(); row++){
            for (int col = 0; col < getCollumnCount(); col++ ){
                if (max < board[row][col].pathNumber){
                    max = board[row][col].pathNumber;
                }
            }
        }

        return max;
    }
    public void markPath(Grid[][]board, int row, int col, int input) {
            if (input == 0){
                board[row-1][col-1].isEndpoint = false;
            }else{
                board[row-1][col-1].isEndpoint = true;
            }
    }

    public boolean isSolved(Grid[][]board){
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getCollumnCount(); col++) {
                if (board[row][col].pathNumber == 0) {
                    break;
                }
                if (board[row][col].isEndpoint == false) {
                    return false;
                }
            }
        }
        state = GameState.SOLVED;
        return true;
    }

    public void makeHint(Grid[][]board){
        Random rand = new Random();
        int row,col;
        do {
            row = rand.nextInt(getRowCount());
            col = rand.nextInt(getCollumnCount());
        }while(board[row][col].isEndpoint == true ||board[row][col].pathNumber == 0 );
        if (board[row][col].isEndpoint == false){
            board[row][col].isEndpoint = true;
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getCollumnCount() {
        return columnCount;
    }

    public void setCollumnCount(int collumnCount) {
        this.columnCount = collumnCount;
    }

    public GameState getState() {
        return state;
    }




}
