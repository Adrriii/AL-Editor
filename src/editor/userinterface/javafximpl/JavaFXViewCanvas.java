package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.Canvas;
import editor.userinterface.ViewScope;

public class JavaFXViewCanvas implements ViewScope {

    @Override
    public void Update() {

        Canvas canvas = App.model.getCanvas();
        
        canvas.Draw();
    }

}