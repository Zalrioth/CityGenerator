package gen;

import javax.swing.*;

//http://forum.codecall.net/topic/56826-hello-world-gui/

public class Main{
    public static void main(String[] args)
    {
        CityGenerator cityGenerator = new CityGenerator(100, 100, 300, 300);
        cityGenerator.generateCities(5);
        cityGenerator.generateRoads();

        DrawWindow drawWindow = new DrawWindow("City Gen", cityGenerator);
        drawWindow.setVisible(true);
        drawWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
