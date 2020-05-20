package editor.model.menu.interactionmenus;

import editor.model.Element;
import editor.model.menu.Interaction;

/**
* An Interaction that will perform an action on an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class ElementInteraction extends Interaction {

    private static final long serialVersionUID = 1L;

    /**
     * An element which can be modified by the interaction.
     */
    protected Element element;

    /**
     * Initiate an interaction on an Element.
     * 
     * @param element The element in question
     */
    public ElementInteraction(String label, Element element) {
        super(label);
        this.element = element;
    }
}