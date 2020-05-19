package editor.userinterface.views;

import editor.application.App;
import editor.domain.Canvas;
import editor.userinterface.ViewScope;

/**
* The main part of the window, in which the whiteboard is contained.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewCanvas implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
        App.view.Clear(GetScopeName());
    }

    @Override
    public void Tick() {
        if(!invalidated) return;

        Canvas canvas = App.model.getCanvas();
        
        canvas.Draw(GetScopeName());

        invalidated = false;
    }

    @Override
    public String GetScopeName() {
        return "canvas";
    }

}