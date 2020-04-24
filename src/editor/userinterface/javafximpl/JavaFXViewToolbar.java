package editor.userinterface.javafximpl;

import editor.application.App;
import editor.domain.Toolbar;
import editor.userinterface.ViewScope;

public class JavaFXViewToolbar implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        Toolbar toolbar = App.model.getToolbar();

        toolbar.Draw();
        // drawJavaFXRectangle(toolbar.pos_x, toolbar.pos_y, toolbar.width, toolbar.height, Color.GREY);

        // drawToolbarElements(toolbar);

        invalidated = false;
    }

}