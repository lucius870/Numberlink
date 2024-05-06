import React from 'react';

const Comments = ({comments}) => {
    return (
        <div>
            <h2>Comments</h2>
            <table className="comments">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Player</th>
                    <th>Points</th>
                    <th>Played at</th>
                </tr>
                </thead>
                <tbody>
                {comments.map((comment, index) => (
                    <tr key={comment.ident}>
                        <td style={{textAlign: "center"}}>{index + 1}</td>
                        <td>{comment.player}</td>
                        <td>{comment.comment}</td>
                        <td>{new Date(comment.commentOn).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Scores;