package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.menu.TopMenuElement;
import editor.domain.operation.LoadCanvas;

/**
* A button that triggers the loading of a new whiteboard.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class TopMenuLoad extends TopMenuElement {

    private static final long serialVersionUID = 1L;

    public TopMenuLoad(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(String viewName, Position ref) {
        if(!isClicked(App.appController.CurrentMousePos())) 
            App.view.drawImage(viewName, "assets/img/load.png", ref.x, ref.y, width, height);
        else
            App.view.drawImage(viewName, "assets/img/load_hover.png", ref.x, ref.y, width, height);
    }

    @Override
    public void onClick() {
        AppController.currentCanvasPath = App.controller.getChosenFile();
        (new LoadCanvas()).Do();
    }
    
}