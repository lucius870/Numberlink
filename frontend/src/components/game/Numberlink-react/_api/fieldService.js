/*import gsAxios from "../../../../_api";

const Grid_URL = '/numberlink/grid';
const NEW_GAME_URL = `${Grid_URL}/newGame`;
const MARK_Grid_URL = `${Grid_URL}/mark`;
const Hint_Grid_URL = `${Grid_URL}/hint`;

const fetchField = () => gsAxios.get(Grid_URL);
const newGame = (rows, cols, numberlink) =>
    gsAxios.post(`${NEW_GAME_URL}`, { rows, cols, numberlink });
const markGrid = (row, col, number) =>
    gsAxios.post(`${MARK_Grid_URL}`, { row, col, num: number });
const hintGrid = () => gsAxios.post(Hint_Grid_URL);

const fieldService = { fetchField, newGame, markGrid, hintGrid };
export default fieldService;*/