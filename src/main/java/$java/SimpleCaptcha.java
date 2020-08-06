package $java;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.backgrounds.SquigglesBackgroundProducer;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.gimpy.*;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.*;
import nl.captcha.text.renderer.ColoredEdgesWordRenderer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static nl.captcha.Captcha.NAME;

/**
 * Generates, displays, and stores in session a 200x50 CAPTCHA image with sheared
 * black text, a solid dark grey background, and a slightly curved line over the
 * text.
 *
 * @author <a href="mailto:james.childers@gmail.com">James Childers</a>
 */
public class SimpleCaptcha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int _width = 200;
	private static int _height = 50;

	private static final List<Color> COLORS = new ArrayList<Color>(2);
	private static final List<Font> FONTS = new ArrayList<Font>(3);

	static {
		COLORS.add(Color.BLACK);
		COLORS.add(Color.BLUE);

		FONTS.add(new Font("Geneva", Font.ITALIC, 48));
		FONTS.add(new Font("Courier", Font.BOLD, 48));
		FONTS.add(new Font("Arial", Font.BOLD, 48));
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		if (getInitParameter("captcha-height") != null) {
			_height = Integer.valueOf(getInitParameter("captcha-height"));
		}

		if (getInitParameter("captcha-width") != null) {
			_width = Integer.valueOf(getInitParameter("captcha-width"));
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ColoredEdgesWordRenderer wordRenderer = new ColoredEdgesWordRenderer(COLORS, FONTS, 2);

		Captcha captcha = new Captcha.Builder(_width, _height)
			.addText(wordRenderer)

			//.addText()
			//.addText(new ArabicTextProducer())
			//.addText(new ChineseTextProducer())
			//.addText(new DefaultTextProducer()) // default
			//.addText(new FiveLetterFirstNameTextProducer())
			//.addText(new NumbersAnswerProducer())

			//.addBackground()
			//.addBackground(new FlatColorBackgroundProducer())
			.addBackground(new GradiatedBackgroundProducer())
			//.addBackground(new SquigglesBackgroundProducer())
			//.addBackground(new TransparentBackgroundProducer()) // default
			.addBorder()
			.addNoise()
			//.addNoise(new CurvedLineNoiseProducer()) // default
			//.addNoise(new StraightLineNoiseProducer())

			//.gimp()
			//.gimp(new BlockGimpyRenderer())
			//.gimp(new DropShadowGimpyRenderer())
			.gimp(new FishEyeGimpyRenderer())
			//.gimp(new RippleGimpyRenderer()) // default
			//.gimp(new ShearGimpyRenderer())
			//.gimp(new StretchGimpyRenderer())

			.build();

		CaptchaServletUtil.writeImage(resp, captcha.getImage());
		req.getSession().setAttribute(NAME, captcha);
	}
}
