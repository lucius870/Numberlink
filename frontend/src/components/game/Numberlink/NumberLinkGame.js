
const NumberLinkGame = ({ playerName }) => {
    const gameUrl = `http://localhost:8080/numberlink/grid/new?name=${playerName}`;

    return (
        <div>
            {/* Use the constructed URL in the iframe src */}
            <iframe src={gameUrl} width="100%" height={780} name="gameWindow" />
        </div>
    );
};
export default NumberLinkGame;