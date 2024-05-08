import React, {useState} from 'react';
import './game.css';
const NumberLinkGame = ({ playerName }) => {
        const [size, setSize] = useState(null);

        const handleSizeChange = (newSize) => {
            setSize(newSize);
        };

        const handleButtonClick = (newSize) => {
            handleSizeChange(newSize);
        };

        const renderButtons = () => (
            <div >
                <label className="size-label" htmlFor="size-select"></label>
                <h1 className="gamefield">Choose game field size:</h1>
                <div className="size-buttons">
                    <button className="sizes" onClick={() => handleButtonClick(4)}><p className="pSize">4x4</p></button>
                    <button className="sizes" onClick={() => handleButtonClick(5)}><p className="pSize">5x5</p></button>
                    <button className="sizes" onClick={() => handleButtonClick(6)}><p className="pSize">6x6</p></button>
                    <button className="sizes" onClick={() => handleButtonClick(7)}><p className="pSize">7x7</p></button>
                    <button className="sizes" onClick={() => handleButtonClick(8)}><p className="pSize">8x8</p></button>
                    <button className="sizes" onClick={() => handleButtonClick(10)}><p className="pSize">9x9</p></button>
                    <button className="sizes" onClick={() => handleButtonClick(11)}><p className="pSize">10x10</p></button>

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
