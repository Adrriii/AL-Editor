package editor.userinterface;

/**
* A part of the screen that can be redrawn independently from the others.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public interface ViewScope {

    public void Tick();

    public void Update();

    public String GetScopeName();
}