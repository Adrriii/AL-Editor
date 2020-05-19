package editor.domain.operation;

import editor.domain.ElementGroup;


/**
* An operation aiming at doing the opposite of GroupElements.
* 
* @author Adrien Boitelle
* @version 1.0
*/
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
