import React from 'react';
import './stylesheet.css';
const Ratings = ({ rating }) => {
    if (rating === null || rating === undefined) {
        return <div>Error: Ratings data is not available</div>;
    }

    return (

            <div className="rating">
                <p className="ratingP">Rating: {rating}</p>
            </div>

    );
};

export default Ratings;