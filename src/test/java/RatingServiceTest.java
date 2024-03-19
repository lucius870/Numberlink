import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceTest {

    public RatingService ratingService = new RatingServiceJDBC();

    @Test
    public void reset(){
        ratingService.reset();
        assertEquals(0,ratingService.getAverageRating("numberlink"));
    }

    @Test
    public void setRating(){
        ratingService.reset();
        var date = new Date();
        Rating rating = new Rating("numberlink", "Lucia", 2, date);
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
        ratingService.setRating(new Rating("numberlink","Lucia",2,date));
        ratingService.setRating(new Rating("numberlink","Mimus",1,date));
        ratingService.setRating(new Rating("numberlink","Alfred",5,date));
        ratingService.setRating(new Rating("numberlink","Alfonz",4,date));

        assertEquals(5,ratingService.getRating("numberlink","Alfred"));
        assertEquals(4,ratingService.getRating("numberlink","Alfonz"));
        assertEquals(2,ratingService.getRating("numberlink","Lucia"));
        assertEquals(1,ratingService.getRating("numberlink","Mimus"));


    }
    @Test
    public void getAverageRating(){
        ratingService.reset();
        var date = new Date();
        ratingService.setRating(new Rating("numberlink","Lucia",2,date));
        ratingService.setRating(new Rating("numberlink","Mimus",1,date));
        ratingService.setRating(new Rating("numberlink","Alfred",1,date));
        ratingService.setRating(new Rating("numberlink","Alfonz",4,date));
        assertEquals(2,ratingService.getAverageRating("numberlink"));
        ratingService.setRating(new Rating("numberlink","Alzbeta",5,date));
        ratingService.setRating(new Rating("numberlink","Natalia",5,date));
        assertEquals(3,ratingService.getAverageRating("numberlink"));


    }


}
