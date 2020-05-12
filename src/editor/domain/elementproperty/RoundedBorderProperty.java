package editor.domain.elementproperty;

import editor.domain.ElementProperty;

public class RoundedBorderProperty extends ElementProperty {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public double h;
    public double w;

    public RoundedBorderProperty(double h, double w) {
        this.h = h;
        this.w = w;
    }

    @Override
    public ElementProperty Clone() {
        return new RoundedBorderProperty(h,w);
    }
}