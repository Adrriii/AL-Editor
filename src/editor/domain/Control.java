package editor.domain;

/**
* A displayable control allowing to interact with the user.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public abstract class Control extends Drawable {

    private static final long serialVersionUID = 1L;

    abstract public void show();

}