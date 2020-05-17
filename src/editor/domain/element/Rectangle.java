package editor.domain.element;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.Element;
import editor.domain.Position;
import editor.domain.elementproperty.ColorProperty;
import editor.domain.elementproperty.RoundedBorderProperty;
import editor.domain.menu.Interaction;
import editor.domain.menu.interactionmenus.*;

public class Rectangle extends Element {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Rectangle(int x, int y, int width, int height, int r, int g, int b, int a, double w, double h) {
        super();
        
        this.pos.x = x;
        this.pos.y = y;
        this.width = width;
        this.height = height;

        properties.put("color",new ColorProperty(r,g,b,a));
        properties.put("roundedborders",new RoundedBorderProperty(h,w));
    }

    public Rectangle(int x, int y, int width, int height, int r, int g, int b, int a) {
        this(x,y,width,height,r,g,b,a,0,0);
    }

    public Rectangle(int width, int height, int r, int g, int b, int a) {
        this(0,0,width,height,r,g,b,a);
    }

    public Rectangle(int x, int y, int width, int height, int r, int g, int b) {
        this(x,y,width,height,r,g,b,255);
    }

    public Rectangle(int width, int height, int r, int g, int b) {
        this(0,0,width,height,r,g,b,255);
    }

    public Rectangle(int x, int y, int width, int height) {
        this(x,y,width,height,0,0,0,255);
    }

    public Rectangle(int width, int height) {
        this(0,0,width,height,0,0,0,255);
    }

    public ColorProperty getColorProperty() {
        return (ColorProperty) properties.get("color");
    }


    @Override
    public Rectangle Clone() {
        ColorProperty colors = getColorProperty();
        Rectangle newElement = new Rectangle(pos.x, pos.y, width, height, 
            colors.r, colors.g, colors.b, colors.a,
            ((RoundedBorderProperty) properties.get("roundedborders")).w,
            ((RoundedBorderProperty) properties.get("roundedborders")).h);
            
        newElement.min_x = min_x;
        newElement.min_y = min_y;
        newElement.rotation = rotation;
        return newElement;
    }

    @Override
    public ArrayList<Interaction> getAvailableInteractions() {
        ArrayList<Interaction> list = super.getAvailableInteractions();
        
        list.add(new DimensionChangeInteraction(this));
        list.add(new BordersChangeInteration(this));

        return list;
    }

    @Override
    public void Draw(Position pos, int fit_width, int fit_height) {
        Draw(pos,Math.min(fit_width / (double) getSurfaceWidth(), fit_height / (double) getSurfaceHeight()));
    }

    @Override
    public void Draw(Position pos , double scale) {

        if(isSelected()) {
            int selectionRectangleX = Math.max(App.model.getCanvas().pos.x,pos.x-5);
            int selectionRectangleY = Math.max(App.model.getCanvas().pos.y,pos.y-5);
            
            Rectangle selecRec = new Rectangle(
                pos.x, 
                pos.y, 
                width + 10 + Math.min(0,pos.x-App.model.getCanvas().pos.x-5), 
                height + 10 + Math.min(0,pos.y-App.model.getCanvas().pos.y-5), 0, 0, 255, 100,
                ((RoundedBorderProperty) properties.get("roundedborders")).w,
                ((RoundedBorderProperty) properties.get("roundedborders")).h);

            selecRec.rotation = rotation;

            App.view.drawRectangle(
                selecRec,
                selectionRectangleX, 
                selectionRectangleY
            );
        }

        App.view.drawRectangle(this, pos.x, pos.y, scale < 1 ? scale : 1);

    }
    
    @Override
    public boolean isClicked(int x, int y) {
        return x > pos.x && x < pos.x + width && y > pos.y && y < pos.y + height;
    }

    @Override
    public int getSurfaceWidth() {
        return width;
    }

    @Override
    public int getSurfaceHeight() {
        return height;
    }
}