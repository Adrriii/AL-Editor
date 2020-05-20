package editor.domain;

import java.util.Stack;

import editor.application.App;

/**
* Controls a series of operations by allowing to Do, Undo and Redo them.
* 
* @author Adrien Boitelle
* @version 1.0
*/
public class ActionControl {

    /**
     * A Stack of the operations that have been Done
     */
    private Stack<Operation> operations;

    /**
     * A Stack of the operations that have been Undone
     */
    private Stack<Operation> reversed;

    /**
     * Initialize a new ActionControl with a clear history
     */
    public ActionControl() {
        operations = new Stack<>();
        reversed = new Stack<>();
    }

    /**
    * Performs and operation and allows for it to be undone.
    * If some operations were still able to be redone, they will be lost.
    * 
    * @param operation The operation to be performed.
    */
    public void Do(Operation operation) {
        reversed.clear();
        operation.Do();
        operations.push(operation);
        App.view.getTopMenuView().Update();
    }

    /**
    * Undoes the previously done operation.
    */
    public void Undo() {
        if(operations.empty()) return;
        Operation toUndo = operations.pop();
        toUndo.Undo();
        reversed.push(toUndo);
        App.view.getTopMenuView().Update();
    }


    /**
    * Redoes the previously undone operation.
    */
    public void Redo() {
        if(reversed.empty()) return;
        Operation toRedo = reversed.pop();
        toRedo.Do();
        operations.push(toRedo);
        App.view.getTopMenuView().Update();
    }

    /**
    * Completely wipes the memory of operations.
    */
    public void Clear() {
        operations.clear();
        reversed.clear();
    }

    /**
    * Returns the latest done operation without affecting the stack.
    */
    public Operation peekLastOperation() {
        return operations.peek();
    }

    /**
    * Whether it is currently possible to undo an action.
    */
    public boolean CanUndo() {
        return !operations.empty();
    }

    /**
    * Whether it is currently possible to redo an action.
    */
    public boolean CanRedo() {
        return !reversed.empty() && reversed.peek().Doable();
    }
    
}
