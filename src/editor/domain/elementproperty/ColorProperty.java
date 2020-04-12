package editor.domain.elementproperty;

import editor.domain.ElementProperty;

public class ColorProperty extends ElementProperty {
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
}