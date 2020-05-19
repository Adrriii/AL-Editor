package editor.userinterface.views;

import editor.application.App;
import editor.userinterface.ViewScope;

/**
* The whole window, and floating elements.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewMain implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
        App.view.Clear(GetScopeName());
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

        App.model.getControls().forEach(control -> control.Draw(GetScopeName()));
        if (App.model.getInteractionMenu() != null) App.model.getInteractionMenu().Draw(GetScopeName());

        invalidated = false;
    }

    @Override
    public String GetScopeName() {
        return "main";
    }

}