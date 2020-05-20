package editor.domain.menu.interactionmenus;

import editor.domain.Element;
import editor.domain.menu.Interaction;
import editor.domain.menu.InteractionMenu;

/**
* An Interaction Menu composed solely of Element Interactions.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ElementInteractionMenu extends InteractionMenu {

    private static final long serialVersionUID = 1L;

    /**
     * An Element that might be interacted with
     */
    protected Element element;

    /**
     * A Menu concerning an Element.
     * 
     * @param x The displayed X coordinate of this menu
     * @param y The displayed Y coordinate of this menu
     * @param element The Element that is concerned by the interactions
     */
    public ElementInteractionMenu(int x, int y, Element element) {
        super(x,y);

        this.element = element;

        SetInteractions();
    }

    @Override
    protected void SetInteractions() {
        for(Interaction inter : element.getAvailableInteractions()) {
            addInteraction(inter);
        }
    }

}