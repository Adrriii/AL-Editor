package editor.controller.operation;

import editor.model.Element;
import editor.controller.Operation;
import editor.model.Position;

/**
* An operation aiming at moving an element inside the whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class MoveElement extends Operation {

    /**
     * The previous position
     */
    protected Position move_from;

    /**
     * The new position
     */
    protected Position move_to;

    /**
     * Move an element from a position to a new one
     * 
     * @param element The element to move
     * @param from The previous position
     * @param to The new position
     */
    public MoveElement(Element element, Position from, Position to) {
        state = element;
        move_from = new Position(from.x, from.y);
        move_to = new Position(to.x, to.y);
    }

    @Override
    public void Do() {
        ((Element) state).Update(move_to);
    }

    @Override
    public void Undo() {
        ((Element) state).Update(move_from);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}