package sample.gof.prototype;

public class Class implements Cloneable {
	public Class clone() throws CloneNotSupportedException {
		return (Class) super.clone();
	}
}
