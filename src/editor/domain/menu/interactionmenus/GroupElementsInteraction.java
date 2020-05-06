package editor.domain.menu.interactionmenus;

import java.util.HashSet;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;
import editor.domain.operation.GroupElements;

public class GroupElementsInteraction extends ElementInteraction {

    public GroupElementsInteraction(Element element) {
        super("Group", element);
    }

    @Override
    public void onClick() {
        HashSet<Element> selected = App.model.getSelectedElements();

        AppController.actionControl.Do(new GroupElements(selected));
        
        App.model.setInteractionMenu(null);
    }

}