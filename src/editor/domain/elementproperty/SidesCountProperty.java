package editor.domain.elementproperty;

import editor.domain.ElementProperty;

public class SidesCountProperty extends ElementProperty {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public int count;

    public SidesCountProperty(int count) {
        this.count = count;
    }

    @Override
    public ElementProperty Clone() {
        return new SidesCountProperty(count);
    }
}