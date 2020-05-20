package editor.domain.operation;

import editor.application.App;
import editor.domain.ToolbarElement;


/**
* An operation aiming at removing a tool from the toolbar.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class DeleteToolbarElement extends AddToolbarElement {

    /**
     * Do the opposite of adding a toolbar element
     * 
     * @param toAdd The toolbar element to delete
     */
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