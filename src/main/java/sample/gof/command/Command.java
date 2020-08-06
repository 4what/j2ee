package sample.gof.command;

public interface Command {
	void execute();

	void revert();
}
