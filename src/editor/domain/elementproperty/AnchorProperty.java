package editor.domain.elementproperty;

import editor.domain.ElementProperty;
import editor.domain.Position;

/**
* A Position to which a surface is relative.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class AnchorProperty extends ElementProperty {

    private static final long serialVersionUID = 1L;
    public Position value;

    public AnchorProperty(Position value) {
        this.value = new Position(value.x, value.y);
    }

    @Override
    public ElementProperty Clone() {
        return new AnchorProperty(value);
    }
}