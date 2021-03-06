package editor.model.menu.interactionmenus;

import editor.application.App;
import editor.controller.AppController;
import editor.model.Element;
import editor.userinterface.control.ColorPicker;
import editor.model.elementproperty.ColorProperty;
import editor.controller.operation.ChangeElementProperty;

/**
* An Interaction aiming at changing the color of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ColorChangeInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    /**
     * Initiate an interaction to modify the Color property of an Element.
     * 
     * @param element The element in question
     */
    public ColorChangeInteraction(Element element) {
        super("Change color", element);
    }

    @Override
    public void onClick() {

        ColorPicker colorPicker = App.controlFactory.GetColorPicker();

        App.model.AddControl(colorPicker);
        colorPicker.show();

        colorPicker.setColor((ColorProperty) element.properties.get("color"));
        ColorProperty color = colorPicker.getColor();
        if (color != null) AppController.actionControl.Do(new ChangeElementProperty(element,"color", element.properties.get("color"), color));

        App.model.RemoveControl(colorPicker);
    }

}
