package editor.controller.operation;

import editor.application.App;
import editor.model.Element;
import editor.model.Position;


/**
* An operation aiming at removing an element from the whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class DeleteElement extends CreateElement {

    /**
     * Do the opposite of creating an element
     * 
     * @param pattern The element to destroy
     * @param location The position of the element when it is destroyed
     */
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