package editor.domain.operation;

import java.util.ArrayList;
import java.util.HashSet;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;
import editor.domain.Operation;
import editor.domain.Position;

public class DeGroupElements extends GroupElements {

    public DeGroupElements(ElementGroup group) {
        super(null);
        this.group = group;
    }

    @Override
    public void Do() {
        super.Undo();
    }

    @Override
    public void Undo() {
        super.Do();
    }
    
}
