package editor.userinterface;

import editor.model.Model;

public interface UserInterface {

    public Controller GetController();
    public View GetView();
}