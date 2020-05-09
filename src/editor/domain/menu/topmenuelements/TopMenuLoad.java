package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.menu.TopMenuElement;
import editor.domain.operation.LoadCanvas;

public class TopMenuLoad extends TopMenuElement {

    public TopMenuLoad(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(Position ref) {
        App.view.drawImage(App.getFilePath("assets/img/load.png"), ref.x, ref.y, width, height);
    }

    @Override
    public void onClick() {
        AppController.currentCanvasPath = App.controller.getChosenFile();
        (new LoadCanvas()).Do();
    }
    
}