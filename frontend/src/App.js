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
import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import NumberLinkGame from "./components/game/Numberlink/NumberLinkGame";
import {addRating, fetchRatings} from "./_api/ratingService";
import Ratings from "./components/Ratings";
import RatingForm from "./components/RatingForm";
import LoginForm from "./components/LoginForm";
import Footer from "./components/Footer";


const gameList = [
    { title: 'Numberlink', path: 'numberlink' },
];

function App() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [comments, setComments] = useState([]);
    const [scores, setScores] = useState([]);
    const [rating, setRating] = useState(0);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();
    const location = useLocation();

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
        navigate("/");
    };
    const handleLogout = () => {
        setUsername('');
        setIsLoggedIn(false);
    };
    return (
        <div>

            <Menu gameList={gameList} />

            <div className="container index-container">

                <Routes>
                    <Route index element={
                        <React.Fragment>

                                <h1 className="frontH1">Welcome to Gamestudio!</h1>
                                {username ? <p className="mainp">Hello {username}! Please select a game from the menu.</p> : <p className="mainp">Please log in to continue.</p>}
                            <div className="video-container">
                                <iframe className="video" src="https://www.youtube.com/embed/1XOdGDJp7SY" allowFullScreen></iframe>
                            </div>
                        </React.Fragment>
                    } />
                    <Route path={"scores"} element={
                        <React.Fragment>
                            <h1 className="scoreH1">Top Scores</h1>
                            <Scores scores={scores}/>
                        </React.Fragment>
                    }/>
                    <Route path="comments">
                        <Route index element={
                            <React.Fragment>
                                <h1 className="commentsH1">Comments</h1>
                                <Comments comments={comments} />
                                {username ? <div className="commentFloating"><button onClick={() => navigate("/comments/add")}><p className="addComment">Add Comment</p></button></div> : <p className="mainpC">Please log in to add a comment.</p>}
                            </React.Fragment>
                        } />
                        <Route path="add" element={
                            <React.Fragment>
                                <h2 className="addH2">Add comment:</h2>
                                {username ? <CommentForm onCommentSent={handleCommentSent} /> : <p className="mainpC">Please log in to add a comment.</p>}
                            </React.Fragment>
                        } />
                    </Route>
                    <Route path="rating">
                        <Route index element={
                            <React.Fragment>
                                <h1 className="ratingsH1">Average Rating</h1>
                                <Ratings rating={rating} />
                                {username ? <div className="ratingFloating"><button onClick={() => navigate("/rating/add")}><p className="addRating"> Add Rating</p></button></div> : <p className="mainpR">Please log in to add a rating.</p>}
                            </React.Fragment>
                        } />
                        <Route path="add" element={
                            <React.Fragment>
                                <h2 className="addH2">Add Rating: </h2>
                                {username ? <RatingForm onRatingSent={handleRatingSent} /> : <p className="mainpR">Please log in to add a rating.</p>}
                            </React.Fragment>
                        } />
                    </Route>
                </Routes>
            </div>
                <Routes>
                    <Route path="login" element={<LoginForm onLogin={handleLogin} />} />
                    <Route path="game/*" element={<GameLayout username={username} />} />
                </Routes>
            {isLoggedIn && <div className="floating-button"><button  onClick={handleLogout}><p className="logout">Logout</p></button></div>}
            <Footer/>
        </div>

    );

    function GameLayout({ username }) {
        return (
            <div className="container index-container">
                <Routes>
                    <Route path="numberlink" element={<NumberLinkGame playerName={username} />} />
                </Routes>
            </div>
        );
    }

}


export default App;




