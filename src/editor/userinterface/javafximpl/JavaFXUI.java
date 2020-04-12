package editor.userinterface.javafximpl;

import editor.userinterface.Controller;
import editor.userinterface.UserInterface;
import editor.userinterface.View;

public class JavaFXUI implements UserInterface {

    @Override
    public Controller GetController() {
        return new JavaFXController();
    }

    @Override
    public View GetView() {
        return new JavaFXView();
    }

}