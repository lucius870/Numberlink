package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.NumberLink.consoleUI.ConsoleUI;
import sk.tuke.gamestudio.game.NumberLink.core.Field;
import sk.tuke.gamestudio.game.NumberLink.core.Grid;
import sk.tuke.gamestudio.game.NumberLink.core.LevelGenerator;
import sk.tuke.gamestudio.service.*;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

   @Bean
    public CommandLineRunner runner(ConsoleUI consoleUI) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please first enter your name");
            var name = scanner.nextLine();
            consoleUI.play(field().board, field().getRowCount());
            if (field().isSolved(field().board)) {
                handleGameEnd(consoleUI,name);
            }
        };

    }

    private static int getBoardSize(Scanner scanner) {
        int n;
        do {
            System.out.println("Enter the size of the board");
            n = scanner.nextInt();
        } while (n > 15 || n < 1);
        return n;
    }

    private static Field createField(int n) {
        Grid[][] board = new Grid[n][n];
        LevelGenerator level = new LevelGenerator();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Grid();
            }
        }
        while (level.addPath(board, n)) ;
        level.assignPathNumbers(board, n);
        return new Field(board, n, n);
    }

    private static void handleGameEnd(ConsoleUI ui,String name) {
        System.out.println("CONGRATS YOU WON!!");
        System.out.println("If you want to play again press 'P', if not press 'L'");
        Scanner scanner1 = new Scanner(System.in);
        String input = scanner1.nextLine();
        if (input.equalsIgnoreCase("L")) {
            ui.saveScore(name);
            ui.printScores();
            System.out.println("Please leave a comment how to improve the game: ");
            input = scanner1.nextLine();
            ui.saveComment(name,input);
            ui.printComment();
            ui.saveRating(name);
            ui.printRating(name);
            System.exit(0);
        }
    }

    @Bean
    public Field field(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        int n = getBoardSize(scanner);
        return createField(n);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ConsoleUI consoleUI(Field field) {
        return new ConsoleUI(field);
    }
/*
    @Bean
    public ScoreService scoreService() {
       return new ScoreServiceJPA();

    }

    @Bean
    public CommentService commentService() {
      return new CommentServiceJPA();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }*/


    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceRestClient();
    }





}

