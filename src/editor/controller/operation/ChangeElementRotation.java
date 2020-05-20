package editor.controller.operation;

import editor.model.Element;
import editor.controller.Operation;


/**
* An operation aiming at changing the roation of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ChangeElementRotation extends Operation {

    /**
     * The initial roation value
     */
    protected int v1;
    
    /**
     * The new roation value
     */
    protected int v2;

    /**
     * Rotate an element from a value to another
     * 
     * @param element The element to rotate
     * @param v1 The initial roation value
     * @param v2 The new roation value
     */
    public ChangeElementRotation(Element element, int v1, int v2) {
        state = element;
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public void Do() {
        Element obj = ((Element) state);
        obj.rotation = v2;
        obj.Update(obj.pos,obj.width,obj.height);
    }

    @Override
    public void Undo() {
        Element obj = ((Element) state);
        obj.rotation = v1;
        obj.Update(obj.pos,obj.width,obj.height);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}