package editor.domain;

import java.util.Stack;

public class ActionControl {

    private Stack<Operation> operations;
    private Stack<Operation> reversed;

    public ActionControl() {
        operations = new Stack<>();
        reversed = new Stack<>();
    }

    public void Do(Operation operation) {
        reversed.clear();
        operation.Do();
        operations.push(operation);
    }

    public void Undo() {
        if(operations.empty()) return;
        Operation toUndo = operations.pop();
        toUndo.Undo();
        reversed.push(toUndo);
    }

    public void Redo() {
        if(reversed.empty()) return;
        Operation toRedo = reversed.pop();
        toRedo.Do();
        operations.push(toRedo);
    }
    
}
