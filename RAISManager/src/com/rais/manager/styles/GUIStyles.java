package com.rais.manager.styles;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Font;
import nextapp.echo.app.Font.Typeface;
import nextapp.echo.app.Insets;
import nextapp.echo.app.MutableStyle;
import nextapp.echo.app.Panel;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.app.Row;
import nextapp.echo.app.Style;

public class GUIStyles {

	// --------------------------------------------------------------------------------

	public static final Color COLOR_DEFAULT = new Color(145, 180, 240);
	public static final Color BORDER_COLOR_DEFAULT = new Color(80, 100, 150);
	public static final Color PRESSED_COLOR_DEFAULT = new Color(90, 125, 190);
	public static final Color ROLLOVER_COLOR_DEFAULT = new Color(135, 170, 230);

	public static final Color ERROR_COLOR = new Color(250, 250, 150);

	public static final int NORMAL = 0;
	public static final int BOLD = 1;
	public static final int ITALIC = 2;
	public static final int UNDERLINE = 3;
	public static final int OVERLINE = 4;

	public static final Typeface FONT_FACE = Font.COURIER_NEW;
	public static final Extent FONT_SIZE = new Extent(12);
	public static final Font DEFAULT_FONT = new Font(FONT_FACE, BOLD, FONT_SIZE);

	public static final int BORDER_DEFAULT = Border.STYLE_OUTSET;
	public static final int BORDER_DEFAULT_SIZE = 1;

	// --------------------------------------------------------------------------------

	public static final Style BUTTON_STYLE;
	static {
		MutableStyle style = new MutableStyle();

		style.set(Button.PROPERTY_WIDTH, new Extent(150));
		style.set(Button.PROPERTY_HEIGHT, new Extent(20));
		style.set(Button.PROPERTY_INSETS, new Insets(2, 2, 2, 2));
		style.set(Button.PROPERTY_ALIGNMENT, Alignment.ALIGN_CENTER);

		style.set(Button.PROPERTY_FONT, DEFAULT_FONT);
		style.set(Button.PROPERTY_FOREGROUND, Color.WHITE);

		style.set(Button.PROPERTY_DISABLED_FOREGROUND, Color.LIGHTGRAY);

		style.set(Button.PROPERTY_TEXT_ALIGNMENT, Alignment.ALIGN_CENTER);
		style.set(Button.PROPERTY_TEXT_POSITION, Alignment.CENTER);
		style.set(Button.PROPERTY_ICON_TEXT_MARGIN, new Extent(3));

		style.set(Button.PROPERTY_BACKGROUND_IMAGE, new FillImage( //
				new ResourceImageReference("/com/rais/manager/images/button.png", //
						new Extent(150), new Extent(30)), //
						new Extent(0), new Extent(0), FillImage.NO_REPEAT));

		style.set(Button.PROPERTY_FOCUSED_ENABLED, true);
		style.set(Button.PROPERTY_FOCUSED_BACKGROUND_IMAGE, new FillImage( //
				new ResourceImageReference("/com/rais/manager/images/button-focused.png", //
						new Extent(150), new Extent(30)), //
						new Extent(0), new Extent(0), FillImage.NO_REPEAT));

		BUTTON_STYLE = style;
	}

	// --------------------------------------------------------------------------------

	public static final Style CENTER_ROW_STYLE;
	static {
		MutableStyle style = new MutableStyle();

		style.set(Row.PROPERTY_ALIGNMENT, Alignment.ALIGN_CENTER);
		style.set(Row.PROPERTY_FONT, FONT_FACE);

		CENTER_ROW_STYLE = style;
	}

	// --------------------------------------------------------------------------------

	public static final Style CENTER_PANEL_STYLE;
	static {
		MutableStyle style = new MutableStyle();

		style.set(Panel.PROPERTY_ALIGNMENT, Alignment.ALIGN_CENTER);
		style.set(Panel.PROPERTY_INSETS, new Insets(10, 10, 10, 10));

		style.set(Panel.PROPERTY_FONT, FONT_FACE);
		style.set(Panel.PROPERTY_FOREGROUND, Color.BLACK);

		CENTER_PANEL_STYLE = style;
	}

	// --------------------------------------------------------------------------------

	public static void setFont(Component component, int fontStyle) {

		switch (fontStyle) {
		case 0:
			component.setFont(new Font(FONT_FACE, Font.PLAIN, FONT_SIZE));
			break;
		case 1:
			component.setFont(new Font(FONT_FACE, Font.BOLD, FONT_SIZE));
			break;
		case 2:
			component.setFont(new Font(FONT_FACE, Font.ITALIC, FONT_SIZE));
			break;
		case 3:
			component.setFont(new Font(FONT_FACE, Font.UNDERLINE, FONT_SIZE));
			break;
		case 4:
			component.setFont(new Font(FONT_FACE, Font.OVERLINE, FONT_SIZE));
			break;
		default:
			break;
		}
	}

	public static void setFont(Component component, int fontStyle, int size) {

		switch (fontStyle) {
		case 0:
			component.setFont(new Font(FONT_FACE, Font.PLAIN, new Extent(size)));
			break;
		case 1:
			component.setFont(new Font(FONT_FACE, Font.BOLD, new Extent(size)));
			break;
		case 2:
			component.setFont(new Font(FONT_FACE, Font.ITALIC, new Extent(size)));
			break;
		case 3:
			component.setFont(new Font(FONT_FACE, Font.UNDERLINE, new Extent(size)));
			break;
		case 4:
			component.setFont(new Font(FONT_FACE, Font.OVERLINE, new Extent(size)));
			break;
		default:
			break;
		}
	}

	// --------------------------------------------------------------------------------

}
