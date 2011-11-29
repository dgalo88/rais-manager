package com.rais.manager;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
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
	public static final String INDEX = "index.html";
	public static final String MAIN = "main.html";

	private RaisManagerWindow window;

	private HtmlLayout htmlLayout;
	private HtmlLayoutData hld;

	// --------------------------------------------------------------------------------

	/**
	 * 
	 * @param template
	 * <br>"index.html" load the system's login/register screen
	 * <br>"main.html" load the system's main screen
	 */
	public Desktop(String template) {

		try {
			htmlLayout = new HtmlLayout( //
					getClass().getResourceAsStream( //
					HTML_DIR + template), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		setInsets(new Insets(10, 10, 10, 10));

		htmlLayout.setBackground(new Color (255, 255, 255));
		htmlLayout.setAlignment(Alignment.ALIGN_CENTER);

		setTitlePane();

		switch (template) {
		case INDEX:
			initIndex();
			break;
		case MAIN:
			initMain();
			break;
		default:
			break;
		}

		add(htmlLayout);

	}

	// --------------------------------------------------------------------------------

	private void initIndex() {

		LoginPane login = new LoginPane();
		setCentralPanel(login);

	}

	// --------------------------------------------------------------------------------

	private void initMain() {

		hld = new HtmlLayoutData("menu");
		MenuPane menuPanel = new MenuPane();
		menuPanel.setId("menu");
		menuPanel.setLayoutData(hld);
		htmlLayout.add(menuPanel);

		MainPane main = new MainPane();
		setCentralPanel(main);

	}

	// --------------------------------------------------------------------------------

	private void setTitlePane() {

		hld = new HtmlLayoutData("title");

		Panel titlePane = new Panel();
		titlePane.setStyle(GUIStyles.CENTER_PANEL_STYLE);
		titlePane.setInsets(new Insets(0, 10, 0, 10));

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

	public void removeCurrent() {

		htmlLayout.removeAll();
		htmlLayout = null;
		removeAll();

	}

	// --------------------------------------------------------------------------------

	public void btnCancelClicked() {
		setCentralPanel(new MainPane());
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
