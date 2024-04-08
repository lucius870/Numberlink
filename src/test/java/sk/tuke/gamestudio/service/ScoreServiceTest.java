package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@SpringBootTest

public class ScoreServiceTest {
/*
    @Autowired
    public ScoreService scoreService;*/

    public ScoreService scoreService = new ScoreServiceJDBC();

    @Test
    public void reset(){
        scoreService.reset();
        assertEquals(0,scoreService.getTopScores("numberlink").size());

    }
    @Test
    public void addScore() {
        scoreService.reset();
        var date = new Date();
        var score = new Score("numberlink",date, "Jaro", 100);

        scoreService.addScore(new Score("numberlink",date, "Jaro", 100 ));

        var scores = scoreService.getTopScores("numberlink");
        assertEquals(1, scores.size());
        assertEquals("numberlink", scores.get(0).getGame());
        assertEquals(date, scores.get(0).getPlayedOn());
        assertEquals("Jaro", scores.get(0).getPlayer());
        assertEquals(100, scores.get(0).getPoints());

    }

    @Test
    public void getTopScores() {
        scoreService.reset();
        var date = new Date();
        scoreService.addScore(new Score("numberlink", date,"Lucia", 100));
        scoreService.addScore(new Score("numberlink", date, "Mima", 150));
        scoreService.addScore(new Score("numberlink", date, "Alfred", 120));
        scoreService.addScore(new Score("numberlink", date, "Alfonz", 180));

        var scores = scoreService.getTopScores("numberlink");

        assertEquals(4, scores.size());

        assertEquals("numberlink", scores.get(1).getGame());
        assertEquals(date, scores.get(1).getPlayedOn());
        assertEquals("Mima", scores.get(1).getPlayer());
        assertEquals(150, scores.get(1).getPoints());


        assertEquals("numberlink", scores.get(0).getGame());
        assertEquals(date, scores.get(0).getPlayedOn());
        assertEquals("Alfonz", scores.get(0).getPlayer());
        assertEquals(180, scores.get(0).getPoints());


        assertEquals("numberlink", scores.get(2).getGame());
        assertEquals(date, scores.get(2).getPlayedOn());
        assertEquals("Alfred", scores.get(2).getPlayer());
        assertEquals(120, scores.get(2).getPoints());


        assertEquals("numberlink", scores.get(3).getGame());
        assertEquals(date, scores.get(3).getPlayedOn());
        assertEquals("Lucia", scores.get(3).getPlayer());
        assertEquals(100, scores.get(3).getPoints());

    }

}
