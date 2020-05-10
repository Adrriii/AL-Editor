package editor.domain;

import java.util.Stack;

import editor.application.App;

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
        App.view.getTopMenuView().Update();
    }

    public void Undo() {
        if(operations.empty()) return;
        Operation toUndo = operations.pop();
        toUndo.Undo();
        reversed.push(toUndo);
        App.view.getTopMenuView().Update();
    }

    public void Redo() {
        if(reversed.empty()) return;
        Operation toRedo = reversed.pop();
        toRedo.Do();
        operations.push(toRedo);
        App.view.getTopMenuView().Update();
    }

    public void Clear() {
        operations.clear();
        reversed.clear();
    }

    public Operation peekLastOperation() {
        return operations.peek();
    }

    public boolean CanUndo() {
        return !operations.empty();
    }

    public boolean CanRedo() {
        return !reversed.empty() && reversed.peek().Doable();
    }
    
}
