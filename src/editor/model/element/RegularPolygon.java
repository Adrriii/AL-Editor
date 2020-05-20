package editor.model.element;

import java.util.ArrayList;
import java.util.HashMap;

import editor.model.Element;
import editor.model.Position;
import editor.model.elementproperty.SidesCountProperty;
import editor.model.menu.Interaction;
import editor.model.menu.interactionmenus.SidesCountChangeInteraction;

/**
* A Polygon with a number of sides equal by length.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class RegularPolygon extends Polygon {

    private static final long serialVersionUID = 1L;

    /**
     * Create a new POlygon with equal sides
     * 
     * @param radius The radius of the polygon
     * @param sides The amount of sides to generate
     */
    public RegularPolygon(int radius, int sides) {
        super();
        properties.put("sidescount", new SidesCountProperty(sides));
        this.width = radius * 2;
        this.height = radius * 2;
        UpdateSides();
    }

    @Override
    public Element Clone() {
        RegularPolygon newpoly = new RegularPolygon(width/2, ((SidesCountProperty) properties.get("sidescount")).value);
        newpoly.properties = new HashMap<>(properties);
        newpoly.pos.x = pos.x;
        newpoly.pos.y = pos.y;
        newpoly.min_x = min_x;
        newpoly.min_y = min_y;
        newpoly.rotation = rotation;
        return newpoly;
    }

    @Override
    public Position Update(Position new_pos, int width, int height) {
        Position n = super.Update(new_pos, width, height);
        this.width = Math.min(width,height);
        this.height = width;
        UpdateSides();
        return n;
    }

    /**
     * Update the points array of this polygon according to the sidescount property
     */
    public void UpdateSides() {
        int count = ((SidesCountProperty) properties.get("sidescount")).value;

        // inspired from https://stackoverflow.com/a/49550853
        double angleStep = Math.PI * 2 / (double) count;
        double angle = 0; // assumes one point is located directly beneath the center point
        getPoints().clear();
        for (int i = 0; i < count; i++, angle += angleStep) {
            getPoints().add(new Position(
                    (int) (Math.sin(angle) * width/2 + width/2), // x coordinate of the corner
                    (int) (Math.cos(angle) * width/2 + width/2) // y coordinate of the corner
                    )
            );
        }
    }

    @Override
    public ArrayList<Interaction> getAvailableInteractions() {
        ArrayList<Interaction> list = super.getAvailableInteractions();
        
        list.add(new SidesCountChangeInteraction(this));
        
        return list;
    }
}