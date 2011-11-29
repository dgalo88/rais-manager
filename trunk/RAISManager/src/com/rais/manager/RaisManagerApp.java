package com.rais.manager;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.ContentPane;
import nextapp.echo.app.HttpImageReference;
import nextapp.echo.app.Window;

import com.rais.manager.database.User;

@SuppressWarnings("serial")
public class RaisManagerApp extends ApplicationInstance {

	private User user;
	private ContentPane contentPane;
	private Desktop desktop;
	private HttpImageReference imageReference;

	// --------------------------------------------------------------------------------

	@Override
	public Window init() {

		Window window = new Window();
		window.setTitle("Sistema de Gestión RAIS");

		desktop = new Desktop(Desktop.INDEX);
		contentPane = new ContentPane();
		contentPane.add(desktop);

//		imageReference = new HttpImageReference("imagesdata?image_id=-1");

		window.setContent(contentPane);
		return window;

	}

	// --------------------------------------------------------------------------------

	public ContentPane getContentPane() {
		return contentPane;
	}

	public Desktop getDesktop() {
		return desktop;
	}

	/**
	 * Remove the current Desktop and adds a new
	 * 
	 * @param template
	 * <br>"index.html" load the system's login/register screen
	 * <br>"main.html" load the system's main screen
	 */
	public void setNewDesktop(String template) {

		contentPane.removeAll();
		desktop.removeCurrent();
		desktop = null;

		desktop = new Desktop(template);
		contentPane.add(desktop);

	}

	// --------------------------------------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// --------------------------------------------------------------------------------

	public HttpImageReference getImageReference() {
		return imageReference;
	}

	public void setImageReference(HttpImageReference imageReference) {
		this.imageReference = imageReference;
	}

	// --------------------------------------------------------------------------------

}
