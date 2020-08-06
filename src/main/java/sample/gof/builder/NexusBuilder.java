package sample.gof.builder;

public class NexusBuilder extends PhoneBuilder {
	@Override
	public void build() {
		phone.setOS("Android");
	}
}
