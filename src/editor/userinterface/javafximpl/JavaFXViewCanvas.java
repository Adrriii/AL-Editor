package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.Canvas;
import editor.userinterface.ViewScope;

public class JavaFXViewCanvas implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
    }

    @Override
    public void Tick() {
        if(!invalidated) return;

        Canvas canvas = App.model.getCanvas();
        
        canvas.Draw();

        invalidated = false;
    }

}