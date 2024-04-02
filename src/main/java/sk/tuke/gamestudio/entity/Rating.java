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
@NamedQuery(name = "Rating.getRating",
        query = "SELECT r FROM Rating r WHERE r.game=:game AND r.player=:player ")
@NamedQuery(name = "Rating.getAverageRating",
        query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game=:game" )
@NamedQuery(name = "Rating.resetRatings",
        query = "DELETE FROM Rating ")
public class Rating {

    @Id
    @GeneratedValue
    private int ident;
    private String game;

    private String player;

    private int rating;

    private Date ratedOn;

    public Rating(String game, String player, int rating, Date ratedOn) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public Rating() {

    }

}
