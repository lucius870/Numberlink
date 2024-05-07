/*import React, { useState, useEffect } from "react";
import fieldService from "./_api/fieldService";
import FieldClass from "./FieldClass";
import FieldComponent from "./FieldComponent";
import GridClass from "./GridClass";
import LevelGenerator from "./LevelGenerator";

function NumberlinkReact({ playerName }) {
    const [selectedNumber, setSelectedNumber] = useState(null);
    const [field, setField] = useState(null);
    const [length, setLength] = useState(0);

    const handleSelectNumber = (number) => {
        setSelectedNumber(number);
    };

    useEffect(() => {
        fieldService.fetchField().then((response) => {
            setField(new FieldClass(response.data.board, response.data.rowCount, response.data.columnCount));
        });
    }, []);

    const onMarkGrid = (row, col, number) => {
        fieldService.markGrid(row, col, number);
    };

    const onHintGrid = (row, col) => {
        fieldService.hintGrid();
    };

    const generateField = () => {
        const n = parseInt(length, 10);
        const board = Array.from({ length: n }, () =>
            Array.from({ length: n }, () => new GridClass())
        );

        const levelGenerator = new LevelGenerator();

        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                board[i][j] = new GridClass();
            }
        }

        const field = new FieldClass(board, n, n);

        while (levelGenerator.addPath(board, n)) {}

        levelGenerator.assignPathNumbers(board, n);
        setField(field);
    };

    return (
        <div className="App">
            <FieldComponent
                field={field}
                onSelectNumber={handleSelectNumber}
                onMarkGrid={onMarkGrid}
                onHintGrid={onHintGrid}
                selectedNumber={selectedNumber}
            />
            <button onClick={generateField}>Generate Field</button>
            <input type="number" value={length} onChange={(e) => setLength(e.target.value)} />
        </div>
    );
}

export default NumberlinkReact;*/