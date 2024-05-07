/*class LevelGenerator {
findSet(grid) {
    if (grid.parent !== null) {
        grid = this.findSet(grid.parent);
    }
    return grid;
}

setUnion(x, y) {
    const rootX = this.findSet(x);
    const rootY = this.findSet(y);
    if (rootX === rootY) {
        return;
    }
    if (rootX.rank > rootY.rank) {
        rootY.parent = rootX;
    } else {
        rootX.parent = rootY;
        if (rootX.rank === rootY.rank) {
            rootY.rank++;
        }
    }
}

checkNeighbours(board, rowA, colA, rowB, colB, length) {
    let count = 0;
    if (rowB + 1 < length && this.findSet(board[rowB + 1][colB]) === this.findSet(board[rowA][colA])) {
        count++;
    }
    if (rowB - 1 >= 0 && this.findSet(board[rowB - 1][colB]) === this.findSet(board[rowA][colA])) {
        count++;
    }
    if (colB + 1 < length && this.findSet(board[rowB][colB + 1]) === this.findSet(board[rowA][colA])) {
        count++;
    }
    if (colB - 1 >= 0 && this.findSet(board[rowB][colB - 1]) === this.findSet(board[rowA][colA])) {
        count++;
    }

    return count <= 1;
}

addPath(board, length) {
    const rand = Math.floor(Math.random() * Math.floor(length * length));
    let rowA, colA, rowB, colB;

    rowA = Math.floor(rand / length);
    colA = rand % length;

    const dx = [0, 0, 1, -1];
    const dy = [1, -1, 0, 0];

    rowB = -1;
    colB = -1;

    let count = 0;

    // Find a valid starting point
    while (true) {
        if (this.findSet(board[rowA][colA]).rank === 0) {
            let order = Math.floor(Math.random() * 4);
            for (let o = 0; o < 4; o++) {
                const ni = rowA + dx[order];
                const nj = colA + dy[order];

                if (ni >= 0 && ni < length && nj >= 0 && nj < length && this.findSet(board[ni][nj]).rank === 0) {
                    rowB = ni;
                    colB = nj;
                    break;
                }
                order = (order + 1) % 4;
            }

            if (rowB !== -1 && colB !== -1) {
                break;
            }
        }

        count++;
        if (count >= length * length) {
            return false;
        }

        colA = (colA + 1) % length;
        if (colA === 0) {
            rowA = (rowA + 1) % length;
        }
    }

    this.setUnion(board[rowA][colA], board[rowB][colB]);
    board[rowA][colA].isEndpoint = true;
    board[rowA][rowB].setState('OPEN');

    // Extend the path
    while (true) {
        rowA = rowB;
        colA = colB;

        let status = 0;
        let order = Math.floor(Math.random() * 4);
        for (let o = 0; o < 4; o++) {
            const ni = rowA + dx[order];
            const nj = colA + dy[order];if (ni >= 0 && ni < length && nj >= 0 && nj < length && this.findSet(board[ni][nj]).rank === 0 &&
                this.checkNeighbours(board, rowA, colA, ni, nj, length)) {
                rowB = ni;
                colB = nj;
                status = 1;
                break;
            }

            order = (order + 1) % 4;
        }

        if (status === 0) {
            break;
        }

        this.setUnion(board[rowA][colA], board[rowB][colB]);
    }

    board[rowB][colB].isEndpoint = true;
    board[rowA][rowB].setState('OPEN');
    return true;
}

assignPathNumbers(board, n) {
    let pathNum = 1;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (this.findSet(board[i][j]).rank !== 0) {
                if (this.findSet(board[i][j]).pathNumber === 0) {
                    this.findSet(board[i][j]).pathNumber = pathNum;
                    board[i][j].pathNumber = pathNum;
                    pathNum++;
                } else {
                    board[i][j].pathNumber = this.findSet(board[i][j]).pathNumber;
                }
            }
        }
    }
}
}*/