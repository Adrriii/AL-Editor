package editor.domain.menu;

import editor.application.App;
import editor.domain.Drawable;
import editor.domain.Position;
import editor.domain.element.Rectangle;

public abstract class Interaction extends Drawable {

    private Rectangle background;
    private String label;

    public int fontSize = 18;
    
    public Interaction(String label) {
        super();

        this.pos.x = 0;
        this.pos.y = 0;

        this.width = 150;
        this.height = 25;

        this.background = new Rectangle(pos.x, pos.y, width, height, 211, 211, 211, 255);
        this.label = label;
    }

    public abstract void onClick();

    @Override
    public void Draw(Position pos) {
        App.view.drawRectangle(this.background, pos.x, pos.y);
        App.view.drawText(this.label, pos.x, pos.y + this.fontSize, this.fontSize);
    }

}