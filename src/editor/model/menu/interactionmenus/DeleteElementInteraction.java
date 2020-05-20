package editor.model.menu.interactionmenus;
import editor.application.App;
import editor.controller.AppController;
import editor.model.Element;
import editor.controller.operation.DeleteElement;

/**
* An Interaction aiming at deleting an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class DeleteElementInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    /**
     * Initiate an interaction used to delete an Element.
     * 
     * @param element The element in question
     */
    public DeleteElementInteraction(Element element) {
        super("Delete", element);
    }

    @Override
    public void onClick() {

        AppController.actionControl.Do(new DeleteElement(element, element.pos));
        
        App.model.setInteractionMenu(null); 
    }

}