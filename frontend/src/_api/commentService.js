import gsAxios from "./index";
import {format} from "date-fns";



export const fetchComments = game => gsAxios.get('/comment/' + game);

export const addComment = (game, player, comment, commented_on) => {
    return gsAxios.post('/comment', {
        game,
        player,
        comment,
        commented_on,
    });
}

