package gen;

//https://stackoverflow.com/questions/26120657/java-object-to-double-hash-function-with-uniform-distribution-on-0-1?rq=1
// Use math.Random() with known seed

public class Street extends RoadNode {

    public Street(float xStart, float yStart, float xEnd, float yEnd) {
        super(xStart, yStart, xEnd, yEnd);
    }
}
