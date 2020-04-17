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

        double scale = 1;
        
        if(fit_width >= 0) {
            scale = fit_width / (double) getSurfaceWidth();
        }
        if(fit_height >= 0) {
            scale = Math.min(scale, fit_height / (double) getSurfaceHeight());
        }

        if(isSelected()) {
            App.view.drawRectangle(new Rectangle(x, y, width + 10, height + 10, 0, 0, 255, 100),pos_x-5, pos_y-5);
        }

        App.view.drawRectangle(this, x, y, scale < 1 ? scale : 1);

    }
}