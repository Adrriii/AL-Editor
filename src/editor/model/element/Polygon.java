package editor.model.element;

import java.util.ArrayList;
import java.util.HashMap;

import editor.application.App;
import editor.model.Element;
import editor.model.ElementProperty;
import editor.model.Position;
import editor.model.elementproperty.ColorProperty;

/**
* An Element that can be represented by an Array of points.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class Polygon extends Element {

    private static final long serialVersionUID = 1L;

    /**
     * A list of points defining the Polygon, relative to the top left of the figure.
     */
    public ArrayList<Position> points;

    /**
     * An internally processed list of the polygon's points relative to the window's top left.
     */
    public ArrayList<Position> real_points;

    /**
     * Constructs a new black Polygon
     * 
     * @param points A list of points relative to the top left of the figure.
     */
    public Polygon(ArrayList<Position> points) {
        super();
        properties.put("color",new ColorProperty(0,0,0,255));
        this.points = new ArrayList<>(points);
        RefreshArea();
    }

    /**
     * Constructs an empty black Polygon (0 sides)
     */
    public Polygon() {
        this(new ArrayList<>());
    }

    /**
     * Constructs a new Polygon according to a set of properties.
     * 
     * @param properties The properties the Polygon will take
     * @param points A list of points relative to the top left of the figure.
     */
    public Polygon(HashMap<String,ElementProperty> properties,ArrayList<Position> points ) {
        super(properties);

        this.points = new ArrayList<>(points);
        RefreshArea();
    }

    /**
     * Get the points defining this Polygon.
     * @return A list of points
     */
    public ArrayList<Position> getPoints() {
        return points;
    }

    /**
     * Reprocess the actual sizes of the polygon
     */
    private void RefreshArea() {
        int max_x = 0;
        int max_y = 0;

        for(Position p : points) {
            if(max_x < p.x + pos.x) max_x = p.x + pos.x;
            if(max_y < p.y + pos.y) max_y = p.y + pos.y;
        }

        width = max_x - pos.x;
        height = max_y - pos.y;
    }

    @Override
    public Element Clone() {
        Polygon newElement = new Polygon(properties, points);
        newElement.pos.x = pos.x;
        newElement.pos.y = pos.y;
        newElement.width = width;
        newElement.height = height;
        newElement.min_x = min_x;
        newElement.min_y = min_y;
        newElement.rotation = rotation;

        return newElement;
    }

    @Override
    public Position Update(Position new_pos, int width, int height) {
        Position n = super.Update(new_pos, width, height);
        RefreshArea();
        return n;
    }

    @Override
    public int getSurfaceWidth() {
        return width;
    }

    @Override
    public int getSurfaceHeight() {
        return height;
    }

    @Override
    public void Draw(String viewName, Position pos, int fit_width, int fit_height) {
        Draw(viewName, pos,Math.min(fit_width / (double) getSurfaceWidth(), fit_height / (double) getSurfaceHeight()));
    }
    
    @Override
    public boolean isClicked(int x, int y) {
        boolean result = false;
        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++) {
          if ((points.get(i).y > y - pos.y) != (points.get(j).y > y - pos.y) &&
              (x - pos.x < (points.get(j).x - points.get(i).x) * (y - pos.y - points.get(i).y) / (points.get(j).y-points.get(i).y) + points.get(i).x)) {
            result = !result;
           }
        }
        return result;
    }

    @Override
    public boolean intersects(int rx, int ry, int rwidth, int rheight) {
        if(isClicked(rx, ry) ||
            isClicked(rx + rwidth, ry) ||
            isClicked(rx, ry + rheight) ||
            isClicked(rx + rwidth, ry + rheight)) return true;

        for(Position p : points) {
            if(!(p.x + pos.x + width <= rx ||
                p.y + pos.y + height <= ry ||
                p.x + pos.x >= rx + rwidth || 
                p.y + pos.y >= ry + rheight)) return true;
        }
        return false;
    }

    @Override
    public void Draw(String viewName, Position pos, double scale) {
        if(isSelected()) {
            Polygon selecPoly = (Polygon) this.Clone();
            selecPoly.properties.put("color", new ColorProperty(0, 0, 255, 100));
            App.view.drawPolygon(viewName, selecPoly, pos.x - 5, pos.y - 5, scale*1.07);
        }
        
        App.view.drawPolygon(viewName, this, pos.x, pos.y, scale);
    }
}