package editor.domain.operation;

import editor.application.App;
import editor.domain.Element;
import editor.domain.Operation;
import editor.domain.Position;
import editor.domain.ToolbarElement;

public class AddToolbarElement extends Operation {

    protected Element toAdd;
    protected ToolbarElement created;

    public AddToolbarElement(Element toAdd) {
        this.toAdd = toAdd.Clone();
    }

    @Override
    public void Do() {
        created = App.model.getToolbar().addElement(toAdd);
    }

    @Override
    public void Undo() {
        if(created != null)
            App.model.getToolbar().removeElement(created);
    }

    @Override
    public boolean Doable() {
        return App.model != null;
    }
    
}