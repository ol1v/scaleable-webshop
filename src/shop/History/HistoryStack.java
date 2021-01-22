package shop.History;

import java.util.Stack;

public class HistoryStack {
    static Stack<HistoryState> redoStack = new Stack<>();
    static Stack<HistoryState> undoStack = new Stack<>();

    public void undoChanges() {
        if(undoStack.size() > 0) {
            redoStack.push(undoStack.peek());
            undoStack.pop().undo.executeCommand();
        }
    }

    public void redoChanges() {
        if(redoStack.size() > 0) {
            undoStack.push(redoStack.peek());
            redoStack.pop().redo.executeCommand();
        }
    }

    public static void addState(HistoryState state) {
        undoStack.push(state);
        redoStack.clear();
    }
}
