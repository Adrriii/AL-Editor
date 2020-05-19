package editor.domain;

import java.io.Serializable;

/**
* A coordinate.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}