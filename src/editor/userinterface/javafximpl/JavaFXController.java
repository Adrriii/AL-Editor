package editor.userinterface.javafximpl;

import java.util.ArrayList;
import java.util.Optional;

import editor.application.App;
import editor.domain.Canvas;
import editor.domain.Element;
import editor.domain.IControllable;
import editor.domain.Toolbar;
import editor.domain.ToolbarElement;
import editor.userinterface.Controller;
import javafx.scene.input.MouseEvent;

public class JavaFXController implements Controller {

    private ArrayList<IControllable> controllables;

    private Element draggingElement = null;
    private int drag_x_start;
    private int drag_y_start;
    private boolean readyToDrag = true;
    private boolean selecting = false;

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

        if(App.model.getCanvas().isClicked((int) mouseEvent.getX(),(int) mouseEvent.getY())) {

            Canvas canvas = App.model.getCanvas();

            NotifyMouseCanvas(mouseEvent, canvas, type);
        } else if(App.model.getToolbar().isClicked((int) mouseEvent.getX(),(int) mouseEvent.getY())) {

            Toolbar toolbar = App.model.getToolbar();

            NotifyMouseToolbar(mouseEvent, toolbar, type);
        }
    }

    public void NotifyMouseCanvas(MouseEvent mouseEvent, Canvas canvas, String type) {

        int relative_x = (int) mouseEvent.getX() - canvas.pos_x;
        int relative_y = (int) mouseEvent.getY() - canvas.pos_y;

        switch(type) {
            case "MOUSE_PRESSED":
                break;
            case "MOUSE_RELEASED":
                break;
            case "MOUSE_MOVED":
                break;
            case "MOUSE_CLICKED":
                
                readyToDrag = true;
                selecting = false;

                if(draggingElement == null) {
                    Element selected = App.model.getSelected();
                    if(selected != null) {
                        App.model.addElement(selected,relative_x,relative_y);
                    }
                }
                draggingElement = null;
                break;
            case "MOUSE_DRAGGED":
                if(selecting) {

                }
                if(!readyToDrag) break; // Started dragging on an empty spot, ignoring.

                if(draggingElement == null) {
                    // Find an element to drag, or, disable
                    Optional<Element> found = canvas.getElements()
                                                            .stream().filter(element -> element.isClicked(relative_x, relative_y))
                                                            .findFirst();

                    if(!found.isPresent()) {
                        readyToDrag = false;
                        selecting = true;
                    } else {
                        draggingElement = found.get();
                        drag_x_start = relative_x - draggingElement.pos_x;
                        drag_y_start = relative_y - draggingElement.pos_y;
                    }
                }

                if(draggingElement != null) {
                    draggingElement.Update(relative_x - drag_x_start, relative_y - drag_y_start);
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
                if(draggingElement != null) break;
                
                readyToDrag = true;
                draggingElement = null;

                // Find an element to select, or, select nothing
                Optional<ToolbarElement> found = toolbar.getToolbarElements()
                                                        .stream().filter(element -> element.isClicked((int) mouseEvent.getX(), (int) mouseEvent.getY()))
                                                        .findFirst();

                if(!found.isPresent()) {
                    App.model.setSelected(null);
                } else {
                    App.model.setSelected(found.get().getElement());
                }
                break;
            case "MOUSE_DRAGGED":

                if(!readyToDrag) break; // Started dragging on an empty spot, ignoring.

                if(draggingElement != null) {
                    draggingElement.Update(relative_x - drag_x_start, relative_y - drag_y_start);
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