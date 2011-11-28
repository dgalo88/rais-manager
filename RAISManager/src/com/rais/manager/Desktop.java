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
import com.rais.manager.interfaz.windows.RaisManagerWindow;
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

	private RaisManagerWindow window;

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

		setTitlePane();

		LoginPane login = new LoginPane();
		setCentralPanel(login);

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

		setTitlePane();

		hld = new HtmlLayoutData("menu");
		MenuPane menuPanel = new MenuPane();
		menuPanel.setId("menu");
		menuPanel.setLayoutData(hld);
		htmlLayout.add(menuPanel);

		MainPane main = new MainPane();
		setCentralPanel(main);

		return htmlLayout;

	}

	// --------------------------------------------------------------------------------

	private void setTitlePane() {

		hld = new HtmlLayoutData("title");

		Panel titlePane = new Panel();
		titlePane.setInsets(new Insets(0, 10, 0, 10));
		titlePane.setAlignment(Alignment.ALIGN_CENTER);

		Label lblTitle = new Label("Sistema de Gestión RAIS");
		GUIStyles.setFont(lblTitle, GUIStyles.BOLD, 16);
		titlePane.add(lblTitle);

		titlePane.setLayoutData(hld);
		htmlLayout.add(titlePane);

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

	public void removeDesktop() {

		htmlLayout.removeAll();
		htmlLayout = null;
		removeAll();

	}

	// --------------------------------------------------------------------------------

	public void setWindowPaneEmergente(String texto) {

		window = new RaisManagerWindow(texto);
		add(window);

	}

	// --------------------------------------------------------------------------------

	public void setInstructionWindow(Panel panel) {

		window = new RaisManagerWindow(panel);
		add(window);

	}

	// --------------------------------------------------------------------------------

}
