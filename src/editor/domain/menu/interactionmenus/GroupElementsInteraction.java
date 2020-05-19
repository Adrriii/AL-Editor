package editor.domain.menu.interactionmenus;

import java.util.ArrayList;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.operation.GroupElements;

/**
* An Interaction aiming at grouping elements into a single ElementGroup.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class GroupElementsInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    public GroupElementsInteraction(Element element) {
        super("Group", element);
    }

    @Override
    public void onClick() {
        ArrayList<Element> selected = App.model.getSelectedElements();

        AppController.actionControl.Do(new GroupElements(selected));
        
        App.model.setInteractionMenu(null);
    }

}