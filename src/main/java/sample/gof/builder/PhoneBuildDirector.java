package sample.gof.builder;

public class PhoneBuildDirector {
	private PhoneBuilder builder;

	public PhoneBuildDirector(PhoneBuilder builder) {
		this.builder = builder;
	}

	public Phone construct() {
		builder.build();

		return builder.getResult();
	}
}
