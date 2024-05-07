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
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.NumberLink.core.*;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/numberlink")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class NumberLinkController {

    private int n = 6;

    private Field field;


    public boolean connecting;
    public boolean hinting;
    public String playername;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;



    private int selectedNumber = 0;


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



    @GetMapping("/grid")
    public String startGame(Model model){

        if (field == null) {
            initializeGame();
        }
        prepareModel(model);
        return "numberlink";
    }
    @GetMapping("/grid/select")
    public String selectNumber(@RequestParam int number, Model model) {
        selectedNumber = number;
        connecting = true;
        prepareModel(model);
        return "numberlink";
    }

    @GetMapping("/grid/mark")
    public String numberlink(@RequestParam int row, @RequestParam int column, Model model) {
        if (connecting) {
            if (selectedNumber == field.board[row][column].pathNumber){
                field.markPath(field.getBoard(), row, column, selectedNumber);
                connecting = !connecting;
            }
        }
        else{
            field.markPath(field.getBoard(), row, column, selectedNumber);
        }

        if (field.isSolved(field.board)){
            scoreService.addScore(new Score("numberlink",new Date(),playername,field.getScore()));
            model.addAttribute("gameWon", true);
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
                        sb.append("<a href='/numberlink/grid/mark?row=" + i + "&column=" + j + "'class ='grids' >&nbsp;");
                    } else {
                        sb.append("<span class='cross'>X</span>");
                    }
                } else if (!cell.isEndpoint) {
                    sb.append("<a  href='/numberlink/grid/mark?row=" + i + "&column=" + j + "'class ='grids' >&nbsp;");
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


    public String getNumbers(Field field, Grid[][] board) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class='numberlink'>\n");
        Set<Integer> pathNumbers = new HashSet<>();
        for (int i = 0; i < field.getMaxPathNumber(board); i++) {
            for (int j = 0; j < field.getRowCount(); j++) {
                for (int k = 0; k < field.getCollumnCount(); k++) {
                    Grid grid = board[j][k];
                    if (grid.pathNumber == i + 1 &&!pathNumbers.contains(grid.pathNumber)) {
                        pathNumbers.add(grid.pathNumber);
                        sb.append("<tr><td ><a href='/numberlink/grid/select?number=").append(grid.pathNumber).append("'class='numbers'>").append(grid.pathNumber).append("</a></td></tr>\n");
                        break;
                    }
                }
            }
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    private String getColorForNumber(int number) {
        int index = (number - 1) % colorSet.length;
        return colorSet[index];
    }

    @GetMapping("/grid/new")
    public String newGame(@RequestParam String name, Model model){
        playername = name;
        field = null;
        startGame(model);
        return "numberlink";
    }
    @GetMapping("/grid/newGame")
    public String startAgain(Model model){
        field = null;
        startGame(model);
        return "numberlink";
    }

    @GetMapping("/grid/hint")
    public String changeHinting(Model model) {
        if (hinting) {
            field.makeHint(field.getBoard());
        }
        hinting = !hinting;
        prepareModel(model);
        return "numberlink";
    }
    private void prepareModel (Model model){

        model.addAttribute("htmlboard",getHtmlGrid(field.board, n));
        model.addAttribute("getNumbers",getNumbers(field,field.board));
        model.addAttribute("connecting", connecting);
        model.addAttribute("hinting", hinting);
    }




}

