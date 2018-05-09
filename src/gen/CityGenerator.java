package gen;

import java.util.ArrayList;
import java.util.List;

//https://stackoverflow.com/questions/30702904/might-not-have-been-initialized-error-at-null-check
// Initialize to null

public class CityGenerator {

    private float xPos;
    private float yPos;
    private float width;
    private float height;

    private CityMap cityMap;

    public CityGenerator(float xPos, float yPos, float width, float height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public void generateCities(int n) {
        cityMap = new CityMap();

        float degreeSpace = (float) (2.0f * Math.PI) / (float) n;

        for (int loopNum = 0; loopNum < n; loopNum++) {

            // Perturb a small amount for variation
            float angle = loopNum * degreeSpace + ((float)Math.random() * 0.5f);

            Vector2 cityPosition = new Vector2((this.xPos + this.width / 2) + (float) Math.cos(angle) * (this.width / 2),
                    (this.yPos + this.height / 2) + (float) Math.sin(angle) * (this.height / 2));

            CityNode addCity = new CityNode(cityPosition, "City: " + loopNum);

            cityMap.addCity(addCity);
        }
    }

    public void generateRoads() {

        // Initial road across the map
        int gotoNode = this.cityMap.getCities().size() / 2;

        float startX = this.cityMap.getCities().get(0).getPosition().getX();
        float startY = this.cityMap.getCities().get(0).getPosition().getY();
        float endX = this.cityMap.getCities().get(gotoNode).getPosition().getX();
        float endY = this.cityMap.getCities().get(gotoNode).getPosition().getY();

        RoadNode highway = new Highway(startX, startY, endX, endY);

        this.cityMap.addRoad(highway);

        // Create a new city road branch connection to the main road
        for (int loopNum = 0; loopNum < this.cityMap.getCities().size(); loopNum++)
        {
            if (loopNum != gotoNode && loopNum != 0)
            {
                Vector2 randomPoint = ((Highway) highway).getPoint((0.2f * (float)Math.random()) + 0.4f);

                float newStartX = this.cityMap.getCities().get(loopNum).getPosition().getX();
                float newStartY = this.cityMap.getCities().get(loopNum).getPosition().getY();
                float newEndX = randomPoint.getX();
                float newEndY = randomPoint.getY();

                RoadNode newRoad = new Highway(newStartX, newStartY, newEndX, newEndY);
                this.cityMap.addRoad(newRoad);

                GrowthNode newGrowth = new GrowthNode(new Vector2(newEndX, newEndY));
                cityMap.addGrowth(newGrowth);

                IntersectionNode newIntersection = new IntersectionNode(new Vector2(newEndX, newEndY));
                cityMap.addIntersection(newIntersection);
            }
        }

        addGenerations(1);
    }

    public void addGenerations(int generations) {
        for (int generation = 0; generation < generations; generation++) {
            List<GrowthNode> newGrowth = new ArrayList<>();
            for (GrowthNode growth : this.cityMap.getGrowthNodes()) {
                float randomRot = 2.0f * (float) (Math.PI * Math.random());
                Vector2 randomLocation = new Vector2(growth.getPosition().getX() + (float) Math.cos(randomRot) * 20,
                        growth.getPosition().getY() + (float) Math.sin(randomRot) * 20);

                GrowthNode secondGrowth = new GrowthNode(randomLocation);
                newGrowth.add(secondGrowth);

                IntersectionNode newIntersection = new IntersectionNode(new Vector2(randomLocation));
                cityMap.addIntersection(newIntersection);
            }
            cityMap.getGrowthNodes().addAll(newGrowth);

            List<IntersectionNode> newIntersections = new ArrayList<>();
            for (IntersectionNode intersection : this.cityMap.getIntersections()) {
                Vector2 newIntersectionPoint = new Vector2(intersection.getPosition().getX(), intersection.getPosition().getY() + 20);
                float distance = Float.MAX_VALUE;
                float maxDistance = 25;
                IntersectionNode connectTo = null;

                for (IntersectionNode checkIntersection : this.cityMap.getIntersections()) {
                    if (intersection != checkIntersection)
                    {
                        float checkDistance = intersection.getPosition().distance(checkIntersection.getPosition());

                        // TODO: Use law of cosines to check angle constraint
                        if (checkDistance < distance && checkDistance < maxDistance) {
                            distance = checkDistance;
                            connectTo = checkIntersection;
                        }
                    }
                }

                if (connectTo != null) {
                    RoadNode newStreet = new Street(intersection.getPosition().getX(), intersection.getPosition().getY(),
                            connectTo.getPosition().getX(), connectTo.getPosition().getY());
                    cityMap.addRoad(newStreet);

                    IntersectionNode newIntersection = new IntersectionNode(new Vector2(connectTo.getPosition()));
                    newIntersections.add(newIntersection);
                }
                else
                {
                    RoadNode newStreet = new Street(intersection.getPosition().getX(), intersection.getPosition().getY(),
                            newIntersectionPoint.getX(), newIntersectionPoint.getY());
                    cityMap.addRoad(newStreet);

                    IntersectionNode newIntersection = new IntersectionNode(newIntersectionPoint);
                    newIntersections.add(newIntersection);
                }
            }
            cityMap.getIntersections().addAll(newIntersections);
        }
    }

    public CityMap getCityMap() {
        return this.cityMap;
    }

    public void clear()
    {
        this.cityMap.clear();
    }
}