package editor.userinterface.javafximpl;

import editor.model.Model;
import editor.userinterface.Controller;
import editor.userinterface.UserInterface;
import editor.userinterface.View;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JavaFXUI implements UserInterface {

    @Override
    public Controller GetController() {
        return new JavaFXController();
    }

    @Override
    public View GetView(Model model) {
        return new JavaFXView(model);
    }

}