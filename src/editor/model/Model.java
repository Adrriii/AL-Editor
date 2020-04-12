package editor.model;

import editor.domain.*;
import editor.domain.menu.*;

public class Model {

    private Canvas canvas;
    private Toolbar toolbar;
    private TopMenu topMenu;

    private boolean running;

    public void init() {
        /*

        Starts at 800x600, has to be resizable.
        Menu is constant height, toolbar is constant width.

        */

        this.running = true;

        this.canvas = new Canvas();
        this.toolbar = new Toolbar();
        this.topMenu = new TopMenu();

        this.canvas.Notify();
        this.toolbar.Notify();
        this.topMenu.Notify();
    }

    public void Update() {
    }

    public void Stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    public TopMenu getTopMenu() {
        return this.topMenu;
    }
    
}