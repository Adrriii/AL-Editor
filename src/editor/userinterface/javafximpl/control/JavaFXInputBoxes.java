package editor.userinterface.javafximpl.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.UnaryOperator;

import editor.domain.Position;
import editor.domain.control.InputBoxes;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class JavaFXInputBoxes extends InputBoxes {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Dialog<Color> dial;
    private String[] texts;
    private ArrayList<TextField> fxInputBoxes;

    public JavaFXInputBoxes(String[] labels) {
        this.texts = labels;
        fxInputBoxes = new ArrayList<>();
    }

    @Override
    public void Draw(Position pos) {
    }

    @Override
    public void show() {
        UnaryOperator<Change> filter = change -> {
            String text = change.getText();
        
            if (text.matches("[0-9]*")) {
                return change;
            }
        
            return null;
        };
        
        GridPane grid = new GridPane();
        int x = 1;
        for(String text : this.texts) {
            Label label = new Label(text);
            TextField textField = new TextField();
            textField.setTextFormatter(new TextFormatter<>(filter));
            fxInputBoxes.add(textField);
            
            grid.add(label, 1, x);
            grid.add(textField, 2, x);
            x++;
        }
        
        dial = new Dialog<>();
        
        dial.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.OK, ButtonType.CANCEL);

        dial.getDialogPane().setContent(grid);
    }

    @Override
    public void setValues(Integer[] values) {
        Iterator<TextField> iter = fxInputBoxes.iterator();
        for(Integer val : values) {
            iter.next().setText(Integer.toString(val));
        }
    }

    @Override
    public Integer[] getValues() {
        dial.showAndWait();

        Integer[] values = new Integer[fxInputBoxes.size()];
        int x = 0;
        for(TextField box : fxInputBoxes) {
            values[x++] = Integer.parseInt(box.getText());
        }

        return values;
    }

}