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
        ArrayList<Element> elements = new ArrayList<>(((ElementGroup) element).getElements());

        element.Detach(App.view.getCanvasView());
        App.model.getCanvas().removeElement(element);
        
        App.model.DeselectAll();

        elements.forEach(out -> {
            try {
                element.Remove(out);
            }
            catch (InvalidCompositeAddition e) {
                e.printStackTrace();
            }
            App.model.getCanvas().addElement(out);
            out.Attach(App.view.getCanvasView());
            App.model.Select(out);
        });
        
        App.model.setInteractionMenu(null); 
    }

}