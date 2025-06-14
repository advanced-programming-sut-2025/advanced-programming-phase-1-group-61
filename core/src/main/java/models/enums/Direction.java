package models.enums;

public enum Direction {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,-1),
    BOTTOM(0,1),
    UP_RIGHT(1,-1),
    BOTTOM_RIGHT(1,1),
    BOTTOM_LEFT(-1,1),
    UP_LEFT(-1,-1);
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
            case "up_right" -> Direction.UP_RIGHT;
            case "up_left" -> Direction.UP_LEFT;
            case "bottom_right" -> Direction.BOTTOM_RIGHT;
            case "bottom_left" -> Direction.BOTTOM_LEFT;
            default -> null;
        };
    }
}
