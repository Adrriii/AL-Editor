package editor.domain.menu.interactionmenus;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.ElementGroup;
import editor.domain.operation.DeGroupElements;

public class DegroupElementsInteraction extends ElementInteraction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DegroupElementsInteraction(ElementGroup element) {
        super("Degroup", element);
    }

    @Override
    public void onClick() {

        AppController.actionControl.Do(new DeGroupElements((ElementGroup) element));
        
        App.model.setInteractionMenu(null); 
    }

}