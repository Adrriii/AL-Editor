package editor.domain.menu;

import editor.domain.Menu;

public class TopMenu extends Menu {

    public TopMenu() {
        super();

        this.pos_x = 0;
        this.pos_y = 0;

        this.width = 800; // Resizable
        this.height = 75; // Constant

        Notify();
    }
}