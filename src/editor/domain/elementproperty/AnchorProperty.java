package editor.domain.elementproperty;

import editor.domain.ElementProperty;
import editor.domain.Position;

public class AnchorProperty extends ElementProperty {
    /**
     *
     */
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