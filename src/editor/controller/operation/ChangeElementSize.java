package editor.controller.operation;

import editor.model.Element;
import editor.controller.Operation;
import editor.model.Position;


/**
* An operation aiming at changing the size of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ChangeElementSize extends Operation {

    /**
     * The initial size value
     */
    protected Position v1;

    /**
     * The new size value
     */
    protected Position v2;


    /**
     * Resize an element from a value to another
     * 
     * @param element The element to rotate
     * @param v1 The initial size value
     * @param v2 The new size value
     */
    public ChangeElementSize(Element element, Position v1, Position v2) {
        state = element;
        this.v1 = new Position(v1.x,v1.y);
        this.v2 = new Position(v2.x,v2.y);
    }

    @Override
    public void Do() {
        Element obj = ((Element) state);
        obj.Update(obj.pos,v2.x,v2.y);
    }

    @Override
    public void Undo() {
        Element obj = ((Element) state);
        obj.Update(obj.pos,v1.x,v1.y);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}