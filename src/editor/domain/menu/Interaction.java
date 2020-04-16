package editor.domain.menu;

import editor.application.App;
import editor.domain.Drawable;
import editor.domain.element.Rectangle;

public abstract class Interaction extends Drawable {

    private Rectangle background;
    private String label;

    public int fontSize = 18;
    
    public Interaction(String label) {
        super();

        this.pos_x = 0;
        this.pos_y = 0;

        this.width = 150;
        this.height = 25;

        this.background = new Rectangle(pos_x, pos_y, width, height, 211, 211, 211, 255);
        this.label = label;
    }

    public abstract void onClick();

    @Override
    public void Draw(int x, int y) {
        App.view.drawRectangle(this.background, x, y);
        App.view.drawText(this.label, x, y + this.fontSize, this.fontSize);
    }

}