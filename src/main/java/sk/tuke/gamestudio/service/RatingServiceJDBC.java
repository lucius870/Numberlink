package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.sql.*;

public class RatingServiceJDBC implements RatingService{

    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT  rating FROM rating WHERE game = ? AND player = ?";
    public static final String SELECT_AVG = "SELECT AVG(rating) FROM rating WHERE game = ?";
    public static final String DELETE = "DELETE FROM rating";
    public static final String INSERT = "INSERT INTO rating (game,player,rated_On,rating) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE rating SET rating = ? WHERE game = ? AND player = ?";

    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE)) {
                if (rating.getRating() <=-1){
                    throw new RatingException("Rating cannot be negative value");
                }
                else if (rating.getRating() >= 6){
                    throw new RatingException("Rating cannot be higher than 5");
                }
                else {
                    updateStatement.setInt(1, rating.getRating());
                    updateStatement.setString(2, rating.getGame());
                    updateStatement.setString(3, rating.getPlayer());

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected == 0) {
                        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
                            statement.setString(1, rating.getGame());
                            statement.setString(2, rating.getPlayer());
                            statement.setTimestamp(3, new Timestamp(rating.getRatedOn().getTime()));
                            statement.setInt(4, rating.getRating());
                            statement.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException r) {
            throw new RatingException("Problem setting rating", r);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(SELECT_AVG)
        ) {
            statement.setString(1, game);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RatingException("Problem getting avg rating", e);
        }
    }


    @Override
    public int getRating(String game, String player) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT);
        ){
            statement.setString(1,game);
            statement.setString(2,player);
            try(ResultSet rr = statement.executeQuery()){
                if (rr.next()){
                    return rr.getInt(1);
                }
                else{
                    throw new RatingException("Problem selecting rating");
                }

            }
        }
        catch (SQLException r){
            throw new RatingException("Problem getting the rating",r);
        }
    }


    @Override
    public void reset() throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new RatingException("Problem deleting rating", e);
        }

    }
}

