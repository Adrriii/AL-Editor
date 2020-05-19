package editor.domain.menu.interactionmenus;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Element;
import editor.domain.Position;
import editor.domain.control.InputBoxes;
import editor.domain.operation.ChangeElementSize;

/**
* An Interaction aiming at changing the dimensions an element.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class DimensionChangeInteraction extends ElementInteraction {

    private static final long serialVersionUID = 1L;

    public DimensionChangeInteraction(Element element) {
        super("Change size", element);
    }

    @Override
    public void onClick() {

        String[] labels = {"Width: ", "Height: "};
        Integer[] values = {element.width, element.height};
        InputBoxes control = App.controlFactory.GetInputBoxes(labels);

        App.model.AddControl(control);
        control.show();

        control.setValues(values);
        values = control.getValues();
        if (values != null) AppController.actionControl.Do(new ChangeElementSize(element,new Position(element.width,element.height),new Position(values[0], values[1])));

        App.model.RemoveControl(control);
    }

}
