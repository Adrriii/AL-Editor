package editor.domain;

import editor.application.App;

public class DeleteElementTool extends Element {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DeleteElementTool(Position pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    @Override
    public void Draw(Position ref) {
        Draw(ref,width,height);
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
    public void Draw(Position ref, int fit_width, int fit_height) {
        if(!isClicked(App.appController.CurrentMousePos())) 
            App.view.drawImage("assets/img/bin.png", ref.x, ref.y, fit_width, fit_height);
        else
            App.view.drawImage("assets/img/bin_hover.png", ref.x, ref.y, fit_width, fit_height);
    }
    
}