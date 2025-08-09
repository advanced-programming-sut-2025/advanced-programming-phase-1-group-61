package models.enums;

public enum Direction {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    BOTTOM(0,-1);

    private final int dx;
    private final int dy;
    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public int getDx() {
        return dx;
    }
    public int getDy() {
        return dy;
    }
    public static Direction fromString(String direction) {
        return switch (direction) {
            case "left" -> Direction.LEFT;
            case "right" -> Direction.RIGHT;
            case "up" -> Direction.UP;
            case "down" -> Direction.BOTTOM;
            default -> null;
        };
    }
}
