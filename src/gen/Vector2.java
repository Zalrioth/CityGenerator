package gen;

public class Vector2 {

    private float x;
    private float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public float distance(Vector2 vector) {
        return (float)Math.sqrt(Math.pow(vector.getX() - this.x, 2) + Math.pow(vector.getY() - this.y, 2));
    }
}
