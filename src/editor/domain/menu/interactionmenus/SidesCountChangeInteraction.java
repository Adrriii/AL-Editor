package editor.domain.menu.interactionmenus;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.control.InputBoxes;
import editor.domain.element.RegularPolygon;
import editor.domain.elementproperty.SidesCountProperty;
import editor.domain.operation.ChangeElementProperty;

/**
* An Interaction aiming at changing the amount of sides of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class SidesCountChangeInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    public SidesCountChangeInteraction(RegularPolygon element) {
        super("Change sides", element);
    }

    @Override
    public void onClick() {

        String[] labels = {"Sides count: "};
        Integer[] values = {(int) ((SidesCountProperty) element.properties.get("sidescount")).value};
        InputBoxes control = App.controlFactory.GetInputBoxes(labels);

        App.model.AddControl(control);
        control.show();

        control.setValues(values);
        values = control.getValues();
        if (values != null) {
            AppController.actionControl.Do(
                new ChangeElementProperty(element,
                                            "sidescount",
                                            element.properties.get("sidescount"),
                                            new SidesCountProperty(values[0])
                                        )
                );
            ((RegularPolygon) element).UpdateSides();
        }

        App.model.RemoveControl(control);
    }

}
