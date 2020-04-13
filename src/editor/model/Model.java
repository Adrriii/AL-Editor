package editor.model;

import java.util.ArrayList;

import editor.domain.*;
import editor.domain.element.Rectangle;
import editor.domain.menu.*;

public class Model {

    private Canvas canvas;
    private Toolbar toolbar;
    private TopMenu topMenu;

    private Element selectedTool;

    private Rectangle selectionRectangle;
    private ArrayList<Element> selected;

    private boolean running;

    public int width;
    public int height;

    public void init() {
        /*

        Starts at 800x600, has to be resizable.
        Menu is constant height, toolbar is constant width.

        */

        this.width = 800;
        this.height = 600;

        this.running = true;

        this.canvas = new Canvas();
        this.toolbar = new Toolbar();
        this.topMenu = new TopMenu();

        this.selected = null;
        this.selectionRectangle = null;

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

    public void setSelectedTool(Element element) {
        this.selectedTool = element;
    }

    public Element getSelectedTool() {
        return selectedTool;
    }

    public void SetSelectionRectangle(int pos_x, int pos_y, int width, int height) {
        this.selectionRectangle = new Rectangle(pos_x, pos_y, width, height, 0,0,255,50);
    }

    public void UpdateSelectionRectangle(int pos_x, int pos_y, int width, int height) {
        if(this.selectionRectangle == null) {
            SetSelectionRectangle(pos_x, pos_y, width, height);
        } else {
            this.selectionRectangle.Update(pos_x, pos_y, width, height);
        }
    }

    public Rectangle getSelectionRectangle() {
        return this.selectionRectangle;
    }

    public void addElement(Element element, int pos_x, int pos_y) {
        this.canvas.newElement(element, pos_x, pos_y);
    }
    
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;

        topMenu.width = width;
        toolbar.height = height;
        canvas.width = width - toolbar.width;
        canvas.height = height - topMenu.height;

        this.canvas.Notify();
        this.toolbar.Notify();
        this.topMenu.Notify();
    }
}