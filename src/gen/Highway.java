package gen;

//https://stackoverflow.com/questions/26120657/java-object-to-double-hash-function-with-uniform-distribution-on-0-1?rq=1
// Use math.Random() with known seed

public class Highway extends RoadNode {
    private float control1XPos;
    private float control1YPos;
    private float control2XPos;
    private float control2YPos;

    public Highway(float xStart, float yStart, float xEnd, float yEnd) {
        super(xStart, yStart, xEnd, yEnd);

        float distance = (float)Math.sqrt(Math.pow(xEnd - xStart, 2) + Math.pow(yEnd - yStart, 2));

        float randomOffset = distance / 2.0f;

        this.control1XPos = this.getStartPosition().getX() + (this.getEndPosition().getX() - this.getStartPosition().getX()) * 0.33f + (randomOffset * (float)Math.random()) - randomOffset / 2.0f;
        this.control1YPos = this.getStartPosition().getY() + (this.getEndPosition().getY() - this.getStartPosition().getY()) * 0.33f + (randomOffset * (float)Math.random()) - randomOffset / 2.0f;
        this.control2XPos = this.getStartPosition().getX() + (this.getEndPosition().getX() - this.getStartPosition().getX()) * 0.66f + (randomOffset * (float)Math.random()) - randomOffset / 2.0f;
        this.control2YPos = this.getStartPosition().getY() + (this.getEndPosition().getY() - this.getStartPosition().getY()) * 0.66f + (randomOffset * (float)Math.random()) - randomOffset / 2.0f;
    }

    public float getControl1XPos() {
        return this.control1XPos;
    }

    public float getControl1YPos() {
        return this.control1YPos;
    }

    public float getControl2XPos() {
        return this.control2XPos;
    }

    public float getControl2YPos() {
        return this.control2YPos;
    }

    public Vector2 getPoint(float t)
    {
        float u = 1.0f - t;
        float tt = t * t;
        float uu = u * u;
        float uuu = uu * u;
        float ttt = tt * t;

        Vector2 p = new Vector2(uuu * this.getStartPosition().getX(), uuu * this.getStartPosition().getY());
        p.add(3 * uu * t * this.control1XPos, 3 * uu * t * this.control1YPos);
        p.add(3 * u * tt * this.control2XPos, 3 * u * tt * this.control2YPos);
        p.add(ttt * this.getEndPosition().getX(), ttt * this.getEndPosition().getY());

        return p;
    }
}
