import React from 'react';
const Ratings = ({ rating }) => {
    if (rating === null || rating === undefined) {
        return <div>Error: Ratings data is not available</div>;
    }

    return (
        <div>
            <h2>Average Rating</h2>
            <div>
                <p>Rating: {rating}</p>
            </div>
        </div>
    );
};

export default Ratings;