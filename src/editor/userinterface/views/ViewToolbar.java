package editor.userinterface.views;

import editor.application.App;
import editor.domain.Toolbar;
import editor.userinterface.ViewScope;

public class ViewToolbar implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
        App.view.Clear(GetScopeName());
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        Toolbar toolbar = App.model.getToolbar();

        toolbar.Draw(GetScopeName());
        // drawJavaFXRectangle(toolbar.pos_x, toolbar.pos_y, toolbar.width, toolbar.height, Color.GREY);

        // drawToolbarElements(toolbar);

        invalidated = false;
    }

    @Override
    public String GetScopeName() {
        return "toolbar";
    }

}