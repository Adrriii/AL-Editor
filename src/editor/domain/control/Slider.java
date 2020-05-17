package editor.domain.control;

import editor.domain.Control;

public abstract class Slider extends Control {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected String text;
    protected int value, lower, upper;

    public Slider(String text, int value, int lower, int upper) {
        this.text = text;
        this.value = value;
        this.lower = lower;
        this.upper = upper;
    }

    public Slider(String text, int lower, int upper) {
        this(text, lower + (upper-lower)/2, lower, upper);
    }

    public Slider(String text) {
        this(text, 50, 0, 100);
    }

    abstract public void setValue(int value);
    abstract public int getValue();

}