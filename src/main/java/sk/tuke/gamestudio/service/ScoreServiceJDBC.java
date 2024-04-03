package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ScoreServiceJDBC implements ScoreService {
    private static final String URL = "jdbc:postgresql://localhost/gamestudio";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static final String INSERT_COMMAND = "INSERT INTO score (game,playedOn, player, points ) VALUES (?, ?, ?, ?)";

    public static final String DELETE_COMMAND = "DELETE FROM score";

    public static final String SELECT_COMMAND = "SELECT  game,playedOn, player, points FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";

    @Override
    public void addScore(Score score) {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.prepareStatement(INSERT_COMMAND)) {

            statement.setString(1, score.getGame());
            statement.setTimestamp(2, new Timestamp(score.getPlayedOn().getTime()));
            statement.setString(3, score.getPlayer());
            statement.setInt(4, score.getPoints());


            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("Problem adding scores",e);
        }
    }

    @Override
    public List<Score> getTopScores(String game) {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.prepareStatement(SELECT_COMMAND)) {
            statement.setString(1, game);
            try (var rs = statement.executeQuery()) {
                var scores = new ArrayList<Score>();

                while (rs.next()) {
                    scores.add(new Score(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getInt(4)));
                }

                return scores;
            }
        } catch (SQLException e) {
            throw new ScoreException("Problem getting scores",e);
        }
    }

    @Override
    public void reset() {
        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_COMMAND);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting scores",e);
        }
    }
}

