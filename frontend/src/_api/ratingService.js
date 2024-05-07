import gsAxios from "./index";
import { formatDate } from "./utils";

export const fetchRatings = game => {
    return gsAxios.get(`/rating/${game}`)
        .then(response => {
            if (typeof response.data === 'number') {
                // If response.data is a number, return it directly
                return response.data;
            } else {
                throw new Error("Invalid ratings data format");
            }
        })
        .catch(error => {
            console.error('Error fetching ratings:', error);
            throw error; // Rethrow the error to be handled by the caller
        });
};

export const addRating = (game, player, rating) =>
    gsAxios.post("/rating", {
        game,
        player,
        rating,
        ratedAt: formatDate(new Date()),
    });