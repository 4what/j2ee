package sample.gof.command;

public class CreateCommand implements Command {
	private Object o;

	public CreateCommand(Object o) {
		this.o = o;
	}

	@Override
	public void execute() {
		o.create();
	}

	@Override
	public void revert() {
		System.out.println("CreateCommand.revert()");
	}
}
