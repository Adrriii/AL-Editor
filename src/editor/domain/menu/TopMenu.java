package editor.domain.menu;

import editor.application.App;
import editor.domain.Menu;
import editor.domain.element.Rectangle;

public class TopMenu extends Menu {

    private Rectangle background;
    
    public TopMenu() {
        super();
        
        Attach(App.view.getTopMenuView());

        this.pos_x = 0;
        this.pos_y = 0;

        this.width = 800; // Resizable
        this.height = 75; // Constant

        this.background = new Rectangle(pos_x, pos_y, width, height, 211, 211, 211, 255);
        
    }

    @Override
    public void Draw(int x, int y) {
        App.view.drawRectangle(this.background, x, y);
    }
}