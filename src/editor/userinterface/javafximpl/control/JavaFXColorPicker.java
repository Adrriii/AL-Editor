package editor.userinterface.javafximpl.control;

import editor.domain.Position;
import editor.domain.control.ColorPicker;
import editor.domain.elementproperty.ColorProperty;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.paint.Color;

/**
* An implementation of the ColorPicker in JavaFX.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class JavaFXColorPicker extends ColorPicker {

    private static final long serialVersionUID = 1L;

    private Dialog<Color> dial;
    private javafx.scene.control.ColorPicker fxColorPicker;

    @Override
    public void Draw(String viewName, Position pos) {
    }

    @Override
    public void show() {
        fxColorPicker = new javafx.scene.control.ColorPicker();
        dial = new Dialog<>();
        
        dial.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.OK, ButtonType.CANCEL);

        dial.getDialogPane().setContent(fxColorPicker);
    }

    @Override
    public void setColor(ColorProperty color) {
        fxColorPicker.setValue(new Color(color.r / 255.0,color.g / 255.0,color.b / 255.0, color.a / 255.0));
    }

    @Override
    public ColorProperty getColor() {
        dial.showAndWait();
        
        Color fxColor = fxColorPicker.getValue();
        ColorProperty color = new ColorProperty((int) (fxColor.getRed() * 255), (int) (fxColor.getGreen() * 255), (int) (fxColor.getBlue() * 255), (int) (fxColor.getOpacity() * 255));

        return color;
    }

}