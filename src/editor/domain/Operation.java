package editor.domain;

public abstract class Operation {

    protected Object state;
    
    abstract public void Do();

    abstract public void Undo();

    abstract public boolean Doable();
    
}