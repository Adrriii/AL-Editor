package editor.domain;

public interface IControllable {

    public void Update(int new_pos_x, int new_pos_y);

    public boolean isClicked(int x, int y);

}