package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.NumberLink.core.Field;
import sk.tuke.gamestudio.game.NumberLink.core.GameState;
import sk.tuke.gamestudio.game.NumberLink.core.Grid;
import sk.tuke.gamestudio.game.NumberLink.core.LevelGenerator;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Date;

@Controller
@RequestMapping("/numberlink")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class NumberLinkController {

    private int n = 6;

    private Field field;

    public boolean connecting;
    public boolean hinting;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;


    public String[] colorSet = {
            "red",
            "blue",
            "navy",
            "purple",
            "orange",
            "darkcyan",
            "darkorange",
            "darkblue",
            "darkgreen",
            "darkmagenta",
            "darkslateblue",
            "deeppink",
            "darkseagreen",
            "darkolivegreen",
            "darkgoldenrod",
            "darkcyan",
            "purple",
            "deeppink",
            "darkred",
            "darkcyan",
            "darkorange",
            "darkkhaki",
            "darkred",
            "darkorange",
            "darkkhaki",
            "slateblue",
            "lightgreen",
            "darkolivegreen",
            "darkorange",
            "darkslateblue"
    };


    public String numberlink(@RequestParam int row, @RequestParam int column, @RequestParam int number, Model model) {
        if (connecting) {
            field.markPath(field.board, row, column, number);
        }
        if (hinting) {
            field.makeHint(field.board);
        }
        if (field.getState() == GameState.SOLVED){
            scoreService.addScore(new Score("numberlink",new Date(),"meno",field.getScore()));
            commentService.addComment(new Comment("numberlink","meno","dajaky koment",new Date()));
        }
        prepareModel(model);
        return "numberlink";

    }
        @GetMapping
        public String startGame(Model model){

        if (field == null) {
            initializeGame();
        }
            prepareModel(model);
            return "numberlink";
        }

    public void initializeGame(){
        Grid[][] board = new Grid[n][n];
        LevelGenerator level = new LevelGenerator();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Grid();
            }
        }

        field = new Field(board,n,n);

        while (level.addPath(board, n)) ;
        level.assignPathNumbers(board, n);
    }

    public String getHtmlGrid(Grid[][] board, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class='numberlink'>\n");

        for (int i = 0; i < n; i++) {
            sb.append("<tr>");
            for (int j = 0; j < n; j++) {
                Grid cell = board[i][j];
                sb.append("<td>");
                if (cell.pathNumber == 0) {
                    if (i == 0 && j == 0) {
                        sb.append("<a href='/numberlink?row=" + i + "&col = " + j + "'class ='grids' >&nbsp;");

                    } else {
                        sb.append("<span class='cross'>X</span>");
                    }
                } else if (!cell.isEndpoint) {
                    sb.append("<a  href='/numberlink?row=" + i + "&col = " + j + "'class ='grids' >&nbsp;");
                } else {
                    String color = getColorForNumber(cell.pathNumber);
                    sb.append("<span style='color: ").append(color).append("'>").append(cell.pathNumber).append("</span>");
                }
                sb.append("</td>");
            }
            sb.append("</tr>");
        }

        sb.append("</table>");
        return sb.toString();
    }
    private String getColorForNumber(int number) {
        int index = (number - 1) % colorSet.length;
        return colorSet[index];
    }

    @GetMapping("/new")
    public String newGame(Model model){

        startGame(model);
        return "numberlink";
    }

    @GetMapping("/connect")
    public String changeConnecting(Model model) {
        connecting = !connecting;
        prepareModel(model);
        return "numberlink";
    }

    @GetMapping("/hint")
    public String changeHinting(Model model) {
        hinting = !hinting;
        prepareModel(model);
        return "numberlink";
    }
    private void prepareModel (Model model){
        model.addAttribute("scores",scoreService.getTopScores("numberlink"));
        model.addAttribute("comment",commentService.getComments("numberlink"));
        model.addAttribute("htmlboard",getHtmlGrid(field.board, n));
        model.addAttribute("connecting", connecting);
        model.addAttribute("hinting", hinting);
    }




}

