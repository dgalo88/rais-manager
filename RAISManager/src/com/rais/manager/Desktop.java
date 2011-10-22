package com.rais.manager;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.ContentPane;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;

import com.rais.manager.interfaz.LoginPane;
import com.rais.manager.interfaz.MainPane;
import com.rais.manager.interfaz.MenuPane;
import com.rais.manager.interfaz.windows.WindowInfo;
import com.rais.manager.interfaz.windows.WindowInstructions;
import com.rais.manager.styles.GUIStyles;

import echopoint.HtmlLayout;
import echopoint.layout.HtmlLayoutData;

@SuppressWarnings("serial")
public class Desktop extends ContentPane {

	public static final String HTML_DIR = "/com/rais/manager/html/";
	public static final String INDEX_HTML = "index.html";
	public static final String MAIN_HTML = "main.html";

	public enum DesktopType {
		INDEX, MAIN
	}

	private WindowInfo windowInfo;
	private WindowInstructions windowIntructions;

	private HtmlLayout htmlLayout;
	private HtmlLayoutData hld;

	// --------------------------------------------------------------------------------

	public Desktop(DesktopType desktopType) {

		switch (desktopType) {
		case INDEX:
			add(initIndex());
			break;
		case MAIN:
			add(initMain());
			break;
		default:
			break;
		}

	}

	// --------------------------------------------------------------------------------

	public Component initIndex() {

		try {
			htmlLayout = new HtmlLayout(getClass().getResourceAsStream( //
					HTML_DIR + INDEX_HTML), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		setInsets(new Insets(10, 10, 10, 10));

		htmlLayout.setBackground(new Color (255, 255, 255));
		htmlLayout.setAlignment(Alignment.ALIGN_CENTER);

		setLabelTitle();

		hld = new HtmlLayoutData("content");
		LoginPane login = new LoginPane();
		login.setId("content");
		login.setLayoutData(hld);
		htmlLayout.add(login);

		return htmlLayout;

	}

	// --------------------------------------------------------------------------------

	public Component initMain() {

		try {
			htmlLayout = new HtmlLayout(getClass().getResourceAsStream( //
					HTML_DIR + MAIN_HTML), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		setInsets(new Insets(10, 10, 10, 10));

		htmlLayout.setBackground(new Color (255, 255, 255));
		htmlLayout.setAlignment(Alignment.ALIGN_CENTER);

		setLabelTitle();

		hld = new HtmlLayoutData("menu");
		MenuPane menu = new MenuPane();
		menu.setId("menu");
		menu.setLayoutData(hld);
		htmlLayout.add(menu);

		hld = new HtmlLayoutData("content");
		MainPane main = new MainPane();
		main.setId("content");
		main.setLayoutData(hld);
		htmlLayout.add(main);

		return htmlLayout;

	}

	// --------------------------------------------------------------------------------

	private void setLabelTitle() {

		hld = new HtmlLayoutData("title");
		Label lblTitle = new Label("Sistema de Gestión RAIS");
		GUIStyles.setFont(lblTitle, GUIStyles.BOLD, 16);
		lblTitle.setLayoutData(hld);
		htmlLayout.add(lblTitle);

	}

	// --------------------------------------------------------------------------------

	public void setCentralPanel(Panel panel) {

		hld = new HtmlLayoutData("content");
		panel.setId("content");
		panel.setLayoutData(hld);
		// Remueve componente anterior del htmlLayout
		htmlLayout.remove(htmlLayout.getComponent("content"));
		htmlLayout.setAlignment(Alignment.ALIGN_CENTER);
		htmlLayout.add(panel);

	}

	// --------------------------------------------------------------------------------

	public void setWindowPaneEmergente(String texto) {

		windowInfo = new WindowInfo(texto);
		add(windowInfo);

	}

	// --------------------------------------------------------------------------------

		public void setInstructionWindow(Panel pane) {

			windowIntructions = new WindowInstructions(pane);
			add(windowIntructions);

		}

	// --------------------------------------------------------------------------------

	public void removeDesktop() {

		htmlLayout.removeAll();
		htmlLayout = null;
		removeAll();

	}

}
