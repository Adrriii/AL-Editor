package editor.domain;

/**
* Represents an action that can be done and undone
* Allows to go back and forth from two states.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Operation {

    protected Object state;
    
    abstract public void Do();

    abstract public void Undo();

    abstract public boolean Doable();
    
}