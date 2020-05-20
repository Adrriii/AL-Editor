package editor.userinterface;

import editor.application.App;

/**
 * A part of the screen that can be redrawn independently from the others.
 * 
 * @author Adrien Boitelle
 * @version 1.0
 */
public abstract class ViewScope {

    public boolean invalidated = true;

    public void Update() {
        invalidated = true;
        App.view.Clear(GetScopeName());
    }

    abstract public void Tick();

    abstract public String GetScopeName();
}