package editor.userinterface.javafximpl;

import java.util.HashMap;

import editor.application.App;
import editor.userinterface.Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
* Translates JavaFX events to what can be read by the application.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class JavaFXController implements Controller {

    private HashMap<KeyCode, Boolean> heldKeys;

    public JavaFXController() {
        heldKeys = new HashMap<>();
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

    public void NotifyKeyboard(KeyEvent keyEvent) {
        String type = keyEvent.getEventType().getName();

        KeyCode key = keyEvent.getCode();

        switch(type) {
            case "KEY_TYPED":
                break;
            case "KEY_PRESSED":
                if(heldKeys.get(key) == null) {
                    App.appController.NotifyKeyPressed(key.toString());
                    heldKeys.put(key, true);
                }
                break;
            case "KEY_RELEASED":
                if(heldKeys.get(key) != null) {
                    App.appController.NotifyKeyReleased(key.toString());
                    heldKeys.remove(key);
                }
                break;
            default:
                System.out.println("Unsupported event type : "+type);
        }
    }

    public void NotifyWidth(int newWidth) {
        if(App.appController != null) App.appController.NotifyWidth(newWidth);
    }

    public void NotifyHeight(int newHeight) {
        if(App.appController != null) App.appController.NotifyHeight(newHeight);
    }

    @Override
    public String getChosenSaveFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialFileName("myCanvas.ale");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("AL-Editor Canvas","*.ale"));

        return fileChooser.showSaveDialog(JavaFXApplication.stage).getAbsolutePath();
    }

    @Override
    public String getChosenFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialFileName("myCanvas.ale");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("AL-Editor Canvas","*.ale"));

        return fileChooser.showOpenDialog(JavaFXApplication.stage).getAbsolutePath();
    }

}