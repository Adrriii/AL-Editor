package editor.userinterface.views;

import editor.application.App;
import editor.domain.menu.TopMenu;
import editor.userinterface.ViewScope;

/**
* The top part of the window, containing some action buttons.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ViewTopMenu implements ViewScope {

    public boolean invalidated = true;

    @Override
    public void Update() {
        invalidated = true;
        App.view.Clear(GetScopeName());
    }

    @Override
    public void Tick() {
        if(!invalidated) return;
        TopMenu topMenu = App.model.getTopMenu();

        topMenu.Draw(GetScopeName());

        invalidated = false;
    }

    @Override
    public String GetScopeName() {
        return "topmenu";
    }

}