// UndoRedoStack is a variation of the Stack class that supports an undo
// operation for reversing the effect of push and pop operations and a redo
// operation that reverses undo operations.  The canUndo method indicates
// whether an undo is legal.  The canRedo operation indicates whether a redo is
// legal (sequences of undo operations can be redone, but once a push or pop
// operation is performed, it is not possible to redo any undo operations until
// another call is made on undo).

import java.util.*;

public class UndoRedoStack<E> extends Stack<E> {
    private Stack undoStack;
    private Stack redoStack;

    // post: constructs an empty UndoRedoStack
    public UndoRedoStack() {
        undoStack = new Stack();
        redoStack = new Stack();
    }

    // post: pushes and returns the given value on top of the stack
    public E push(E value) {
        super.push(value);
        undoStack.push("push");
        redoStack.clear();
        return value;
    }

    // post: pops and returns the value at the top of the stack
    public E pop() {
        E value = super.pop();
        undoStack.push(value);
        undoStack.push("pop");
        redoStack.clear();
        return value;
    }

    // post: returns whether or not an undo can be done
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    // pre : canUndo() (throws IllegalStateException if not)
    // post: undoes the last stack push or pop command
    public void undo() {
        if (!canUndo()) {
            throw new IllegalStateException();
        }
        Object action = undoStack.pop();
        if (action.equals("push")) {
            E value = super.pop();
            redoStack.push(value);
            redoStack.push("push");
        } else {
            E value = (E) undoStack.pop();         
            super.push(value);
            redoStack.push("pop");
        }
    }

    // post: returns whether or not a redo can be done
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    // pre : canRedo() (throws IllegalStateException if not)
    // post: redoes the last undone operation
    public void redo() {
        if (!canRedo()) {
            throw new IllegalStateException();
        }
        Object action = redoStack.pop();
        if (action.equals("push")) {
            E value = (E) redoStack.pop();
            super.push(value);
            undoStack.push("push");
        } else {
            E value = super.pop();
            undoStack.push(value);
            undoStack.push("pop");
        }
    }
}
