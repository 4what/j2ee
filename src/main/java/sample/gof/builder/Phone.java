package sample.gof.builder;

public class Phone {
	private Object OS;

	public Object getOS() {
		return OS;
	}

	public void setOS(Object OS) {
		this.OS = OS;
	}

	@Override
	public String toString() {
		return "Phone{" +
			"OS=" + OS +
			'}';
	}
}
