package editor.domain.menu;

import editor.application.App;
import editor.domain.Menu;
import editor.domain.Position;
import editor.domain.element.Rectangle;

public class TopMenu extends Menu {

    private Rectangle background;
    
    public TopMenu() {
        super();
        
        Attach(App.view.getTopMenuView());

        this.pos.x = 0;
        this.pos.y = 0;

        this.width = 800; // Resizable
        this.height = 75; // Constant

        this.background = new Rectangle(pos.x, pos.y, width, height, 211, 211, 211, 255);
        
    }
    
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.background.Update(this.pos, width, height);
    }

    @Override
    public void Draw(Position pos) {
        App.view.drawRectangle(this.background, pos.x, pos.y);
    }
}