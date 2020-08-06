package sample.gof.builder;

public abstract class PhoneBuilder {
	protected Phone phone;

	public PhoneBuilder() {
		phone = new Phone();
	}

	public abstract void build();

	public Phone getResult() {
		return phone;
	}
}
