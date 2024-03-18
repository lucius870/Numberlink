package sk.tuke.gamestudio.game.NumberLink.core;

import java.util.Random;

public class LevelGenerator {



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
        board[rowA][rowB].setState(GridState.MARKED);

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
        }

        board[rowB][colB].isEndpoint = true;
        board[rowA][rowB].setState(GridState.MARKED);
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


}
