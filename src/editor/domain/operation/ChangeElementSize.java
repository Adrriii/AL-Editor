package editor.domain.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementProperty;
import editor.domain.Operation;
import editor.domain.Position;
import editor.domain.elementproperty.ColorProperty;

public class ChangeElementSize extends Operation {

    protected Position v1;
    protected Position v2;

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