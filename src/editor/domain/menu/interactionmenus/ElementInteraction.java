package editor.domain.menu.interactionmenus;

import editor.domain.Element;
import editor.domain.menu.Interaction;

public abstract class ElementInteraction extends Interaction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected Element element;

    public ElementInteraction(String label, Element element) {
        super(label);
        this.element = element;
    }
}