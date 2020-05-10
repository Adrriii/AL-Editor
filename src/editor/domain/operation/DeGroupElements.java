package editor.domain.operation;

import editor.domain.ElementGroup;

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
