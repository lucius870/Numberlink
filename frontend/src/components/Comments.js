import React from 'react';
import './stylesheet.css'

function Comments({comments}) {
    return (
        <table className="comments">
            <thead>
            <tr>
                <th>Player</th>
                <th>Comment</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            {comments.map(comment => (
                <tr key={`comment-${comment.ident}`}>
                    <td>{comment.player}</td>
                    <td>{comment.comment}</td>
                    <td>{new Date(comment.commentedAt).toLocaleString()}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
}

export default Comments;

