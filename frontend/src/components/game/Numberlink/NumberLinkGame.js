import React, {useState} from 'react';
import './stylesheet.css';
const NumberLinkGame = ({ playerName }) => {
        const [size, setSize] = useState(null);

        const handleSizeChange = (newSize) => {
            setSize(newSize);
        };

        const handleButtonClick = (newSize) => {
            handleSizeChange(newSize);
        };

        const renderButtons = () => (
            <div className="size-buttons">
                <label className="size-label" htmlFor="size-select">Choose game field size:</label>
                <div>
                    <button onClick={() => handleButtonClick(6)}>6x6</button>
                    <button onClick={() => handleButtonClick(7)}>7x7</button>
                    <button onClick={() => handleButtonClick(8)}>8x8</button>
                    <button onClick={() => handleButtonClick(10)}>10x10</button>
                    <button onClick={() => handleButtonClick(11)}>11x11</button>
                    <button onClick={() => handleButtonClick(12)}>12x12</button>
                    <button onClick={() => handleButtonClick(13)}>13x13</button>
                </div>
            </div>
        );

        const renderGame = () => {
            const gameUrl = `http://localhost:8080/numberlink/grid/new?name=${playerName}&size=${size}`;
            return <iframe src={gameUrl} width="100%" height={780} name="gameWindow" />;
        };

        return (
            <div>
                {size ? renderGame() : renderButtons()}
            </div>
        );
    };

    export default NumberLinkGame;
