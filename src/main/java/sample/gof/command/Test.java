package sample.gof.command;

public class Test {


	public static void main(String[] args) {
		Object o = new Object();

		CreateCommand create = new CreateCommand(o);
		DeleteCommand delete = new DeleteCommand(o);

		CommandManager manager = new CommandManager();

		manager.execute(create);
		manager.execute(delete);

		manager.undo();
		manager.undo();

		manager.redo();
		manager.redo();
	}
}
