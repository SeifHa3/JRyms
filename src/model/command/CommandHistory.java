package model.command;

import java.util.Stack;

public class CommandHistory {

    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandHistory() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void push(Command c) {
        undoStack.push(c);
        redoStack.clear();
    }

    public void undo() {
        if (canUndo()) {
            Command c = undoStack.pop();
            c.undo();
            redoStack.push(c);
        }
    }

    public void redo() {
        if (canRedo()) {
            Command c = redoStack.pop();
            c.execute();
            undoStack.push(c);
        }
    }

    public boolean canUndo() { return !undoStack.isEmpty(); }
    public boolean canRedo() { return !redoStack.isEmpty(); }
}