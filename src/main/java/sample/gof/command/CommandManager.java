package sample.gof.command;

import java.util.Stack;

public class CommandManager {
	private Stack<Command> undoStack;
	private Stack<Command> redoStack;

	public CommandManager() {
		undoStack = new Stack<>();
		redoStack = new Stack<>();
	}

	public void execute(Command cmd) {
		cmd.execute();

		undoStack.push(cmd);

		if (!redoStack.empty()) {
			redoStack.clear();
		}
	}

	public void undo() {
		if (!undoStack.empty()) {
			Command cmd = undoStack.pop();
			cmd.revert();

			redoStack.push(cmd);
		}
	}

	public void redo() {
		if (!redoStack.empty()) {
			Command cmd = redoStack.pop();
			cmd.execute();
		}
	}
}
