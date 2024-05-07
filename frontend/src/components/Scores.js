import React from 'react';
import './stylesheet.css';

function Scores({scores}) {
    return (
        <table className="scores">
            <thead>
            <tr>
                <th>Player</th>
                <th>Points</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            {scores.map(score => (
                <tr key={`score-${score.ident}`}>
                    <td>{score.player}</td>
                    <td>{score.points}</td>
                    <td>{new Date(score.playedAt).toLocaleString()}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
}

export default Scores;



