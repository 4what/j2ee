package sample.java;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class Tag extends SimpleTagSupport {
	private boolean hidden;

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if (!hidden) {
			StringWriter sw = new StringWriter();

			getJspBody().invoke(sw);

			JspWriter out = getJspContext().getOut();

			out.println(sw.toString());
		}
	}
}
