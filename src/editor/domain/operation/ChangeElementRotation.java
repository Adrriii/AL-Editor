package editor.domain.operation;

import editor.domain.Element;
import editor.domain.Operation;


/**
* An operation aiming at changing the roation of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ChangeElementRotation extends Operation {

    protected int v1;
    protected int v2;

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