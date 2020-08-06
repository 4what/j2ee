package sample.gof.builder;

public class iPhoneBuilder extends PhoneBuilder {
	@Override
	public void build() {
		phone.setOS("iOS");
	}
}
