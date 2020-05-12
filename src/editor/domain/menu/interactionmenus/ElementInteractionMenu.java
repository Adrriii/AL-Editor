package editor.domain.menu.interactionmenus;

import editor.domain.Element;
import editor.domain.menu.Interaction;
import editor.domain.menu.InteractionMenu;

public class ElementInteractionMenu extends InteractionMenu {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected Element element;

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