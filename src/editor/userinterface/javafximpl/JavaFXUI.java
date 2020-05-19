package editor.userinterface.javafximpl;

import editor.userinterface.Controller;
import editor.userinterface.UserInterface;
import editor.userinterface.View;

/**
* Allows to fetch the required elements for a JavaFX user interface.
* 
* @author Adrien Boitelle
* @version 1.0
*/
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