package models.character;

public class Cell {
    private int x;
    private int y;
    private Cell previousCell;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Cell getPreviousCell() {
        return previousCell;
    }
    public Cell setX(int x) {
        this.x = x;
        return this;
    }
    public Cell setY(int y) {
        this.y = y;
        return this;
    }
    public Cell setPreviousCell(Cell previousCell) {
        this.previousCell = previousCell;
        return this;
    }
}
