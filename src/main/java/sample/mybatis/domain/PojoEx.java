package sample.mybatis.domain;

import java.util.List;

/**
 * OneToOne, ManyToMany, OneToMany (ManyToOne)
 * https://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html#bnbqh
 */
public class PojoEx extends Pojo {
	private Parent parent;

	private List<Child> children;

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "PojoEx{" +
			"parent=" + parent +
			", children=" + children +
			'}';
	}
}
