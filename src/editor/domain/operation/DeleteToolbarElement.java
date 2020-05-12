package editor.domain.operation;

import editor.application.App;
import editor.domain.ToolbarElement;

public class DeleteToolbarElement extends AddToolbarElement {

    public DeleteToolbarElement(ToolbarElement toAdd) {
        super(toAdd.getElement());
        created = toAdd;
    }

    @Override
    public void Do() {
        super.Undo();
    }

    @Override
    public void Undo() {
        super.Do();
    }

    @Override
    public boolean Doable() {
        return App.model != null;
    }
    
}