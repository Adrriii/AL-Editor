package editor.domain.menu.interactionmenus;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.control.InputBoxes;
import editor.domain.elementproperty.RoundedBorderProperty;
import editor.domain.operation.ChangeElementProperty;

/**
* An Interaction aiming at changing the borders rounding of an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class BordersChangeInteration extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    /**
     * Initiate an interaction to modify the RoundedBorders property of an Element.
     * 
     * @param element The element in question
     */
    public BordersChangeInteration(Element element) {
        super("Change corners", element);
    }

    @Override
    public void onClick() {

        String[] labels = {"Arc width: ", "Arc height: "};
        Integer[] values = {(int) ((RoundedBorderProperty) element.properties.get("roundedborders")).w, 
                            (int) ((RoundedBorderProperty) element.properties.get("roundedborders")).h};
        InputBoxes control = App.controlFactory.GetInputBoxes(labels);

        App.model.AddControl(control);
        control.show();

        control.setValues(values);
        values = control.getValues();
        if (values != null) 
            AppController.actionControl.Do(
                new ChangeElementProperty(element,
                                            "roundedborders",
                                            element.properties.get("roundedborders"),
                                            new RoundedBorderProperty(values[0], values[1])
                                        )
                );

        App.model.RemoveControl(control);
    }

}
