package com.rais.manager.styles;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.MutableStyle;
import nextapp.echo.app.Row;
import nextapp.echo.app.Style;
import nextapp.echo.app.Font.Typeface;

public class GUIStyles {

	// --------------------------------------------------------------------------------

	public static final Color COLORDEFAULT = new Color(200, 200, 225);
	public static final Color BORDEDEFAULT = new Color(80, 100, 150);
	public static final Color FOCUSEDDEFAULT = new Color(80, 85, 155);
	public static final Color ROLLOVERDEFAULT = new Color(150, 170, 200);

	public static final Color ERRORCOLOR = new Color(250, 250, 150);

	public static final int NORMAL = 0;
	public static final int BOLD = 1;
	public static final int ITALIC = 2;
	public static final int UNDERLINE = 3;
	public static final int OVERLINE = 4;

	// --------------------------------------------------------------------------------

	public static final Style DEFAULT_STYLE;
	static {
		MutableStyle style = new MutableStyle();

		style.set(Row.PROPERTY_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));

		style.set(Button.PROPERTY_WIDTH, new Extent(150));
		style.set(Button.PROPERTY_HEIGHT, new Extent(20));
		style.set(Button.PROPERTY_INSETS, new Insets(2, 2, 2, 2));
		style.set(Button.PROPERTY_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));

		style.set(Button.PROPERTY_LINE_WRAP, false);
		style.set(Button.PROPERTY_BACKGROUND, GUIStyles.COLORDEFAULT);
		style.set(Button.PROPERTY_BORDER, new Border(1, GUIStyles.BORDEDEFAULT, Border.STYLE_SOLID));
		style.set(Button.PROPERTY_FONT, Font.PLAIN);
		style.set(Button.PROPERTY_FONT, Font.MONOSPACE);

		style.set(Button.PROPERTY_TEXT_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));
		style.set(Button.PROPERTY_TEXT_POSITION, new Alignment(Alignment.CENTER, Alignment.CENTER));
		style.set(Button.PROPERTY_ICON_TEXT_MARGIN, new Extent(3));

		style.set(Button.PROPERTY_DISABLED_FOREGROUND, Color.LIGHTGRAY);

		style.set(Button.PROPERTY_FOCUSED_ENABLED, true);
		style.set(Button.PROPERTY_FOCUSED_BACKGROUND, GUIStyles.ROLLOVERDEFAULT);
		style.set(Button.PROPERTY_FOCUSED_BORDER, new Border(1, GUIStyles.FOCUSEDDEFAULT, Border.STYLE_SOLID));

		style.set(Button.PROPERTY_ROLLOVER_ENABLED, true);
		style.set(Button.PROPERTY_ROLLOVER_BACKGROUND, GUIStyles.ROLLOVERDEFAULT);
		style.set(Button.PROPERTY_ROLLOVER_BORDER, new Border(1, GUIStyles.BORDEDEFAULT, Border.STYLE_SOLID));

		DEFAULT_STYLE = style;
	}

	// --------------------------------------------------------------------------------

	public static void setFont(Component component, int fontStyle) {

		int size = 12;
		Typeface typeface = Font.MONOSPACE;

		switch (fontStyle) {
		case 0:
			component.setFont(new Font(typeface, Font.PLAIN, new Extent(size)));
			break;
		case 1:
			component.setFont(new Font(typeface, Font.BOLD, new Extent(size)));
			break;
		case 2:
			component.setFont(new Font(typeface, Font.ITALIC, new Extent(size)));
			break;
		case 3:
			component.setFont(new Font(typeface, Font.UNDERLINE, new Extent(size)));
			break;
		case 4:
			component.setFont(new Font(typeface, Font.OVERLINE, new Extent(size)));
			break;
		default:
			break;
		}
	}

	public static void setFont(Component component, int fontStyle, int size) {

		Typeface typeface = Font.MONOSPACE;

		switch (fontStyle) {
		case 0:
			component.setFont(new Font(typeface, Font.PLAIN, new Extent(size)));
			break;
		case 1:
			component.setFont(new Font(typeface, Font.BOLD, new Extent(size)));
			break;
		case 2:
			component.setFont(new Font(typeface, Font.ITALIC, new Extent(size)));
			break;
		case 3:
			component.setFont(new Font(typeface, Font.UNDERLINE, new Extent(size)));
			break;
		case 4:
			component.setFont(new Font(typeface, Font.OVERLINE, new Extent(size)));
			break;
		default:
			break;
		}
	}

	// --------------------------------------------------------------------------------

}
