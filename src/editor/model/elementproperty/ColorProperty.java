package editor.model.elementproperty;

import editor.model.ElementProperty;

/**
* A Color belonging to an Element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ColorProperty extends ElementProperty {

    private static final long serialVersionUID = 1L;

    /**
     * The composition of this color
     */
    public int r, g, b, a;

    /**
     * Create a new Color property
     * 
     * @param r Red composition (0-255)
     * @param g Green composition (0-255)
     * @param b Blue composition (0-255)
     * @param a Alpha channel (0-255)
     */
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