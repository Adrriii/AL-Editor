package editor.userinterface;

import editor.domain.IControllable;

public interface Controller {

    public void Attach(IControllable controllable);
    public void Detach(IControllable controllable);

    public String getChosenFile();
    public String getChosenSaveFile();

}