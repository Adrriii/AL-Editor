package editor.userinterface.testimpl;

import editor.userinterface.Controller;
import editor.userinterface.UserInterface;
import editor.userinterface.View;

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