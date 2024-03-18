import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.CommentServiceJDBC;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {

    public CommentService commentService = new CommentServiceJDBC();

    @Test
    public void addComment(){
        commentService.reset();
        var date = new Date();

        commentService.addComment(new Comment("numberlink", "Lucia","very good", date));
        var comments = commentService.getComments("numberlink");
        assertEquals(1,comments.size());
        assertEquals("numberlink",comments.get(0).getGame());
        assertEquals("Lucia", comments.get(0).getPlayer());
        assertEquals("very good",comments.get(0).getComment());
        assertEquals(date, comments.get(0).getCommentedOn());
    }

    @Test
    public void getComments(){

        commentService.reset();
        var date = new Date();
        commentService.addComment(new Comment("numberlink", "Lucia","very good", date));
        commentService.addComment(new Comment("numberlink", "Mimus","great", date));
        commentService.addComment(new Comment("numberlink", "Alfonz","baddd", date));
        commentService.addComment(new Comment("numberlink", "Alfred","average", date));

        var comments = commentService.getComments("numberlink");

        assertEquals(4,comments.size());

        assertEquals("numberlink",comments.get(0).getGame());
        assertEquals("Lucia", comments.get(0).getPlayer());
        assertEquals("very good",comments.get(0).getComment());
        assertEquals(date, comments.get(0).getCommentedOn());

        assertEquals("numberlink",comments.get(1).getGame());
        assertEquals("Mimus", comments.get(1).getPlayer());
        assertEquals("great",comments.get(1).getComment());
        assertEquals(date, comments.get(1).getCommentedOn());

        assertEquals("numberlink",comments.get(2).getGame());
        assertEquals("Alfonz", comments.get(2).getPlayer());
        assertEquals("baddd",comments.get(2).getComment());
        assertEquals(date, comments.get(2).getCommentedOn());

        assertEquals("numberlink",comments.get(3).getGame());
        assertEquals("Alfred", comments.get(3).getPlayer());
        assertEquals("average",comments.get(3).getComment());
        assertEquals(date, comments.get(3).getCommentedOn());

    }

    @Test
    public void reset(){
        commentService.reset();
        assertEquals(0,commentService.getComments("numberlink").size());

    }
}
