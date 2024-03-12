package NumberLink.core;

import java.util.*;

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
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int rowCount;
    private int columnCount;


    public Field(int row, int col) {
        this.rowCount=row;
        this.columnCount = col;


    }

    public Grid findSet(Grid grid) {
        if (grid.parent!=null) {
        grid = grid.parent;
        }
            return grid;
    }

    public void setUnion(Grid x, Grid y) {
        Grid rootX = findSet(x);
        Grid rootY = findSet(y);
        if (rootX == rootY)
            return;
        if (rootX.rank > rootY.rank) {
            rootY.parent = rootX;
        } else {
            rootX.parent = rootY;
            if (rootX.rank == rootY.rank) {
                rootY.rank++;
            }
        }
    }


    public boolean checkNeighbours(Grid[][]board, int rowA, int colA, int rowB, int colB, int length){
        int count =0;
        if (rowB + 1 < length && findSet(board[rowB + 1][colB]) == findSet(board[rowA][colA])) count++;
        if (rowB - 1 >= 0 && findSet(board[rowB - 1][colB]) == findSet(board[rowA][colA])) count++;
        if (colB + 1 < length && findSet(board[rowB][colB + 1]) == findSet(board[rowA][colA])) count++;
        if (colB - 1 >= 0 && findSet(board[rowB][colB - 1]) == findSet(board[rowA][colA])) count++;

        return count<=1;
    }


    public boolean addPath(Grid[][] board, int length) {
        Random rand = new Random();
        int rowA, colA, rowB, colB;

        rowA = rand.nextInt(length);
        colA = rand.nextInt(length);

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        rowB = -1;
        colB = -1;

        int count = 0;

        // Find a valid starting point
        while (true) {
            if (findSet(board[rowA][colA]).rank == 0) {
                int order = rand.nextInt(4);
                for (int o = 0; o < 4; o++) {
                    int ni = rowA + dx[order];
                    int nj = colA + dy[order];

                    if (ni >= 0 && ni < length && nj >= 0 && nj < length && findSet(board[ni][nj]).rank == 0) {
                        rowB = ni;
                        colB = nj;
                        break;
                    }
                    order = (order + 1) % 4;
                }

                if (rowB != -1 && colB != -1) break;
            }

            count++;
            if (count >= length * length) return false;

            colA = (colA + 1) % length;
            if (colA == 0) rowA = (rowA + 1) % length;
        }

        setUnion(board[rowA][colA], board[rowB][colB]);
        board[rowA][colA].isEndpoint = true;
        System.out.printf("New Path: (%d, %d) (%d, %d) ", rowA, colA, rowB, colB);

        // Extend the path
        while (true) {
            rowA = rowB;
            colA = colB;

            int status = 0;
            int order = rand.nextInt(4);
            for (int o = 0; o < 4; o++) {
                int ni = rowA + dx[order];
                int nj = colA + dy[order];

                if (ni >= 0 && ni < length && nj >= 0 && nj < length && findSet(board[ni][nj]).rank == 0 &&
                        checkNeighbours(board, rowA, colA, ni, nj, length)) {
                    rowB = ni;
                    colB = nj;
                    status = 1;
                    break;
                }

                order = (order + 1) % 4;
            }

            if (status == 0) break;

            setUnion(board[rowA][colA], board[rowB][colB]);
            System.out.printf("(%d, %d) ", rowB, colB);
        }

        board[rowB][colB].isEndpoint = true;
        System.out.println();
        return true;
    }


    public void assignPathNumbers(Grid[][] board, int n) {
        int pathNum = 1;
        for (Grid[] row : board) {
            for (Grid cell : row) {
                if (findSet(cell).rank != 0) {
                    if (findSet(cell).pathNumber == 0) {
                        findSet(cell).pathNumber = pathNum;
                        cell.pathNumber = pathNum;
                        pathNum++;
                    } else {
                        cell.pathNumber = findSet(cell).pathNumber;
                    }
                }
            }
        }
    }
    public void printBoard(Grid[][] board, int n, boolean solved) {
        int startNum = 0;

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
                if (!solved) {
                    System.out.print("+---");
                } else {
                    System.out.print("+---");
                }
            }
            System.out.println("+");

            for (int j = 0; j < n; j++) {
                Grid cell = board[i][j];
                if (!solved) {
                    if (cell.pathNumber == 0) {
                        if (i == 0 && j == 0) {
                            System.out.printf("| . ");
                        } else {
                            System.out.print("| X ");
                        }
                    } else if (!cell.isEndpoint) {
                        System.out.print("| . ");
                    } else {
                        System.out.printf("| %d ", cell.pathNumber);
                    }
                } else {
                    if (cell.pathNumber == 0) {
                        if (i == 0 && j == 0) {
                            System.out.printf("| . ");
                        } else {
                            System.out.print("| X ");
                        }
                    } else {
                        System.out.printf("| %d ", cell.pathNumber);
                    }
                }
            }
            System.out.println("|");
        }

        for (int j = 0; j < n; j++) {
            System.out.print("+---");
        }
        System.out.println("+\n");

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
