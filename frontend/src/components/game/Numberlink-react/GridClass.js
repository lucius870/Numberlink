/*import React from "react";

class GridClass {
    constructor() {
        this.parent = this;
        this.rank = 0;
        this.pathNumber = 0;
        this.isEndpoint = false;
        this.state = null;
    }

    getPathNumber() {
        return this.pathNumber;
    }

    isEndpoint() {
        return this.isEndpoint;
    }

    setEndpoint(endpoint) {
        this.isEndpoint = endpoint;
    }

    setPathNumber(pathNumber) {
        this.pathNumber = pathNumber;
    }

    getState() {
        return this.state;
    }

    setState(state) {
        this.state = state;
    }
}

const Grid = ({ grid, onMarkGrid, onSelectNumber, selectedNumber }) => {
    const gridClass =
        "numberlink" +
        (grid.state === "OPEN" && grid.pathNumber === undefined
            ? ""
            : ` ${grid.state.toLowerCase()}`);

    const handleClick = () => {
        if (grid.state === "OPEN" && grid.pathNumber !== undefined) {
            onSelectNumber(grid.pathNumber);
        } else if (grid.state === "OPEN" && selectedNumber !== null) {
            onMarkGrid(grid.row, grid.col, selectedNumber);
        } else if (grid.state === "HINTED") {
            onSelectNumber(grid.pathNumber);
        }
    };

    const handleRightClick = (event) => {
        event.preventDefault();
        if (grid.state === "OPEN" && selectedNumber !== null) {
            onSelectNumber(selectedNumber);
        }
    };

    return (
        <td className={gridClass} onClick={handleClick} onContextMenu={handleRightClick}>
            <span>{grid.pathNumber}</span>
        </td>
    );
};

export default Grid;

*/