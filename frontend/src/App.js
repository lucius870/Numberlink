import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import React from 'react';
import {addComment, fetchComments} from "./_api/commentService";
import {useEffect, useState} from "react";
import Comments from "./components/Comments";
import Scores from "./components/Scores";
import {fetchScores} from "./_api/scoreService";
import CommentForm from "./components/CommentForm";
import Menu from "./components/Menu";
import {Route, Routes, useNavigate} from "react-router-dom";
import NumberLinkGame from "./components/game/Numberlink/NumberLinkGame";
import {addRating, fetchRatings} from "./_api/ratingService";
import Ratings from "./components/Ratings";
import RatingForm from "./components/RatingForm";
import LoginForm from "./components/LoginForm";


const gameList = [
    { title: 'numberlink', path: 'numberlink' },
];

function App() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [comments, setComments] = useState([]);
    const [scores, setScores] = useState([]);
    const [rating, setRating] = useState(0);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();

    const fetchData = () => {
        fetchScores("numberlink").then((response) =>{
            setScores(response.data);
        });
        fetchComments('numberlink').then((response) => {
            setComments(response.data);
        });
        fetchRatings('numberlink').then((rating) => setRating(rating)).catch((error) => {
            console.error('Error fetching ratings:', error);
            alert('Error fetching ratings: ' + error.message);
        });

    };

    useEffect(() => {
        fetchData();
    }, [isLoggedIn]);

    const handleCommentSent = (comment) => {
        addComment('numberlink', username, comment).then((response) => {
            fetchData();
        });
    };

    const handleRatingSent = (rating) => {
        addRating('numberlink', username, rating).then((response) => {
            fetchData();
        });
    };

    const handleLogin = (username, password) => {
        setUsername(username);
        setPassword(password);
        setIsLoggedIn(true);
    };
    const handleLogout = () => {
        setUsername('');
        setIsLoggedIn(false);
    };
    const checkSolved = (username) => {
        fetch(`/grid/solved?name=${username}`)
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error(error));
    };

    return (
        <div>

            <Menu gameList={gameList} />

            <div className="container index-container">
                <Routes>
                    <Route index element={
                        <React.Fragment>
                            <h1>Welcome to GameStudio!</h1>
                            {username ? <p>Hello {username}! Please select a game from the menu.</p> : <p>Please log in to continue.</p>}
                        </React.Fragment>
                    } />
                    <Route path={"scores"} element={
                        <React.Fragment>
                            <h1>Score</h1>
                            <Scores scores={scores}/>
                        </React.Fragment>
                    }/>
                    <Route path="comments">
                        <Route index element={
                            <React.Fragment>
                                <h1>Comments</h1>
                                <Comments comments={comments} />
                                {username ? <button onClick={() => navigate("/comments/add")}>Add Comment</button> : <p>Please log in to add a comment.</p>}
                            </React.Fragment>
                        } />
                        <Route path="add" element={
                            <React.Fragment>
                                <h2>Add comment:</h2>
                                {username ? <CommentForm onCommentSent={handleCommentSent} /> : <p>Please log in to add a comment.</p>}
                            </React.Fragment>
                        } />
                    </Route>
                    <Route path="rating">
                        <Route index element={
                            <React.Fragment>
                                <h1>Ratings</h1>
                                <Ratings rating={rating} />
                                {username ? <button onClick={() => navigate("/rating/add")}>Add Rating</button> : <p>Please log in to add a rating.</p>}
                            </React.Fragment>
                        } />
                        <Route path="add" element={
                            <React.Fragment>
                                <h2>Add Rating: </h2>
                                {username ? <RatingForm onRatingSent={handleRatingSent} /> : <p>Please log in to add a rating.</p>}
                            </React.Fragment>
                        } />
                    </Route>

                </Routes>

            </div>

            <div className="container index-container">
                <Routes>
                    <Route path="game">
                        <Route path="numberlink" element={<NumberLinkGame playerName={username} checkSolved={checkSolved} />} />
                    </Route>
                </Routes>
            </div>
            {!isLoggedIn && <LoginForm onLogin={handleLogin} />}
            {isLoggedIn && <button onClick={handleLogout}>Logout</button>}
        </div>

    );
}

export default App;




