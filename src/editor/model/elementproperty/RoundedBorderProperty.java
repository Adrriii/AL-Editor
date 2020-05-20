package editor.model.elementproperty;

import editor.model.ElementProperty;

/**
* An Element's homogeneously rounded borders property.
* Defined by an arc.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class RoundedBorderProperty extends ElementProperty {

    private static final long serialVersionUID = 1L;

    /**
     * The arc width and height of the rounding
     */
    public double h, w;

    /**
     * Create a new RoundedBorder property defined by a width and a height
     * 
     * @param h The height of the arc (0 = no rounding)
     * @param w The width of the arc (0 = no rounding)
     */
    public RoundedBorderProperty(double h, double w) {
        this.h = h;
        this.w = w;
    }

    @Override
    public ElementProperty Clone() {
        return new RoundedBorderProperty(h,w);
    }
}