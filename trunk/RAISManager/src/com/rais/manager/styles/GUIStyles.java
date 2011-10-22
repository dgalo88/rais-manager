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

	public static final Color COLORDEFAULT = new Color(145, 180, 240);
	public static final Color BORDERCOLORDEFAULT = new Color(80, 100, 150);
	public static final Color FOCUSEDCOLORDEFAULT = new Color(90, 125, 190);
	public static final Color ROLLOVERCOLORDEFAULT = new Color(135, 170, 230);

	public static final Color ERRORCOLOR = new Color(250, 250, 150);

	public static final int NORMAL = 0;
	public static final int BOLD = 1;
	public static final int ITALIC = 2;
	public static final int UNDERLINE = 3;
	public static final int OVERLINE = 4;

	public static final Typeface FONTFACE = Font.MONOSPACE;
	public static final int BORDERDEFAULT = Border.STYLE_OUTSET;
	public static final int BORDERDEFAULTSIZE = 1;

	// --------------------------------------------------------------------------------

	public static final Style DEFAULT_STYLE;
	static {
		MutableStyle style = new MutableStyle();

		style.set(Row.PROPERTY_ALIGNMENT, new Alignment( //
				Alignment.CENTER, Alignment.CENTER));

		style.set(Button.PROPERTY_WIDTH, new Extent(150));
		style.set(Button.PROPERTY_HEIGHT, new Extent(20));
		style.set(Button.PROPERTY_INSETS, new Insets(2, 2, 2, 2));
		style.set(Button.PROPERTY_ALIGNMENT, new Alignment( //
				Alignment.CENTER, Alignment.CENTER));

		style.set(Button.PROPERTY_LINE_WRAP, false);
		style.set(Button.PROPERTY_BACKGROUND, COLORDEFAULT);
		style.set(Button.PROPERTY_BORDER, new Border( //
				BORDERDEFAULTSIZE, BORDERCOLORDEFAULT, BORDERDEFAULT));
		style.set(Button.PROPERTY_FONT, Font.PLAIN);
		style.set(Button.PROPERTY_FONT, Font.MONOSPACE);

		style.set(Button.PROPERTY_TEXT_ALIGNMENT, new Alignment( //
				Alignment.CENTER, Alignment.CENTER));
		style.set(Button.PROPERTY_TEXT_POSITION, new Alignment( //
				Alignment.CENTER, Alignment.CENTER));
		style.set(Button.PROPERTY_ICON_TEXT_MARGIN, new Extent(3));

		style.set(Button.PROPERTY_DISABLED_FOREGROUND, Color.LIGHTGRAY);

		style.set(Button.PROPERTY_FOCUSED_ENABLED, true);
		style.set(Button.PROPERTY_FOCUSED_BACKGROUND, FOCUSEDCOLORDEFAULT);
		style.set(Button.PROPERTY_FOCUSED_BORDER, new Border( //
				BORDERDEFAULTSIZE, FOCUSEDCOLORDEFAULT, BORDERDEFAULT));

		style.set(Button.PROPERTY_ROLLOVER_ENABLED, true);
		style.set(Button.PROPERTY_ROLLOVER_BACKGROUND, ROLLOVERCOLORDEFAULT);
		style.set(Button.PROPERTY_ROLLOVER_BORDER, new Border( //
				BORDERDEFAULTSIZE, BORDERCOLORDEFAULT, BORDERDEFAULT));

		DEFAULT_STYLE = style;
	}

	// --------------------------------------------------------------------------------

	public static void setFont(Component component, int fontStyle) {

		int size = 12;

		switch (fontStyle) {
		case 0:
			component.setFont(new Font(FONTFACE, Font.PLAIN, new Extent(size)));
			break;
		case 1:
			component.setFont(new Font(FONTFACE, Font.BOLD, new Extent(size)));
			break;
		case 2:
			component.setFont(new Font(FONTFACE, Font.ITALIC, new Extent(size)));
			break;
		case 3:
			component.setFont(new Font(FONTFACE, Font.UNDERLINE, new Extent(size)));
			break;
		case 4:
			component.setFont(new Font(FONTFACE, Font.OVERLINE, new Extent(size)));
			break;
		default:
			break;
		}
	}

	public static void setFont(Component component, int fontStyle, int size) {

		switch (fontStyle) {
		case 0:
			component.setFont(new Font(FONTFACE, Font.PLAIN, new Extent(size)));
			break;
		case 1:
			component.setFont(new Font(FONTFACE, Font.BOLD, new Extent(size)));
			break;
		case 2:
			component.setFont(new Font(FONTFACE, Font.ITALIC, new Extent(size)));
			break;
		case 3:
			component.setFont(new Font(FONTFACE, Font.UNDERLINE, new Extent(size)));
			break;
		case 4:
			component.setFont(new Font(FONTFACE, Font.OVERLINE, new Extent(size)));
			break;
		default:
			break;
		}
	}

	// --------------------------------------------------------------------------------

}
