package editor.model.menu.topmenuelements;

import editor.application.App;
import editor.controller.AppController;
import editor.model.Position;
import editor.model.menu.TopMenuElement;

/**
* A button that triggers the Undo action.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TopMenuUndo extends TopMenuElement {

    private static final long serialVersionUID = 1L;

    public TopMenuUndo(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(String viewName, Position ref) {
        if(AppController.actionControl.CanUndo()) {
            if(!isClicked(App.appController.CurrentMousePos())) 
                App.view.drawImage(viewName, "assets/img/undo.png", ref.x, ref.y, width, height);
            else
                App.view.drawImage(viewName, "assets/img/undo_hover.png", ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(viewName, "assets/img/undo_grey.png", ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        AppController.actionControl.Undo();
    }
    
}