package editor.domain;

public interface IControllable {

    public Position Update(Position new_pos);

    public boolean isClicked(Position pos);

}