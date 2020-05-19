package editor.userinterface;

/**
* Defines a Factory used to create a User Interface.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public interface UserInterface {

    public Controller GetController();
    public View GetView();
}