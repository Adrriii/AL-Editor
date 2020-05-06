package editor.domain.operation;

import editor.domain.Element;
import editor.domain.Operation;
import editor.domain.Position;

public class MoveElement extends Operation {

    protected Position move_from;
    protected Position move_to;

    public MoveElement(Element element, Position from, Position to) {
        state = element;
        move_from = from;
        move_to = to;
    }

    @Override
    public void Do() {
        ((Element) state).Update(move_to);
    }

    @Override
    public void Undo() {
        ((Element) state).Update(move_from);
    }
    
}