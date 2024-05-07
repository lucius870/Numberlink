import gsAxios from "./index";
import {formatDate} from "./utils";


export const fetchComments = game => gsAxios.get('/comment/' + game);

//post api/comment - comment
export const addComment = (game, player, comment) => gsAxios.post('/comment', {
    game, player, comment, commentedAt: formatDate(new Date()),
});


