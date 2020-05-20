package editor.controller;

/**
* Represents an action that can be done and undone
* Allows to go back and forth from two states.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Operation {

    /**
     * The current state of the operation
     */
    protected Object state;
    
    /**
     * Perform the operation
     */
    abstract public void Do();

    /**
     * Restore the original state
     */
    abstract public void Undo();

    /**
     * Whether the operation can be performed at this time
     * @return Whether the operation can be performed at this time
     */
    abstract public boolean Doable();
    
}