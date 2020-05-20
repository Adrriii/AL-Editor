package editor.model;

import java.io.Serializable;

/**
* A coordinate.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * A vector
     */
    public int x, y;

    /**
     * Create a new vector
     * @param x X position of the vector
     * @param y Y position of the vector
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}