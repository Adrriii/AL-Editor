package editor.domain;

import java.util.HashMap;
import java.util.Optional;

import editor.application.App;
import editor.domain.menu.interactionmenus.ElementInteractionMenu;
import editor.domain.operation.*;

/**
* Controls the model according to user input.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class AppController {

    /**
     * Defines how many pixels the mouse has to travel while
     * being held to consider this a drag
     */
    private final int drag_start_dist = 4;

    /**
     * The current saving location of the whiteboard
     */
    static public String currentCanvasPath;

    /**
     * A helper to handle operations
     */
    static public ActionControl actionControl;

    /**
     * Lists all the keys that have been pressed and not released
     */
    private HashMap<String, Boolean> heldKeys;

    /**
     * Whether left is currently held
     */
    private boolean left = false;

    /**
     * Whether left was recently released
     */
    private boolean left_clicked = false;

    /**
     * Last known position of a left button event
     */
    private int left_pos_x = -1, left_pos_y = -1;

    /**
     * Whether right is currently held
     */
    private boolean right = false;

    /**
     * Whether right was recently released
     */
    private boolean right_clicked = false;

    /**
     * Last known position of a right button event
     */
    private int right_pos_x = -1, right_pos_y = -1;

    /**
     * Last known position of the mouse
     */
    private int pos_x = 0, pos_y = 0;

    /**
     * The element that is currently held by the user with left click
     */
    private Element holdElement = null;

    /**
     * Whether the user just clicked on an already held element
     */
    private boolean clickedOnHoldElement = false;

    /**
     * The element that is currently being dragged accross by the user
     */
    private Element draggingElement = null;

    /**
     * The position relative to the currently dragged element's top left
     */
    private int drag_x_elem_rel, drag_y_elem_rel;

    /**
     * Whether the user has started dragging from the toolbar
     */
    private boolean dragging_from_toolbar = false;

    /**
     * Whether the user just released an element from the toolbar
     */
    private boolean dropped_from_toolbar = false;

    /**
     * Whether a dragging can be started at this time
     */
    private boolean readyToDrag = true;

    /**
     * Whether the user is currently performing a rectangular selection
     */
    private boolean selecting = false;

    /**
     * The starting position of a selection
     */
    private int select_start_x, select_start_y;

    /**
    * Creates the app controller.
    */
    public AppController() {
        actionControl = new ActionControl();
        heldKeys = new HashMap<>();

        currentCanvasPath = null;

        heldKeys.put("CONTROL", false);
        heldKeys.put("SHIFT", false);
        heldKeys.put("ALT", false);
    }

    /**
    * Updates the model on a left mouse press
    *
    * @param x The x screen position
    * @param y The y screen position
    */
    public void NotifyMouseLeftPress(int x, int y) {
        left = true;
        left_pos_x = x;
        left_pos_y = y;
        NotifyMousePos(x, y);
    }

    /**
    * Updates the model on a left mouse release
    *
    * @param x The x screen position
    * @param y The y screen position
    */
    public void NotifyMouseLeftRelease(int x, int y) {
        left = false;
        left_clicked = true;
        left_pos_x = -1;
        left_pos_y = -1;
        NotifyMousePos(x, y);
    }

    /**
    * Updates the model on a right mouse press
    *
    * @param x The x screen position
    * @param y The y screen position
    */
    public void NotifyMouseRightPress(int x, int y) {
        right = true;
        right_pos_x = x;
        right_pos_y = y;
        NotifyMousePos(x, y);
    }

    /**
    * Updates the model on a right mouse release
    *
    * @param x The x screen position
    * @param y The y screen position
    */
    public void NotifyMouseRightRelease(int x, int y) {
        right = false;
        right_clicked = true;
        right_pos_x = -1;
        right_pos_y = -1;
        NotifyMousePos(x, y);
    }

    /**
    * Updates the model on a mouse movement
    *
    * @param x The x screen position
    * @param y The y screen position
    */
    public void NotifyMousePos(int x, int y) {
        pos_x = x;
        pos_y = y;
        UpdateMouse();
    }

    /**
    * Updates the model when resizing the window
    *
    * @param newWidth The new width of the model
    */
    public void NotifyWidth(int newWidth) {
        App.model.Resize(newWidth, App.model.height);
    }

    /**
    * Updates the model when resizing the window
    *
    * @param newWidth The new height of the model
    */
    public void NotifyHeight(int newHeight) {
        App.model.Resize(App.model.width, newHeight);
    }

    /**
    * Updates the model after any mouse event
    */
    private void UpdateMouse() {

        
        int canvas_relative_x = Math.max(0, pos_x - App.model.getCanvas().pos.x);
        int canvas_relative_y = Math.max(0, pos_y - App.model.getCanvas().pos.y);
        
        boolean inCanvas = App.model.getCanvas().isClicked(pos_x, pos_y);
        boolean inToolbar = App.model.getToolbar().isClicked(pos_x, pos_y);
        boolean inTopMenu = App.model.getTopMenu().isClicked(pos_x, pos_y);
        boolean inInteractionMenu = App.model.getInteractionMenu() == null ? false : App.model.getInteractionMenu().isClicked(pos_x, pos_y);

        if(inTopMenu) { // Hover over elements
            App.view.getTopMenuView().Update();
        }

        if(left) {
            if(inInteractionMenu) {
                return;
            }

            if(draggingElement == null && readyToDrag && holdElement == null) {
                // Find the clicked element before dragging
                Optional<Element> found = App.model.getCanvas().getElementAt(canvas_relative_x, canvas_relative_y);
                
                if(found.isPresent()) {
                    SelectHoldElement(found.get());
                }
            }

            if(Math.sqrt((left_pos_y - pos_y) * (left_pos_y - pos_y) + (left_pos_x - pos_x) * (left_pos_x - pos_x)) >= this.drag_start_dist) {
                // Dragging

                if(inCanvas || draggingElement != null) {
                    if(selecting) {
                        int sr_x = Math.min(select_start_x, canvas_relative_x);
                        int sr_y = Math.min(select_start_y, canvas_relative_y);
                        int sr_width = select_start_x < canvas_relative_x ? canvas_relative_x - select_start_x : select_start_x - canvas_relative_x;
                        int sr_height = select_start_y < canvas_relative_y ? canvas_relative_y - select_start_y : select_start_y - canvas_relative_y;

                        App.model.UpdateSelectionRectangle(new Position(sr_x, sr_y), sr_width, sr_height);

                        App.model.getCanvas().getElements().forEach(element -> {
                            if(element.intersects(sr_x, sr_y, sr_width, sr_height)) {
                                App.model.Select(element, true);
                            } else {
                                App.model.Deselect(element);
                            }
                        });
                        return;
                    }
                    if(!readyToDrag) return; // Started dragging on an empty spot, ignoring.
                    if(dragging_from_toolbar) {
                        dragging_from_toolbar = false;
                        
                        CreateElement createElement = new CreateElement(
                            App.model.getSelectedTool().getElement(),
                            new Position(canvas_relative_x,canvas_relative_y)
                        );

                        actionControl.Do(createElement);  
                        draggingElement = createElement.getCreatedElement();

                        dropped_from_toolbar = true;

                        App.model.DeselectAll();
                        App.model.Select(draggingElement);
                        App.model.setSelectedTool(null);
                    }

                    if(draggingElement == null) {

                        select_start_x = canvas_relative_x;
                        select_start_y = canvas_relative_y;
                        if(holdElement == null) {
                            readyToDrag = false;
                            selecting = true;
                        } else {
                            draggingElement = holdElement;
                            drag_x_elem_rel = canvas_relative_x - draggingElement.pos.x;
                            drag_y_elem_rel = canvas_relative_y - draggingElement.pos.y;
                        }
                    }

                    if(draggingElement != null) {
                        draggingElement.Update(new Position(Math.max(0,canvas_relative_x - drag_x_elem_rel), Math.max(0,canvas_relative_y - drag_y_elem_rel)));
                        App.view.getToolbarView().Update(); // Sometimes it hovers on the toolbar
                        App.view.getTopMenuView().Update(); // Sometimes it hovers on the topmenu
                    }
                } else if (inToolbar) {

                    if(!dragging_from_toolbar) {
                        // Find an element to select and drag, or, select nothing
                        Optional<ToolbarElement> found = App.model.getToolbar().getToolbarElements()
                                                                .stream().filter(element -> element.isClicked(pos_x, pos_y))
                                                                .findFirst();
        
                        if(!found.isPresent()) {
                            App.model.setSelectedTool(null);
                            readyToDrag = false;
                        } else {
                            App.model.setSelectedTool(found.get());
                            dragging_from_toolbar = true;
                        }
                    } else {
                        App.view.getToolbarView().Update();
                    }
                }
            } else {
                // Holding click
            }
        }
        
        if(left_clicked) {
            left_clicked = false; // Consume event

            if(inInteractionMenu) {
                App.model.getInteractionMenu().onClick(pos_x, pos_y);
                return;
            } else if (!(App.model.getInteractionMenu() == null)) {
                App.model.setInteractionMenu(null);
                return;
            }

            if(inTopMenu) {
                App.model.getTopMenu().onClick(pos_x, pos_y);
                return;
            }
                
            readyToDrag = true;

            if(selecting) {
                selecting = false;
                App.model.UpdateSelectionRectangle(new Position(0,0),0,0);
                return;
            }

            boolean onBin = App.model.getToolbar().getDeleteElementTool().isClicked(pos_x, pos_y);

            if(draggingElement != null) {
                if(inToolbar) {
                    if(onBin) {
                        actionControl.Do(new DeleteElement(draggingElement, new Position(select_start_x - drag_x_elem_rel, select_start_y - drag_y_elem_rel)));
                    } else {
                        draggingElement.Update(new Position(select_start_x - drag_x_elem_rel, select_start_y - drag_y_elem_rel));
                        actionControl.Do(new AddToolbarElement(draggingElement));
                    }
                } else {

                    Position elementNextPosition = new Position(
                        Math.max(0,canvas_relative_x - drag_x_elem_rel), 
                        Math.max(0,canvas_relative_y - drag_y_elem_rel)
                    );
                    
                    if (!dropped_from_toolbar) {
                        actionControl.Do(
                            new MoveElement(
                                draggingElement, 
                                new Position(select_start_x - drag_x_elem_rel, select_start_y - drag_y_elem_rel),
                                elementNextPosition
                            )
                        );
                    } else {
                        try {
                            ((CreateElement) actionControl.peekLastOperation()).setLocation(elementNextPosition);
                            dropped_from_toolbar = false;
                        } catch(Exception e) {}
                    }
                }

            } else {

                if(inCanvas) {
                    // Clicking in canvas adds the selected element
                    if(holdElement == null) {
                        App.model.DeselectAll();
                        App.view.getCanvasView().Update();
                    } else {
                        if(clickedOnHoldElement) {
                            clickedOnHoldElement = false;
                            App.model.Deselect(holdElement);
                        }
                    }
                }
    
                if(inToolbar) {
                    if(dragging_from_toolbar && onBin && App.model.getSelectedTool() != null) {
                        dragging_from_toolbar = false;
                        actionControl.Do(new DeleteToolbarElement(App.model.getSelectedTool()));
                    } else {
                        // Find an element to select, or, select nothing
                        Optional<ToolbarElement> found = App.model.getToolbar().getToolbarElements()
                                                                .stream().filter(element -> element.isClicked(pos_x, pos_y))
                                                                .findFirst();
        
                        if(!found.isPresent()) {
                            App.model.setSelectedTool(null);
                        } else {
                            App.model.setSelectedTool(found.get());
                        }
                    }
                }

            }
            
            draggingElement = null;
            holdElement = null;
            return;
        }
    
        if(right) {

        }

        if(right_clicked) {
            right_clicked = false;

            if(inCanvas) {
                Optional<Element> found = App.model.getCanvas().getElementAt(canvas_relative_x, canvas_relative_y);

                if(found.isPresent()) {
                    App.model.Select(found.get());
                    App.model.setInteractionMenu(new ElementInteractionMenu(pos_x, pos_y,found.get()));
                    return;
                } else {
                    App.model.DeselectAll();
                    App.view.getCanvasView().Update();
                }
            } 
            
            if (!(App.model.getInteractionMenu() == null)) {
                App.model.setInteractionMenu(null);
                return;
            }
        }
    }

    private void SelectHoldElement(Element element) {
        holdElement = element;
        clickedOnHoldElement = holdElement.isSelected();
        App.model.DeselectAll();
        App.model.Select(holdElement);
        App.view.getCanvasView().Update();
    }

    /**
    * Updates the model after any key press
    *
    * @param key The text representation of the pressed key
    */
    public void NotifyKeyPressed(String key) {
        heldKeys.put(key, true);

        switch(key) {
            case "S":
                if(heldKeys.get("CONTROL") == true) {
                    if(heldKeys.get("SHIFT") == true) {
                        AppController.currentCanvasPath = App.controller.getChosenSaveFile();
                    }
                    (new SaveCanvas()).Do();
                }
                break;
            case "O":
                if(heldKeys.get("CONTROL") == true) {
                    AppController.currentCanvasPath = App.controller.getChosenFile();
                    (new LoadCanvas()).Do();
                    actionControl.Clear();
                }
                break;
            case "Z":
                if(heldKeys.get("CONTROL") == true) {
                    actionControl.Undo();
                }
                break;
            case "Y":
                if(heldKeys.get("CONTROL") == true) {
                    actionControl.Redo();
                }
                break;
            case "A":
                if(heldKeys.get("CONTROL") == true) {
                    App.model.SelectAll();
                }
                break;
            default:
                break;
        }
    }

    /**
    * Updates the model after any key release
    *
    * @param key The text representation of the released key
    */
    public void NotifyKeyReleased(String key) {
        heldKeys.put(key, false);

        switch(key) {
            default:
                break;
        }
    }

    /**
    * Returns the latest known position of the mouse
    */
    public Position CurrentMousePos() {
        return new Position(pos_x, pos_y);
    }
}