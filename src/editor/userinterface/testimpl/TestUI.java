package editor.userinterface.testimpl;

import editor.userinterface.Controller;
import editor.userinterface.UserInterface;
import editor.userinterface.View;

/**
* Allows to get a dummy user interface for unit testing purposes.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TestUI implements UserInterface {

    @Override
    public Controller GetController() {
        return new TestController();
    }

    @Override
    public View GetView() {
        return new TestView();
    }

}