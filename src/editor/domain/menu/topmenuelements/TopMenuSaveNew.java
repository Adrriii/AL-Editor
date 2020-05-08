package editor.domain.menu.topmenuelements;

import editor.application.App;
import editor.domain.AppController;
import editor.domain.Position;
import editor.domain.element.Rectangle;
import editor.domain.menu.TopMenuElement;

public class TopMenuSaveNew extends TopMenuElement {

    public TopMenuSaveNew(int width, int height) {
        super(width, height);
    }

    @Override
    public void Draw(Position ref) {
        if(true) { // Can save
            App.view.drawImage(App.getFilePath("assets/img/save_new.png"), ref.x, ref.y, width, height);
        } else {
            App.view.drawImage(App.getFilePath("assets/img/save_new_grey.png"), ref.x, ref.y, width, height);
        }
    }

    @Override
    public void onClick() {
        //AppController.actionControl.SaveCanvas();
    }
    
}