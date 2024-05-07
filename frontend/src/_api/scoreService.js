import gsAxios from "./index";

export const fetchScores = game => gsAxios.get('/score/' + game);

