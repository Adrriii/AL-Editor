package editor.model.menu.interactionmenus;

import editor.application.App;
import editor.controller.AppController;
import editor.model.ElementGroup;
import editor.controller.operation.DeGroupElements;

/**
* An Interaction aiming at splitting an element group into its children.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class DegroupElementsInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    /**
     * Initiate an interaction used to degroup Elements.
     * 
     * @param element The element in question
     */
    public DegroupElementsInteraction(ElementGroup element) {
        super("Degroup", element);
    }

    @Override
    public void onClick() {

        AppController.actionControl.Do(new DeGroupElements((ElementGroup) element));
        
        App.model.setInteractionMenu(null); 
    }

}