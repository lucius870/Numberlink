/*import GridClass from "./GridClass";

class FieldClass {
    constructor(board, rowCount, columnCount) {
        this.board = board;
        this.state = 'PLAYING';
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.hints = Math.floor(rowCount * columnCount / 4);
    }

    getBoard() {
        return this.board;
    }

    getMaxPathNumber() {
        let max = 0;
        for (let row = 0; row < this.rowCount; row++) {
            for (let col = 0; col < this.columnCount; col++) {
                if (max < this.board[row][col].pathNumber) {
                    max = this.board[row][col].pathNumber;
                }
            }
        }

        return max;
    }

    markPath(row, col, input) {
        if (input === 0) {
            this.board[row][col].isEndpoint = false;
            this.board[row][col].setState('OPEN');
        } else {
            this.board[row][col].isEndpoint = true;
            this.board[row][col].setState('MARKED');
        }
    }

    isSolved() {
        for (let row = 0; row < this.rowCount; row++) {
            for (let col = 0; col < this.columnCount; col++) {
                if (this.board[row][col].pathNumber === 0) {
                    continue;
                }
                if (this.board[row][col].isEndpoint === false) {
                    return false;
                }
            }
        }
        this.state = 'SOLVED';
        return true;
    }

    makeHint() {
        const rand = Math.floor(Math.random() * Math.floor(this.rowCount * this.columnCount));
        const row = Math.floor(rand / this.columnCount);
        const col = rand % this.columnCount;
        if (this.board[row][col].isEndpoint === true || this.board[row][col].pathNumber === 0) {
            this.makeHint();
            return;
        }
        this.board[row][col].isEndpoint = true;
        this.board[row][col].setState('HINTED');
        this.hints--;
    }

    getHints() {
        return this.hints;
    }

    getRowCount() {
        return this.rowCount;
    }

    setRowCount(rowCount) {
        this.rowCount = rowCount;
    }

    getColumnCount() {
        return this.columnCount;
    }

    setColumnCount(columnCount) {
        this.columnCount = columnCount;
    }

    getState() {
        return this.state;
    }

    getScore() {
        let score = 0;
        for (let row = 0; row < this.rowCount; row++) {
            for (let col = 0; col < this.columnCount; col++) {
                if (this.board[row][col].getState() === 'MARKED') {
                    score += 10;
                }
                if (this.board[row][col].getState() === 'HINTED') {
                    score -= 5;
                }
            }
        }
        return score;
    }
}

export default FieldClass;*/