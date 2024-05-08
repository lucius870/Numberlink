import React from 'react';
import './Component.css';
import moment from "moment";



function Scores({scores}) {
    return (
        <div className="body">
        <table>
        <thead >
        <tr>
            <th colSpan="4">Top Scores</th>
        </tr>
        <tr>
            <th>No.</th>
            <th>Player</th>
            <th>Points</th>
            <th>Played at</th>
        </tr>


        {scores.map((score, index) => (
            <tr key={score.ident}>
                <td style={{ textAlign: "center" }}>{index + 1}</td>
                <td>{score.player}</td>
                <td>{score.points}</td>
                <td>{moment(score.playedAt).format('MMMM Do YYYY, h:mm:ss a')}</td>

            </tr>
        ))}

        </thead>
    </table>
        </div>
    );
}

export default Scores;

/*
  <td>{new Date(comment.commented_on).toLocaleString("lookup")}</td>
 */

