package gen;

public class RoadNode {

    // Highway bez
    // Road bez
    // Street strict

    private Vector2 startPosition;
    private Vector2 endPosition;

    public RoadNode(float xStart, float yStart, float xEnd, float yEnd) {
        this.startPosition = new Vector2(xStart, yStart);
        this.endPosition = new Vector2(xEnd, yEnd);
    }

    public Vector2 getStartPosition() {
        return this.startPosition;
    }

    public Vector2 getEndPosition() {
        return this.endPosition;
    }
}
