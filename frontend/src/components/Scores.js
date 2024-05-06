import React from 'react';

const Scores = ({scores}) => {
    return (
        <div>
            <h2>Top scores</h2>
            <table className="top-scores">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Player</th>
                    <th>Points</th>
                    <th>Played at</th>
                </tr>
                </thead>
                <tbody>
                {scores.map((score, index) => (
                    <tr key={score.ident}>
                        <td style={{textAlign: "center"}}>{index + 1}</td>
                        <td>{score.player}</td>
                        <td>{score.points}</td>
                        <td>{new Date(score.playedOn00).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Scores;

