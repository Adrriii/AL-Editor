package editor.userinterface.javafximpl;

import java.util.ArrayList;
import java.util.Optional;

import editor.application.App;
import editor.domain.Canvas;
import editor.domain.Element;
import editor.domain.IControllable;
import editor.userinterface.Controller;
import javafx.scene.input.MouseEvent;

public class JavaFXController implements Controller {

    private ArrayList<IControllable> controllables;

    private Element draggingElement = null;
    private int drag_x_start;
    private int drag_y_start;
    private boolean readyToDrag = true;

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

        switch(type) {
            case "MOUSE_PRESSED":
                System.out.println("Mouse pressed at "+mouseEvent.getX()+","+mouseEvent.getY());
                break;
            case "MOUSE_RELEASED":
                System.out.println("Mouse released at "+mouseEvent.getX()+","+mouseEvent.getY());
                readyToDrag = true;
                draggingElement = null;
                break;
            case "MOUSE_MOVED":
                //System.out.println("Mouse moved to "+mouseEvent.getX()+","+mouseEvent.getY());
                break;
            case "MOUSE_DRAGGED":
                if(!readyToDrag) break; // Started dragging on an empty spot, ignoring.

                Canvas canvas = App.model.getCanvas();

                int relative_x = (int) mouseEvent.getX() - canvas.pos_x;
                int relative_y = (int) mouseEvent.getY() - canvas.pos_y;

                if(draggingElement == null) {
                    // Find an element to drag, or, disable
                    Optional<Element> found = canvas.getElements()
                                                            .stream().filter(element -> element.isClicked(relative_x, relative_y))
                                                            .findFirst();

                    if(!found.isPresent()) {
                        readyToDrag = false;
                        System.out.println("Dragging nothing");
                    } else {
                        draggingElement = found.get();
                        drag_x_start = relative_x - draggingElement.pos_x;
                        drag_y_start = relative_y - draggingElement.pos_y;
                    }
                }

                if(draggingElement != null) {
                    System.out.println("Dragging element");
                    draggingElement.Update(relative_x - drag_x_start, relative_y - drag_y_start);
                }
                break;
            default:
                System.out.println("Unsupported event type : "+type);
        }
    }

    public void NotifyWidth(int newWidth) {
        App.model.Resize(newWidth, App.model.height);
    }

    public void NotifyHeight(int newHeight) {
        App.model.Resize(App.model.width, newHeight);
    }

}