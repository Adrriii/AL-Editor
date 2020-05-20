package editor.model.menu.topmenuelements;

import editor.application.App;
import editor.controller.AppController;
import editor.model.Position;
import editor.model.menu.TopMenuElement;

/**
* A button that triggers the Redo action.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TopMenuRedo extends TopMenuElement {

    private static final long serialVersionUID = 1L;

    public TopMenuRedo(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(String viewName, Position ref) {
        if(AppController.actionControl.CanRedo()) {
            if(!isClicked(App.appController.CurrentMousePos())) 
                App.view.drawImage(viewName, "assets/img/redo.png", ref.x, ref.y, width, height);
            else
                App.view.drawImage(viewName, "assets/img/redo_hover.png", ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(viewName, "assets/img/redo_grey.png", ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        AppController.actionControl.Redo();
    }
    
}