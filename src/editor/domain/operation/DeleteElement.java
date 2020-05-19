package editor.domain.operation;

import editor.application.App;
import editor.domain.Element;
import editor.domain.Position;


/**
* An operation aiming at removing an element from the whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
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