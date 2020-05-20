package editor.domain.operation;

import editor.application.App;
import editor.domain.Element;
import editor.domain.Operation;
import editor.domain.Position;


/**
* An operation aiming at adding a new element to the whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class CreateElement extends Operation {

    /**
     * The element that has been created by the operation
     */
    protected Element created;

    /**
     * Where the element has been placed while being created
     */
    protected Position location;

    /**
     * Create a new element in the whiteboard 
     * 
     * @param pattern The element used as a pattern to create the new one
     * @param location Where to position the new element on its creation
     */
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