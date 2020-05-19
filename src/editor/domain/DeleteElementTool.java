package editor.domain;

import editor.application.App;

/**
* A special tool allowing to destroy an element
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class DeleteElementTool extends Element {

    private static final long serialVersionUID = 1L;

    public DeleteElementTool(Position pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    @Override
    public void Draw(String viewName, Position ref) {
        Draw(viewName, ref,width,height);
    }

    @Override
    public Element Clone() {
        return null;
    }

    @Override
    public int getSurfaceWidth() {
        return width;
    }

    @Override
    public int getSurfaceHeight() {
        return height;
    }

    @Override
    public void Draw(String viewName, Position ref, int fit_width, int fit_height) {
        if(!isClicked(App.appController.CurrentMousePos())) 
            App.view.drawImage(viewName, "assets/img/bin.png", ref.x, ref.y, fit_width, fit_height);
        else
            App.view.drawImage(viewName, "assets/img/bin_hover.png", ref.x, ref.y, fit_width, fit_height);
    }
    
}