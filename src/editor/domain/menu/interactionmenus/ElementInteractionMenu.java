package editor.domain.menu.interactionmenus;

import editor.application.App;
import editor.domain.Element;
import editor.domain.menu.InteractionMenu;

public class ElementInteractionMenu extends InteractionMenu {

    protected Element element;

    public ElementInteractionMenu(int x, int y, Element element) {
        super(x,y);

        SetInteractions();
    }

    @Override
    protected void SetInteractions() {
        this.interactions.add(new GroupElementsInteraction(element));
        this.interactions.add(new ColorChangeInteraction(element));
    }

}