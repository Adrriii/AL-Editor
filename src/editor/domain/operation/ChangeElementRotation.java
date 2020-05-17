package editor.domain.operation;

import editor.domain.Element;
import editor.domain.Operation;

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
        obj.Update(obj.pos,obj.pos.x,obj.pos.y);
    }

    @Override
    public void Undo() {
        Element obj = ((Element) state);
        obj.rotation = v1;
        obj.Update(obj.pos,obj.pos.x,obj.pos.y);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}