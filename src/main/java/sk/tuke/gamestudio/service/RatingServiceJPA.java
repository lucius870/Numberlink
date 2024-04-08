package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional

public class RatingServiceJPA implements RatingService{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) throws RatingException {
        int affectedRows = entityManager.createNamedQuery("Rating.setRating")
                .setParameter("ratedOn",rating.getRatedOn())
                .setParameter("rating",rating.getRating())
                .setParameter("game",rating.getGame())
                .setParameter("player",rating.getPlayer())
                .executeUpdate();
        if (affectedRows == 0){
            entityManager.persist(rating);
        }

    }
    @Override
    public int getAverageRating(String game) throws RatingException {
        Object result = entityManager.createNamedQuery("Rating.getAverageRating")
                .setParameter("game", game)
                .getSingleResult();
        if (result != null) {
            return ((Number) result).intValue();
        } else {
            return 0;
        }
    }


    @Override
    public int getRating(String game, String player) throws RatingException {
        Rating rating = (Rating) entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game)
                .setParameter("player", player)
                .getSingleResult();
        return rating.getRating();
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRatings").executeUpdate();

    }
}
