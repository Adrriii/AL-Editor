package editor.domain.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementProperty;
import editor.domain.Operation;

public class ChangeElementProperty extends Operation {

    protected String propertyName;
    protected ElementProperty from;
    protected ElementProperty to;

    public ChangeElementProperty(Element element, String propertyName, ElementProperty from, ElementProperty to) {
        state = element;
        this.propertyName = propertyName;
        this.from = from.Clone();
        this.to = to.Clone();
    }

    @Override
    public void Do() {
        ((Element) state).properties.put(propertyName, to);
    }

    @Override
    public void Undo() {
        ((Element) state).properties.put(propertyName, from);
    }

    @Override
    public boolean Doable() {
        return true;
    }
    
}