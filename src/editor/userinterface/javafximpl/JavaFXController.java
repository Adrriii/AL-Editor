package editor.userinterface.javafximpl;

import java.util.ArrayList;
import java.util.Optional;

import editor.application.App;
import editor.domain.Canvas;
import editor.domain.Element;
import editor.domain.IControllable;
import editor.domain.Toolbar;
import editor.domain.ToolbarElement;
import editor.domain.menu.TopMenu;
import editor.userinterface.Controller;
import javafx.scene.input.MouseEvent;

public class JavaFXController implements Controller {

    private ArrayList<IControllable> controllables;

    private Element draggingElement = null;
    private int drag_x_elem_rel;
    private int drag_y_elem_rel;
    private boolean readyToDrag = true;

    private boolean selecting = false;
    private int drag_x_start;
    private int drag_y_start;

    public JavaFXController() {
        controllables = new ArrayList<>();
    }

    @Override
    public void Attach(IControllable controllable) {
        controllables.add(controllable);
    }

    @Override
    public void Detach(IControllable controllable) {
        controllables.remove(controllable);
    }

    public void NotifyMouse(MouseEvent mouseEvent) {
        String type = mouseEvent.getEventType().getName();

        if(App.model.getToolbar().isClicked((int) mouseEvent.getX(),(int) mouseEvent.getY())) {

            Toolbar toolbar = App.model.getToolbar();

            NotifyMouseToolbar(mouseEvent, toolbar, type);
        } else if(App.model.getTopMenu().isClicked((int) mouseEvent.getX(),(int) mouseEvent.getY())) {

            TopMenu topMenu = App.model.getTopMenu();

            NotifyMouseTopMenu(mouseEvent, topMenu, type);
        } else {

            Canvas canvas = App.model.getCanvas();

            NotifyMouseCanvas(mouseEvent, canvas, type);
        }
    }

    public void NotifyMouseCanvas(MouseEvent mouseEvent, Canvas canvas, String type) {

        int relative_x = Math.max(0,(int) mouseEvent.getX() - canvas.pos_x);
        int relative_y = Math.max(0,(int) mouseEvent.getY() - canvas.pos_y);

        switch(type) {
            case "MOUSE_PRESSED":
                break;
            case "MOUSE_RELEASED":
                break;
            case "MOUSE_MOVED":
                break;
            case "MOUSE_CLICKED":
                
                readyToDrag = true;

                if(selecting) {
                    selecting = false;
                    App.model.UpdateSelectionRectangle(0,0,0,0);
                    break;
                }

                if(draggingElement == null) {
                    Element selected = App.model.getSelectedTool();
                    if(selected != null) {
                        App.model.addElement(selected,relative_x,relative_y);
                    }
                }
                draggingElement = null;
                break;
            case "MOUSE_DRAGGED":
                if(selecting) {
                    App.model.UpdateSelectionRectangle(
                        Math.min(this.drag_x_start, relative_x), 
                        Math.min(this.drag_y_start, relative_y),
                        this.drag_x_start < relative_x ? relative_x - this.drag_x_start : this.drag_x_start - relative_x, 
                        this.drag_y_start < relative_y ? relative_y - this.drag_y_start : this.drag_y_start - relative_y
                    );

                    break;
                }
                if(!readyToDrag) break; // Started dragging on an empty spot, ignoring.

                if(draggingElement == null) {
                    // Find an element to drag, or, disable
                    Optional<Element> found = canvas.getElements()
                                                            .stream().filter(element -> element.isClicked(relative_x, relative_y))
                                                            .findFirst();

                    drag_x_start = relative_x;
                    drag_y_start = relative_y;

                    if(!found.isPresent()) {
                        readyToDrag = false;
                        selecting = true;
                    } else {
                        draggingElement = found.get();
                        drag_x_elem_rel = relative_x - draggingElement.pos_x;
                        drag_y_elem_rel = relative_y - draggingElement.pos_y;
                    }
                }

                if(draggingElement != null) {
                    draggingElement.Update(relative_x - drag_x_elem_rel, relative_y - drag_y_elem_rel);
                }
                break;
            default:
                //System.out.println("Unsupported event type : "+type);
        }
    }

    public void NotifyMouseToolbar(MouseEvent mouseEvent, Toolbar toolbar, String type) {

        int relative_x = (int) mouseEvent.getX() - toolbar.pos_x;
        int relative_y = (int) mouseEvent.getY() - toolbar.pos_y;

        switch(type) {
            case "MOUSE_PRESSED":
                break;
            case "MOUSE_RELEASED":
                break;
            case "MOUSE_MOVED":
                break;
            case "MOUSE_CLICKED":
                if(draggingElement != null);
                
                readyToDrag = true;
                draggingElement = null;

                if(selecting) {
                    selecting = false;
                    App.model.UpdateSelectionRectangle(0,0,0,0);
                    break;
                }

                // Find an element to select, or, select nothing
                Optional<ToolbarElement> found = toolbar.getToolbarElements()
                                                        .stream().filter(element -> element.isClicked((int) mouseEvent.getX(), (int) mouseEvent.getY()))
                                                        .findFirst();

                if(!found.isPresent()) {
                    App.model.setSelectedTool(null);
                } else {
                    App.model.setSelectedTool(found.get().getElement());
                }
                break;
            case "MOUSE_DRAGGED":
                if(selecting) {
                    App.model.UpdateSelectionRectangle(
                        0, 
                        Math.min(this.drag_y_start, relative_y),
                        this.drag_x_start, 
                        this.drag_y_start < relative_y ? relative_y - this.drag_y_start : this.drag_y_start - relative_y
                    );

                    break;
                }

                if(!readyToDrag) break; // Started dragging on an empty spot, ignoring.

                if(draggingElement != null) {
                    int canvas_x = Math.max(0,relative_x - App.model.getCanvas().pos_x);
                    draggingElement.Update(canvas_x - drag_x_elem_rel, relative_y - drag_y_elem_rel);
                }
                break;
            default:
                //System.out.println("Unsupported event type : "+type);
        }
    }

    public void NotifyMouseTopMenu(MouseEvent mouseEvent, TopMenu topMenu, String type) {

        int relative_x = (int) mouseEvent.getX() - topMenu.pos_x;
        int relative_y = (int) mouseEvent.getY() - topMenu.pos_y;

        switch(type) {
            case "MOUSE_PRESSED":
                break;
            case "MOUSE_RELEASED":
                break;
            case "MOUSE_MOVED":
                break;
            case "MOUSE_CLICKED":

                if(selecting) {
                    selecting = false;
                    App.model.UpdateSelectionRectangle(0,0,0,0);
                    break;
                }
                
                break;
            case "MOUSE_DRAGGED":
                if(selecting) {
                    int canvas_x = Math.max(0,relative_x - App.model.getCanvas().pos_x);
                    App.model.UpdateSelectionRectangle(
                        Math.min(this.drag_x_start, canvas_x),
                        0, 
                        this.drag_x_start < canvas_x ? canvas_x - this.drag_x_start : this.drag_x_start - canvas_x,
                        this.drag_y_start
                    );

                    break;
                }

                if(!readyToDrag) break; // Started dragging on an empty spot, ignoring.

                if(draggingElement != null) {
                    int canvas_x = Math.max(0,relative_x - App.model.getCanvas().pos_x);
                    int canvas_y = Math.max(0,relative_y - App.model.getCanvas().pos_y);
                    draggingElement.Update(canvas_x - drag_x_elem_rel, canvas_y - drag_y_elem_rel);
                }
                break;
            default:
                //System.out.println("Unsupported event type : "+type);
        }
    }

    public void NotifyWidth(int newWidth) {
        App.model.Resize(newWidth, App.model.height);
    }

    public void NotifyHeight(int newHeight) {
        App.model.Resize(App.model.width, newHeight);
    }

}