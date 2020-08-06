package sample.gof.strategy;

public class Calculator {
	private Operation operation;

	public Calculator(Operation operation) {
		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public void execute() {
		operation.execute();
	}
}
