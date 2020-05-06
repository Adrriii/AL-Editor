package editor.domain.menu.interactionmenus;

import java.util.ArrayList;
import java.util.HashSet;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.ElementGroup;
import editor.domain.InvalidCompositeAddition;
import editor.domain.operation.DeGroupElements;

public class DegroupElementsInteraction extends ElementInteraction {

    public DegroupElementsInteraction(ElementGroup element) {
        super("Degroup", element);
    }

    @Override
    public void onClick() {

        AppController.actionControl.Do(new DeGroupElements((ElementGroup) element));
        
        App.model.setInteractionMenu(null); 
    }

}