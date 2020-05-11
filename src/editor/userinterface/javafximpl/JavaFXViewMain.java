package editor.userinterface.javafximpl;

import editor.application.App;
import editor.userinterface.ViewScope;

public class JavaFXViewMain implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
    }

    @Override
    public void Tick() {
        if(!App.ready) return;
        if(invalidated) {
            App.view.getCanvasView().Update();
            App.view.getToolbarView().Update();
            App.view.getTopMenuView().Update();
        }

        App.view.getCanvasView().Tick();
        App.view.getToolbarView().Tick();
        App.view.getTopMenuView().Tick();

        if(!invalidated) return;

        App.model.getControls().forEach(control -> control.Draw());
        if (App.model.getInteractionMenu() != null) App.model.getInteractionMenu().Draw();

        invalidated = false;
    }

}