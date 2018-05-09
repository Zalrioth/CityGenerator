package gen;

public class CityNode extends NucleiNode{

    private String name;

    public CityNode(Vector2 position, String name) {
        super(position);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
