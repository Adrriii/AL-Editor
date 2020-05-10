package editor.domain.menu.interactionmenus;
import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.operation.DeleteElement;

public class DeleteElementInteraction extends ElementInteraction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DeleteElementInteraction(Element element) {
        super("Delete", element);
    }

    @Override
    public void onClick() {

        AppController.actionControl.Do(new DeleteElement(element, element.pos));
        
        App.model.setInteractionMenu(null); 
    }

}