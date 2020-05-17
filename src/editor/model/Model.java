package editor.model;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.*;
import editor.domain.element.Rectangle;
import editor.domain.menu.*;
import editor.domain.operation.LoadToolbar;

public class Model {

    private Canvas canvas;
    private Toolbar toolbar;
    private TopMenu topMenu;

    private ToolbarElement selectedTool;

    private Rectangle selectionRectangle;
    private ArrayList<Element> selected;
    private ArrayList<Control> controls;

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
        this.topMenu = new TopMenu();
        
        try {
            (new LoadToolbar()).Do();
        } catch(Exception e) {
            App.win_env = false;
            (new LoadToolbar()).Do();
        }

        this.selected = new ArrayList<>();
        this.selectionRectangle = null;

        this.controls = new ArrayList<>();

        this.canvas.Notify();
        this.toolbar.Notify();
        this.topMenu.Notify();
    }

    public void SetCanvas(Canvas canvas) {
        this.canvas = canvas;

        this.selected = new ArrayList<>();
        this.selectionRectangle = null;

        Resize(width,height);
        App.view.Update();
    }

    public void SetToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;

        Resize(width,height);
        App.view.Update();
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

    public void setSelectedTool(ToolbarElement element) {
        this.selectedTool = element;
    }

    public ToolbarElement getSelectedTool() {
        return selectedTool;
    }

    public void SetSelectionRectangle(Position pos, int width, int height) {
        if(pos.x == 0 && pos.y == 0 && width == 0 && height == 0) {
            this.selectionRectangle = null;
        } else {
            this.selectionRectangle = new Rectangle(pos.x, pos.y, width, height, 0,0,255,50);
            this.selectionRectangle.Attach(App.view.getCanvasView());
        }
    }

    public void UpdateSelectionRectangle(Position pos, int width, int height) {
        if(this.selectionRectangle == null) {
            SetSelectionRectangle(pos, width, height);
        } else {
            this.selectionRectangle.Update(pos, width, height);
        }
    }

    public Rectangle getSelectionRectangle() {
        return this.selectionRectangle;
    }

    public Element addElement(Element element, Position pos) {
        return this.canvas.newElement(element, pos);
    }

    public Element addExistingElement(Element element, Position pos) {
        element.Update(pos);
        this.canvas.addElement(element);
        return element;
    }

    public void removeElement(Element element) {
        this.canvas.removeElement(element);
    }
    
    public void Resize(int width, int height) {
        this.width = width;
        this.height = height;

        topMenu.Resize(width, topMenu.height);
        toolbar.Resize(toolbar.width, height);
        canvas.Resize(width - toolbar.width, height - topMenu.height);

        this.canvas.Notify();
        this.toolbar.Notify();
        this.topMenu.Notify();
    }

    public void Select(Element element) {
        Select(element, false);
    }

    public void Select(Element element, boolean keepOrder) {
        ArrayList<Element> nselect = new ArrayList<>();
        this.selected.add(element);
        element.setSelected(true);

        // Preserve elements order
        for(Element el : canvas.getElements()) {
            if(this.selected.contains(el)) {
                nselect.add(el);
            }
        }
        this.selected = nselect;
        if (!keepOrder) this.canvas.SetOnTop(element);
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
        new ArrayList<Element>(this.selected).forEach(element -> Deselect(element));
    }
 
    public ArrayList<Element> getSelectedElements() {
        return this.selected;
    }

    public void setInteractionMenu(InteractionMenu interactionMenu) {
        this.currentInteraction = interactionMenu;
        App.view.getMainView().Update();
    }

    public InteractionMenu getInteractionMenu() {
        return this.currentInteraction;
    }

    public void AddControl(Control control) {
        this.controls.add(control);
        App.view.getMainView().Update();
    }

    public void RemoveControl(Control control) {
        this.controls.remove(control);
        App.view.getMainView().Update();
    }

    public ArrayList<Control> getControls() {
        return controls;
    }
}