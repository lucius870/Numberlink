package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;
import sk.tuke.gamestudio.service.RatingServiceJPA;

import javax.persistence.EntityManager;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class RatingServiceTest {

    @Autowired
    public RatingService ratingService;
    //public RatingService ratingService = new RatingServiceJDBC();


    @Test
    public void reset(){
        ratingService.reset();
        assertEquals(0,ratingService.getAverageRating("numberlink"));
    }

    @Test
    public void setRating(){
        ratingService.reset();
        var date = new Date();
        Rating rating = new Rating("numberlink", "Lucia",date ,2);
        ratingService.setRating(rating);
        int gotRating = ratingService.getRating("numberlink", "Lucia");
        assertEquals(2, gotRating);
        assertEquals("numberlink", rating.getGame());
        assertEquals("Lucia", rating.getPlayer());
        assertEquals(2, rating.getRating());
        assertEquals(date, rating.getRatedOn());

    }

    @Test
    public void getRating(){
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("numberlink","Lucia",date,2));
        ratingService.setRating(new Rating("numberlink","Mimus",date,1));
        ratingService.setRating(new Rating("numberlink","Alfred",date,5));
        ratingService.setRating(new Rating("numberlink","Alfonz",date,4));
        assertEquals(5,ratingService.getRating("numberlink","Alfred"));
        assertEquals(4,ratingService.getRating("numberlink","Alfonz"));
        assertEquals(2,ratingService.getRating("numberlink","Lucia"));
        assertEquals(1,ratingService.getRating("numberlink","Mimus"));
    }
    @Test
    public void getAverageRating(){
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("numberlink","Lucia",date,2));
        ratingService.setRating(new Rating("numberlink","Mimus",date,1));
        ratingService.setRating(new Rating("numberlink","Alfred",date,1));
        ratingService.setRating(new Rating("numberlink","Alfonz",date,4));
        assertEquals(2,ratingService.getAverageRating("numberlink"));
        ratingService.setRating(new Rating("numberlink","Alzbeta",date,5));
        ratingService.setRating(new Rating("numberlink","Natalia",date,5));
        assertEquals(3,ratingService.getAverageRating("numberlink"));
    }


}
