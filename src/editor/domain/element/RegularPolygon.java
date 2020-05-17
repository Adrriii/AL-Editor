package editor.domain.element;

import java.util.ArrayList;
import java.util.HashMap;

import editor.domain.Element;
import editor.domain.Position;
import editor.domain.elementproperty.SidesCountProperty;
import editor.domain.menu.Interaction;
import editor.domain.menu.interactionmenus.SidesCountChangeInteraction;

public class RegularPolygon extends Polygon {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegularPolygon(int radius, int sides) {
        super();
        properties.put("sidescount", new SidesCountProperty(sides));
        this.width = radius * 2;
        this.height = radius * 2;
        UpdateSides();
    }

    @Override
    public Element Clone() {
        RegularPolygon newpoly = new RegularPolygon(width/2, ((SidesCountProperty) properties.get("sidescount")).count);
        newpoly.properties = new HashMap<>(properties);
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

    public void UpdateSides() {
        int count = ((SidesCountProperty) properties.get("sidescount")).count;

        // inspired from https://stackoverflow.com/a/49550853
        double angleStep = Math.PI * 2 / (double) count;
        double angle = 0; // assumes one point is located directly beneat the center point
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