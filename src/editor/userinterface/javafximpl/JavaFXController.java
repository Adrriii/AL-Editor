package editor.userinterface.javafximpl;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.IControllable;
import editor.userinterface.Controller;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class JavaFXController implements Controller {

    private ArrayList<IControllable> controllables;

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

        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();

        switch(type) {
            case "MOUSE_PRESSED":
                if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                    App.appController.NotifyMouseLeftPress(x, y);

                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    App.appController.NotifyMouseRightPress(x, y);
                }
                break;
            case "MOUSE_RELEASED":
                if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                    App.appController.NotifyMouseLeftRelease(x, y);

                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    App.appController.NotifyMouseRightRelease(x, y);
                }
                break;
            case "MOUSE_MOVED":
                App.appController.NotifyMousePos(x, y);
                break;
            case "MOUSE_CLICKED":
                break;
            case "MOUSE_DRAGGED":
                App.appController.NotifyMousePos(x, y);
                break;
            case "MOUSE_ENTERED_TARGET":
                break;
            case "MOUSE_EXITED_TARGET":
                break;
            case "MOUSE_ENTERED":
                break;
            case "MOUSE_EXITED":
                break;
            case "DRAG_DETECTED":
                break;
            default:
                System.out.println("Unsupported event type : "+type);
        }
    }

    public void NotifyWidth(int newWidth) {
        App.appController.NotifyWidth(newWidth);
    }

    public void NotifyHeight(int newHeight) {
        App.appController.NotifyHeight(newHeight);
    }

}