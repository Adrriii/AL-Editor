package editor.userinterface.javafximpl.control;

import editor.domain.Position;
import editor.domain.control.Slider;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class JavaFXSlider extends Slider {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Dialog<javafx.scene.control.Slider> dial;
    private javafx.scene.control.Slider slider;


    public JavaFXSlider(String text, int value, int lower, int upper) {
        super(text, value, lower, upper);
    }

    public JavaFXSlider(String text, int lower, int upper) {
        super(text, lower, upper);
    }

    public JavaFXSlider(String text) {
        super(text);
    }

    @Override
    public void Draw(String viewName, Position pos) {
    }

    @Override
    public void show() {
        
        GridPane grid = new GridPane();
        Label label = new Label(text);
        slider = new javafx.scene.control.Slider(lower, upper, value);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
            
        grid.add(label, 1, 1);
        grid.add(slider, 2, 1);
        
        dial = new Dialog<>();
        
        dial.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.OK, ButtonType.CANCEL);

        dial.getDialogPane().setContent(grid);
    }

    @Override
    public void setValue(int value) {
        slider.setValue(value);
    }

    @Override
    public int getValue() {
        dial.showAndWait();
        return (int) slider.getValue();
    }

}