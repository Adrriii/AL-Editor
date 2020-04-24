package editor.domain.element;

import editor.application.App;
import editor.domain.elementproperty.ColorProperty;

public class Rectangle extends Polygon {

    public Rectangle(int x, int y, int width, int height, int r, int g, int b, int a) {
        super();
        
        this.pos_x = x;
        this.pos_y = y;
        this.width = width;
        this.height = height;

        properties.put("color",new ColorProperty(r,g,b,a));
    }

    public Rectangle(int x, int y, int width, int height, int r, int g, int b) {
        this(x,y,width,height,r,g,b,255);
    }

    public Rectangle(int width, int height, int r, int g, int b, int a) {
        this(0,0,width,height,r,g,b,a);
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

    @Override
    public Rectangle Clone() {
        ColorProperty colors = getColorProperty();
        Rectangle newElement = new Rectangle(pos_x, pos_y, width, height, colors.r, colors.g, colors.b, colors.a);
        return newElement;
    }

    @Override
    public void Draw(int x, int y, int fit_width, int fit_height) {
        Draw(x,y,Math.min(fit_width / (double) getSurfaceWidth(), fit_height / (double) getSurfaceHeight()));
    }

    @Override
    public void Draw(int x, int y, double scale) {

        if(isSelected()) {
            int selectionRectangleX = Math.max(App.model.getCanvas().pos_x,x-5);
            int selectionRectangleY = Math.max(App.model.getCanvas().pos_y,y-5);
            
            App.view.drawRectangle(new Rectangle(x, y, width + 10 + Math.min(0,x-App.model.getCanvas().pos_x-5), height + 10 + Math.min(0,y-App.model.getCanvas().pos_y-5), 0, 0, 255, 100),selectionRectangleX, selectionRectangleY);
        }

        App.view.drawRectangle(this, x, y, scale < 1 ? scale : 1);

    }
}