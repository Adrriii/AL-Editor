package editor.model;

import java.util.HashSet;

import editor.application.App;
import editor.domain.*;
import editor.domain.element.Rectangle;
import editor.domain.menu.*;

public class Model {

    private Canvas canvas;
    private Toolbar toolbar;
    private TopMenu topMenu;

    private Element selectedTool;

    private Rectangle selectionRectangle;
    private HashSet<Element> selected;

    private boolean running;

    private InteractionMenu currentInteraction;

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

        this.selected = new HashSet<>();
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
        if(pos_x == 0 && pos_y == 0 && width == 0 && height == 0) {
            this.selectionRectangle = null;
        } else {
            this.selectionRectangle = new Rectangle(pos_x, pos_y, width, height, 0,0,255,50);
            this.selectionRectangle.Attach(App.view.getCanvasView());
        }
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

    public void Select(Element element) {
        this.selected.add(element);
        element.setSelected(true);
    }

    public void Deselect(Element element) {
        this.selected.remove(element);
        element.setSelected(false);
    }

    public void SelectAll() {
        canvas.getElements().forEach(element -> Select(element));
    }

    public void DeselectAll() {
        canvas.getElements().forEach(element -> Deselect(element));
    }

    public void setInteractionMenu(InteractionMenu interactionMenu) {
        this.currentInteraction = interactionMenu;
        App.view.getMainView().Update();
    }

    public InteractionMenu getInteractionMenu() {
        return this.currentInteraction;
    }
}