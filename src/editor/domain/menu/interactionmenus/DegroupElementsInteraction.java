package editor.domain.menu.interactionmenus;

import java.util.ArrayList;
import java.util.HashSet;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;

public class DegroupElementsInteraction extends ElementInteraction {

    public DegroupElementsInteraction(ElementGroup element) {
        super("Degroup", element);
    }

    @Override
    public void onClick() {
        ArrayList<Element> elements = ((ElementGroup) element).getElements();
        element.Detach(App.view.getCanvasView());
        App.model.getCanvas().removeElement(element);
        
        App.model.DeselectAll();

        elements.forEach(element -> {
            App.model.getCanvas().addElement(element);
            element.Attach(App.view.getCanvasView());
            App.model.Select(element);
        });
        
        App.model.setInteractionMenu(null);
    }

}