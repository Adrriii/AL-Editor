package editor.domain.element;

import editor.domain.elementproperty.ColorProperty;

public class Rectangle extends Polygon {

    public Rectangle(int x, int y, int width, int height, int r, int g, int b, int a) {
        super();
        
        this.pos_x = 0;
        this.pos_y = 0;
        this.width = 100;
        this.height = 150;

        properties.put("color",new ColorProperty(255,100,0,255));
    }

    public Rectangle(int x, int y, int width, int height, int r, int g, int b) {
        this(x,y,width,height,r,g,b,255);
    }

    public Rectangle(int x, int y, int width, int height) {
        this(x,y,width,height,0,0,0,255);
    }

    public Rectangle(int width, int height) {
        this(0,0,width,height,0,0,0,255);
    }
}