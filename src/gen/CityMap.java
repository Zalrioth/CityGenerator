package gen;

import java.util.ArrayList;
import java.util.List;

public class CityMap {

    private List<CityNode> cityNodes = new ArrayList<>();
    private List<RoadNode> roadNodes = new ArrayList<>();
    private List<GrowthNode> growthNodes = new ArrayList<>();
    private List<IntersectionNode> intersectionNodes = new ArrayList<>();

    public CityMap() {

    }

    public void addCity(CityNode cityNode) {
        this.cityNodes.add(cityNode);
    }

    public List<CityNode> getCities() {
        return this.cityNodes;
    }

    public void addRoad(RoadNode roadNode) {
        this.roadNodes.add(roadNode);
    }

    public List<RoadNode> getRoads() {
        return this.roadNodes;
    }

    public void addGrowth(GrowthNode growthNode) {
        this.growthNodes.add(growthNode);
    }

    public List<GrowthNode> getGrowthNodes() {
        return this.growthNodes;
    }

    public void addIntersection(IntersectionNode intersectionNode) {
        this.intersectionNodes.add(intersectionNode);
    }

    public List<IntersectionNode> getIntersections() {
        return this.intersectionNodes;
    }

    public void clear() {
        this.cityNodes.clear();
        this.roadNodes.clear();
        this.growthNodes.clear();
        this.intersectionNodes.clear();
    }
}
