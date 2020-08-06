package sample.gof.command;

public class DeleteCommand implements Command {
	private Object o;

	public DeleteCommand(Object o) {
		this.o = o;
	}

	@Override
	public void execute() {
		o.delete();
	}

	@Override
	public void revert() {
		System.out.println("DeleteCommand.revert()");
	}
}
