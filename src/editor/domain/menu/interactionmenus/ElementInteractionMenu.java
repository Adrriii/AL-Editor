package editor.domain.menu.interactionmenus;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementGroup;
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
        
        if(new ArrayList<Element>(App.model.getSelectedElements()).size() > 1) {
            this.addInteraction(new GroupElementsInteraction(element));
        } 
        if (new ArrayList<Element>(App.model.getSelectedElements()).size() == 1 && element.getClass().isAssignableFrom(ElementGroup.class)) {
            this.addInteraction(new DegroupElementsInteraction((ElementGroup) element));
        }
        this.addInteraction(new ColorChangeInteraction(element));
        this.addInteraction(new DimensionChangeInteraction(element));
        this.addInteraction(new DeleteElementInteraction(element));
    }

}