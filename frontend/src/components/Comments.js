import React from 'react';
import './Component.css'
import moment from "moment";
import {formatDate} from "../_api/utils";
import dateFormat from "dateformat";

function Comments({comments}) {
    return (
        <div className="body">
        <table >
            <thead>
            <tr>
                <th colSpan="4">Comments</th>
            </tr>
            <tr>
                <th>No.</th>
                <th>Player</th>
                <th>Comment</th>
                <th>Date</th>
            </tr>

            {comments.map((comment,index) => (
                <tr key={comment.ident}>
                    <td style={{ textAlign: "center" }}>{index + 1}</td>
                    <td>{comment.player}</td>
                    <td>{comment.comment}</td>
                    <td>{moment(comment.commented_on).format('MMMM Do YYYY, h:mm:ss a')}</td>
                </tr>
            ))}
            </thead>
        </table>
        </div>
    );
}

export default Comments;

