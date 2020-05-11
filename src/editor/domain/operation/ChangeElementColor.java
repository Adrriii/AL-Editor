package editor.domain.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementProperty;
import editor.domain.Operation;
import editor.domain.elementproperty.ColorProperty;

public class ChangeElementColor extends Operation {

    protected ElementProperty from;
    protected ElementProperty to;

    public ChangeElementColor(Element element, ColorProperty from, ColorProperty to) {
        state = element;
        this.from = from.Clone();
        this.to = to.Clone();
    }

    @Override
    public void Do() {
        ((Element) state).properties.put("color", to);
    }

    @Override
    public void Undo() {
        ((Element) state).properties.put("color", from);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}