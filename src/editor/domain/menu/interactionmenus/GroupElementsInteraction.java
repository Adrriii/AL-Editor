package editor.domain.menu.interactionmenus;

import java.util.HashSet;

import editor.application.App;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;

public class GroupElementsInteraction extends ElementInteraction {

    public GroupElementsInteraction(Element element) {
        super("Group", element);
    }

    @Override
    public void onClick() {
        HashSet<Element> selected = App.model.getSelectedElements();


        ElementGroup group = new ElementGroup();

        selected.forEach(element -> {
            try {
                group.Add(element);
                App.model.getCanvas().removeElement(element);
            } catch (InvalidCompositeAddition e) {
                e.printStackTrace();
            }
        });
        
        App.model.getCanvas().addElement(group);
        App.model.DeselectAll();
        App.model.Select(group);
        App.model.setInteractionMenu(null);
    }

}