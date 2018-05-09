package gen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;

//https://stackoverflow.com/questions/15728230/for-a-method-like-filloval-what-does-x-and-y-denote-what-coordinates-are-t
//http://www.dreamincode.net/forums/topic/26502-drawing-bezier-curves-with-java/
//https://stackoverflow.com/questions/36593388/how-to-get-points-of-a-curve-shape
//http://devmag.org.za/2011/04/05/bzier-curves-a-tutorial/
// Works great find point on curved line
// Also could maybe implement Bezier path in future
//https://stackoverflow.com/questions/14886848/keyboard-input-using-jframe-and-keyadapter

public class DrawWindow extends JFrame {
    CityGenerator cityGenerator;

    DrawWindow(String title, CityGenerator cityGenerator) {
        this.setSize(500, 500);
        setTitle(title);
        addKeyListener(new AL());

        this.cityGenerator = cityGenerator;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);

        g.setColor(Color.black);
        Rectangle r = new Rectangle(100, 100, 300, 300);
        g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());

        for (CityNode city : this.cityGenerator.getCityMap().getCities()) {
            float circleRadius = 10.0f;

            g.setColor(Color.YELLOW);
            g.fillOval((int) (city.getPosition().getX() - circleRadius), (int) (city.getPosition().getY() - circleRadius), (int) (circleRadius * 2), (int) (circleRadius * 2));

            g.setColor(Color.BLUE);
            g.drawString(city.getName(), (int) city.getPosition().getX(), (int) city.getPosition().getY());
        }

        for (RoadNode road : this.cityGenerator.getCityMap().getRoads()) {
            if (road instanceof Highway) {
                // Line to each point

                //Vector2 testPoint = ((Highway) road).getPoint(0.5f);

                //g.setColor(Color.CYAN);
                //g.fillRect((int) testPoint.getX(), (int) testPoint.getY(), 5, 5);

                CubicCurve2D.Float cubicCurve;
                cubicCurve = new CubicCurve2D.Float(road.getStartPosition().getX(), road.getStartPosition().getY(), ((Highway) road).getControl1XPos(), ((Highway) road).getControl1YPos(),
                        ((Highway) road).getControl2XPos(), ((Highway) road).getControl2YPos(), road.getEndPosition().getX(), road.getEndPosition().getY());

                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.draw(cubicCurve);
            }
            else if (road instanceof Street) {
                g.setColor(Color.BLUE);
                g.drawLine((int)road.getStartPosition().getX(), (int)road.getStartPosition().getY(), (int)road.getEndPosition().getX(), (int)road.getEndPosition().getY());
            }
        }

        for (GrowthNode growth : this.cityGenerator.getCityMap().getGrowthNodes()) {
            float growthDrawSize = 5.0f;

            g.setColor(Color.RED);
            g.fillOval((int)(growth.getPosition().getX() - growthDrawSize / 2.0f), (int)(growth.getPosition().getY() - growthDrawSize / 2.0f), (int)growthDrawSize, (int)growthDrawSize);
        }

        for (IntersectionNode intersection : this.cityGenerator.getCityMap().getIntersections()) {
            float growthDrawSize = 2.5f;

            g.setColor(Color.CYAN);
            g.fillOval((int)(intersection.getPosition().getX() - growthDrawSize / 2.0f), (int)(intersection.getPosition().getY() - growthDrawSize / 2.0f), (int)growthDrawSize, (int)growthDrawSize);
        }
    }

    public class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
        }

        @Override
        public void keyReleased(KeyEvent event) {
            int keyCode = event.getKeyCode();
            if (keyCode == event.VK_G)
            {
                cityGenerator.clear();
                cityGenerator.generateCities((int)(Math.random() * 2.0f) + 4);
                cityGenerator.generateRoads();

                repaint();
            }
        }
    }
}
