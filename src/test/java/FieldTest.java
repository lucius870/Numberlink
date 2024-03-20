import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.NumberLink.core.Field;
import sk.tuke.gamestudio.game.NumberLink.core.GameState;
import sk.tuke.gamestudio.game.NumberLink.core.Grid;
import sk.tuke.gamestudio.game.NumberLink.core.GridState;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    @Test
    public void getMaxPathNumber(){
        Grid[][] board = {
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()}
        };
        board[0][0].pathNumber = 1;
        board[0][1].pathNumber = 2;
        board[0][2].pathNumber = 3;
        board[1][0].pathNumber = 1;
        board[1][1].pathNumber = 2;
        board[1][2].pathNumber = 1;
        board[2][0].pathNumber = 1;
        board[2][1].pathNumber = 0;
        board[2][2].pathNumber = 1;

        Field field = new Field(board,3,3);
        assertEquals(3,field.getMaxPathNumber(board));
    }

    @Test
    public void markPath(){
        Grid[][] board = {
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()}
        };
        board[0][0].pathNumber = 1;
        board[0][1].pathNumber = 2;
        board[0][2].pathNumber = 3;
        board[1][0].pathNumber = 1;
        board[1][1].pathNumber = 2;
        board[1][2].pathNumber = 1;
        board[2][0].pathNumber = 1;
        board[2][1].pathNumber = 0;
        board[2][2].pathNumber = 1;

        Field field = new Field(board,3,3);

        field.markPath(board,3,3,1);
        assertTrue(board[2][2].isEndpoint);
        assertEquals(GridState.MARKED,board[2][2].getState());

        field.markPath(board,1,2,0);
        assertFalse(board[0][1].isEndpoint);
        assertEquals(GridState.OPEN,board[0][1].getState());
    }

    @Test
    public void isSolved(){
        Grid[][] board = {
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()}
        };
        board[0][0].pathNumber = 1;
        board[0][1].pathNumber = 2;
        board[0][2].pathNumber = 3;
        board[1][0].pathNumber = 1;
        board[1][1].pathNumber = 2;
        board[1][2].pathNumber = 1;
        board[2][0].pathNumber = 1;
        board[2][1].pathNumber = 0;
        board[2][2].pathNumber = 1;
        board[0][0].isEndpoint = true;
        board[0][1].isEndpoint = true;
        board[0][2].isEndpoint = true;
        board[1][0].isEndpoint = true;
        board[1][1].isEndpoint = true;
        board[1][2].isEndpoint = false;
        board[2][0].isEndpoint = true;
        board[2][1].isEndpoint = true;
        board[2][2].isEndpoint = true;

        Field field = new Field(board,3,3);
        assertFalse(field.isSolved(board));
        assertEquals(GameState.PLAYING, field.getState());

        board[1][2].isEndpoint = true;
        assertTrue(field.isSolved(board));
        assertEquals(GameState.SOLVED, field.getState());

    }
    @Test
    public void makeHint(){
        Grid[][] board = {
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()},
                {new Grid(),new Grid(),new Grid()}
        };
        board[0][0].pathNumber = 1;
        board[0][1].pathNumber = 2;
        board[0][2].pathNumber = 1;
        board[1][0].pathNumber = 1;
        board[1][1].pathNumber = 2;
        board[1][2].pathNumber = 1;
        board[2][0].pathNumber = 1;
        board[2][1].pathNumber = 2;
        board[2][2].pathNumber = 1;
        board[0][0].isEndpoint = false;
        board[0][1].isEndpoint = true;
        board[0][2].isEndpoint = true;
        board[1][0].isEndpoint = true;
        board[1][1].isEndpoint = true;
        board[1][2].isEndpoint = true;
        board[2][0].isEndpoint = true;
        board[2][1].isEndpoint = true;
        board[2][2].isEndpoint = true;

        Field field = new Field(board,3,3);
        int initialHints = field.getHints();
        field.makeHint(board);
        assertEquals(initialHints-1,field.getHints());
        assertTrue((board[0][0].isEndpoint));
        assertEquals(GridState.HINTED,board[0][0].getState());
    }

    @Test
    public void getScore(){

        Grid[][] board = {
            {new Grid(),new Grid(),new Grid()},
            {new Grid(),new Grid(),new Grid()},
            {new Grid(),new Grid(),new Grid()}
    };
        board[0][0].setState(GridState.MARKED);
        board[0][1].setState(GridState.OPEN);
        board[0][2].setState(GridState.MARKED);
        board[1][0].setState(GridState.HINTED);
        board[1][1].setState(GridState.OPEN);
        board[1][2].setState(GridState.OPEN);
        board[2][0].setState(GridState.MARKED);
        board[2][1].setState(GridState.OPEN);
        board[2][2].setState(GridState.HINTED);

        Field field = new Field(board, 3, 3);

        int score = 20;
        assertEquals(score,field.getScore());

    }
}
