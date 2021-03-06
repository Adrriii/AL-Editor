package editor.model.menu.interactionmenus;

import editor.application.App;
import editor.controller.AppController;
import editor.model.Element;
import editor.userinterface.control.Slider;
import editor.controller.operation.ChangeElementRotation;

/**
* An Interaction aiming at changing the rotation of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class RotationChangeInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    /**
     * Initiate an interaction used to rotate an Element.
     * 
     * @param element The element in question
     */
    public RotationChangeInteraction(Element element) {
        super("Rotate", element);
    }

    @Override
    public void onClick() {

        Slider slider = App.controlFactory.GetSlider("Rotation", element.rotation, -180, 180);

        App.model.AddControl(slider);
        slider.show();

        int rotation = slider.getValue();
        AppController.actionControl.Do(new ChangeElementRotation(element,element.rotation,rotation));

        App.model.RemoveControl(slider);
    }

}
