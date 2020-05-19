package editor.domain.menu.interactionmenus;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.control.ColorPicker;
import editor.domain.elementproperty.ColorProperty;
import editor.domain.operation.ChangeElementProperty;

/**
* An Interaction aiming at changing the color of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ColorChangeInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

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
