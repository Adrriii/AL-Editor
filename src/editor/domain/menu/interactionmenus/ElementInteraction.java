package editor.domain.menu.interactionmenus;

import editor.domain.Element;
import editor.domain.menu.Interaction;

/**
* An Interaction that will perform an action on an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class ElementInteraction extends Interaction {

    private static final long serialVersionUID = 1L;
    protected Element element;

    public ElementInteraction(String label, Element element) {
        super(label);
        this.element = element;
    }
}