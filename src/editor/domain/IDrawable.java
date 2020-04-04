package editor.domain;

import editor.userinterface.View;

public interface IDrawable {

    public void Attach(View view);
    public void Detach(View view);
    public void Notify();
    public void Draw();
    
}