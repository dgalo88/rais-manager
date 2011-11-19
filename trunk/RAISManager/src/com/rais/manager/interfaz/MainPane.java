package com.rais.manager.interfaz;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Polls;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Panel;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

@SuppressWarnings("serial")
public class MainPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;
	private int pollNum;

	// --------------------------------------------------------------------------------

	public MainPane() {

		user = app.getUser();
		initGui();

	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Column col = new Column();
		col.setCellSpacing(new Extent(50));

		col.add(Constructor.initTopRow("Hola " + user.getName() + ", Bienvenido!"));

		if (user.getTeacherRef() == null) {
			col.add(pollStatus());
		} else {
			//TODO
		}

		add(col);

	}

	// --------------------------------------------------------------------------------

	private Row pollStatus() {

		pollNum = Polls.checkPendingPoll(user);

		if (pollNum == 0) {
			return Constructor.initTopRow( //
					"No tienes encuestas pendiente", 14);
		}

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);
		row.setCellSpacing(new Extent(30));

		row.add(Constructor.initTopRow( //
				"Tienes " + pollNum + " encuestas pendiente", 14));

		Button btnNotification = new Button();
		btnNotification.setToolTipText("Ver Instrucciones");
		btnNotification.setHeight(new Extent(30));
		btnNotification.setWidth(new Extent(30));
		btnNotification.setBackgroundImage(new FillImage( //
				new ResourceImageReference("/com/rais/manager/images/notification.png", //
						new Extent(30), new Extent(30)), //
						new Extent(0), new Extent(0), FillImage.NO_REPEAT));
		btnNotification.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnNotificationClicked();
			}
		});
		row.add(btnNotification);

		return row;

	}

	// --------------------------------------------------------------------------------

	private void btnNotificationClicked() {

		AutoCoEvaluationPane panel = new AutoCoEvaluationPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// --------------------------------------------------------------------------------

	public int getPollNum() {
		return pollNum;
	}

	public void setPollNum(int pollNum) {
		this.pollNum = pollNum;
	}

	// --------------------------------------------------------------------------------

}
