package editor.domain.operation;

import editor.application.App;
import editor.domain.Element;
import editor.domain.Operation;
import editor.domain.Position;

public class CreateElement extends Operation {

    protected Element created;
    protected Position location;

    public CreateElement(Element pattern, Position location) {
        this.created = pattern.Clone();
        this.location = location;
    }

    @Override
    public void Do() {
        created = App.model.addExistingElement(created,location);
    }

    @Override
    public void Undo() {
        if(created == null) return;

        App.model.removeElement(created);
        App.view.getCanvasView().Update();
    }

    public Element getCreatedElement() {
        return created;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    @Override
    public boolean Doable() {
        return App.model != null;
    }
    
}