/*import FieldClass from "./FieldClass";
import Grid from "./GridClass";

function FieldComponent({ field, onMarkGrid, onSelectNumber, selectedNumber }) {
    if (!field) {
        return null;
    }

    const handleHintGrid = () => {
        if (field.getHints() > 0) {
            field.makeHint();
        }
    };

    const handleMarkGrid = (row, col, number) => {
        field.markPath(row, col, number);
    };

    const handleSelectNumber = (number) => {
        if (selectedNumber === number) {
            selectedNumber = null;
        } else {
            selectedNumber = number;
        }
    };

    return (
        <table className={"numberlink"}>
            <tbody>
            {field.getBoard().map((row, rowIndex) => (
                <tr key={`row-${rowIndex}`}>
                    {row.map((grid, colIndex) => (
                        <Grid
                            key={`grid-${rowIndex}-${colIndex}`}
                            grid={grid}
                            onHintGrid={handleHintGrid}
                            onMarkGrid={handleMarkGrid}
                            onSelectNumber={handleSelectNumber}
                            selectedNumber={selectedNumber}
                        />
                    ))}
                </tr>
            ))}
            </tbody>
        </table>
    );
}

export default FieldComponent;*/