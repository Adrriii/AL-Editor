package editor.domain.operation;

import editor.application.App;
import editor.domain.Element;
import editor.domain.Position;

public class DeleteElement extends CreateElement {

    public DeleteElement(Element pattern, Position location) {
        super(pattern, location);
        created = pattern;
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