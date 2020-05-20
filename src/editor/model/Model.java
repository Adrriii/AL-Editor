package editor.model;

import java.util.ArrayList;

import editor.application.App;
import editor.model.*;
import editor.model.element.Rectangle;
import editor.model.menu.*;
import editor.controller.Control;
import editor.controller.operation.LoadToolbar;

/**
* Holds the application's memory representation.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class Model {

    /**
     * The whiteboard of the application, containing
     * all the elements to create what the user wishes
     */
    private Canvas canvas;

    /**
     * The sidebar in which all of the user's tools are stored
     */
    private Toolbar toolbar;

    /**
     * The menu used by the user to perform global actions
     */
    private TopMenu topMenu;

    /**
     * The currently selected tool
     */
    private ToolbarElement selectedTool;

    /**
     * The rectangle defining the current user's selection inside the whiteboard
     */
    private Rectangle selectionRectangle;

    /**
     * The currently selected elements
     */
    private ArrayList<Element> selected;

    /**
     * The controls available to the user at this time
     */
    private ArrayList<Control> controls;

    /**
     * Whether the application is currently running
     */
    private boolean running;

    /**
     * The currently displayed interaction menu
     */
    private InteractionMenu currentInteraction;

    /**
     * The dimensions of the window
     */
    public int width, height;

    /**
     * Prepare the application
     */
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

    /**
     * Changes the current whiteboard to a new one
     * 
     * @param canvas The whiteboard to use
     */
    public void SetCanvas(Canvas canvas) {
        this.canvas = canvas;

        this.selected = new ArrayList<>();
        this.selectionRectangle = null;

        Resize(width,height);
        App.view.Update();
    }

    /**
     * Changes the currently used toolbar
     * 
     * @param toolbar The toolbar to use
     */
    public void SetToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;

        Resize(width,height);
        App.view.Update();
    }

    /**
     * Perform potentially needed updates
     */
    public void Update() {
        
    }

    /**
     * Tell the model that the application is not running
     */
    public void Stop() {
        this.running = false;
    }

    /**
     * Whether the model thinks the application is running
     * @return Whether the model thinks the application is running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Get the current whiteboard
     * @return The current whiteboard
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    /**
     * Get the current toolbar
     * @return The current toolbar
     */
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    /**
     * Get the current topmenu
     * @return The current topmenu
     */
    public TopMenu getTopMenu() {
        return this.topMenu;
    }

    /**
     * Change the currently selected tool
     * @param element The tool to select
     */
    public void setSelectedTool(ToolbarElement element) {
        this.selectedTool = element;
    }

    /**
     * Get the currently selected tool
     * @return The currently selected tool
     */
    public ToolbarElement getSelectedTool() {
        return selectedTool;
    }

    /**
     * Create the selection rectangle 
     * @param pos The position
     * @param width The width
     * @param height The height
     */
    public void SetSelectionRectangle(Position pos, int width, int height) {
        if(pos.x == 0 && pos.y == 0 && width == 0 && height == 0) {
            this.selectionRectangle = null;
        } else {
            this.selectionRectangle = new Rectangle(pos.x, pos.y, width, height, 0,0,255,50);
            this.selectionRectangle.Attach(App.view.getCanvasView());
        }
    }

    /**
     * Update the selection rectangle 
     * @param pos The new position
     * @param width The new width
     * @param height The new height
     */
    public void UpdateSelectionRectangle(Position pos, int width, int height) {
        if(this.selectionRectangle == null) {
            SetSelectionRectangle(pos, width, height);
        } else {
            this.selectionRectangle.Update(pos, width, height);
        }
    }

    /**
     * Get the current selection rectangle
     * @returnGet the current selection rectangle
     */
    public Rectangle getSelectionRectangle() {
        return this.selectionRectangle;
    }

    /**
     * Add a new element to the whiteboard
     * 
     * @param element The element to add
     * @param pos The position to put the element at
     * @return The created element
     */
    public Element addElement(Element element, Position pos) {
        return this.canvas.newElement(element, pos);
    }

    /**
     * Add an element to the whiteboard without cloning it
     * Careful when using this method !
     * 
     * @param element The element to add
     * @param pos The position to put the element at
     * @return The element, it might have been modified!
     */
    public Element addExistingElement(Element element, Position pos) {
        element.Update(pos);
        this.canvas.addElement(element);
        return element;
    }

    /**
     * Remove an element from the whiteboard
     * 
     * @param element The element to remove
     */
    public void removeElement(Element element) {
        this.canvas.removeElement(element);
    }
    
    /**
     * Change the dimensions of the model.
     * It automatically updates the other parts !
     * 
     * @param width The new width
     * @param height The new height
     */
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

    /**
     * Select an element and place it on top (Drawn last, selected last)
     * 
     * @param element The element to select
     */
    public void Select(Element element) {
        Select(element, false);
    }


    /**
     * Select an element and chose whether to place it on top (Drawn last, selected last)
     * 
     * @param element The element to select
     * @param keepOrder Set to true if you don't want it to be placed on top
     */
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

    /**
     * Deselect an element
     * 
     * @param element The element to deselect
     */
    public void Deselect(Element element) {
        this.selected.remove(element);
        element.setSelected(false);
    }

    /**
     * Select all elements in the whiteboard (doesn't change the order)
     */
    public void SelectAll() {
        canvas.getElements().forEach(element -> Select(element, true));
        App.view.getCanvasView().Update();
    }

    /**
     * Deselect all elements in the whiteboard
     */
    public void DeselectAll() {
        canvas.getElements().forEach(element -> Deselect(element));
        new ArrayList<Element>(this.selected).forEach(element -> Deselect(element));
    }
 
    /**
     * Get all the selected elements in the whiteboard
     * @return A list of elements
     */
    public ArrayList<Element> getSelectedElements() {
        return this.selected;
    }

    /**
     * Set a new interaction menu. Only one can be displayed at a time
     * 
     * @param interactionMenu The new interaction menu
     */
    public void setInteractionMenu(InteractionMenu interactionMenu) {
        this.currentInteraction = interactionMenu;
        App.view.getMainView().Update();
    }

    /**
     * Get the current interaction menu 
     * @return The current interaction menu 
     */
    public InteractionMenu getInteractionMenu() {
        return this.currentInteraction;
    }

    /**
     * Add a new control in the model
     * 
     * @param control The control to be added
     */
    public void AddControl(Control control) {
        this.controls.add(control);
        App.view.getMainView().Update();
    }

    /**
     * Remove a control from the model
     * 
     * @param control The control to remove
     */
    public void RemoveControl(Control control) {
        this.controls.remove(control);
        App.view.getMainView().Update();
    }

    /**
     * Get all the currently available controls
     * 
     * @return A list of Controls
     */
    public ArrayList<Control> getControls() {
        return controls;
    }
}