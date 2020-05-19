package editor.domain.elementproperty;

import editor.domain.ElementProperty;

/**
* A Color belonging to an Element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ColorProperty extends ElementProperty {

    private static final long serialVersionUID = 1L;
    public int r;
    public int g;
    public int b;
    public int a;

    public ColorProperty(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public ElementProperty Clone() {
        return new ColorProperty(r, g, b, a);
    }
}