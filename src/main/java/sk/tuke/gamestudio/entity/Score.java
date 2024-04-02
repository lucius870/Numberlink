package sk.tuke.gamestudio.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NamedQuery(name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")
@NamedQuery(name = "Score.resetScores",
        query = "DELETE FROM Score")

public class Score{
    @Id
    @GeneratedValue
    private int ident;

    private String game;

    private String player;

    private int points;

    private Date playedOn;

    public Score(String game, Date playedOn, String player, int points) {
        this.game = game;
        this.player = player;
        this.points = points;
        this.playedOn = playedOn;
    }
    public Score(){

    }


}