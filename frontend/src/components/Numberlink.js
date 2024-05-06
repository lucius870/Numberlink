import React, {useEffect, useState} from "react";
import axios from "axios";
import Scores from "./Scores";
import Comments from "./Comments";

const Numberlink = () =>{

    const FIELD_REMOTE_URL = "http://localhost:8080/numberlink";
    const [scores, setScores] = useState([]);
    const [comments, setComments] = useState([]);

    useEffect(() => {
        fetchScore();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    useEffect(() => {
        fetchComment();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const fetchScore = () => axios.get('/api/score/numberlink')
        .then(response => setScores(response.data))
        .catch(() => setScores([]));

    const fetchComment = () => axios.get('/api/comment/numberlink')
        .then(response => setComments(response.data))
        .catch(() => setComments([]));

    return (
        <div>
            <h1>Numberlink</h1>

            <div className="game-container">
                <a className="connect" href={FIELD_REMOTE_URL + "/connect"} target="gameWindow">Connect</a>
                <a className="hint" href={FIELD_REMOTE_URL + "/hint"} target="gameWindow">Hint</a>
                <a className="newGame" href={FIELD_REMOTE_URL + "/new"} target="gameWindow">New Game</a>
            </div>

            <iframe src={FIELD_REMOTE_URL + "/new"} width="100%" height={780} name="gameWindow" style={{border: "0"}}/>

            <Scores scores={scores}/>
            <Comments comments={comments}/>
        </div>
    )
}
export default Numberlink;




/*
const Mines = () => {
    const FIELD_REMOTE_URL = "http://localhost:8080/mines/field";

    const [scores, setScores] = useState([]);

    useEffect(() => {
        fetchScore();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const fetchScore = () => axios.get('/api/score/mines')
        .then(response => setScores(response.data))
        .catch(() => setScores([]));

    return (
        <div>
            <h1>Minesweeper</h1>

            <div className="minesControl">
                <a className="abutton" href={FIELD_REMOTE_URL + "/mark"} target="gameWindow">Change to OPEN/MARK</a>
                <a className="abutton" href={FIELD_REMOTE_URL + "/new"} target="gameWindow">New Game</a>
            </div>

            <iframe src={FIELD_REMOTE_URL + "/new"} width="100%" height={780} name="gameWindow" style={{border: "0"}}/>

            <Scores scores={scores}/>
        </div>
    )
}

export default Mines;

 */