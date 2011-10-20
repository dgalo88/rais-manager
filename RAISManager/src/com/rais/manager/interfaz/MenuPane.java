package com.rais.manager.interfaz;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.Desktop.DesktopType;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class MenuPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	public MenuPane() {
		initGui();
	}

	private void initGui() {

		setInsets(new Insets(0, 10, 0, 0));
		setAlignment(Alignment.ALIGN_LEFT);

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_LEFT);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		Column col = new Column();

		Button btnHome = new Button("Inicio");
		btnHome.setStyle(GUIStyles.DEFAULT_STYLE);
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnHomeClicked();
			}
		});
		col.add(btnHome);

		Button btnProfile = new Button("Perfil");
		btnProfile.setStyle(GUIStyles.DEFAULT_STYLE);
		btnProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnProfileClicked();
			}
		});
		col.add(btnProfile);

		Button btnSurveys = new Button("Encuestas");
		btnSurveys.setStyle(GUIStyles.DEFAULT_STYLE);
		btnSurveys.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSurveysClicked();
			}
		});
		col.add(btnSurveys);

		Button btnWorks = new Button("Tareas");
		btnWorks.setStyle(GUIStyles.DEFAULT_STYLE);
		btnWorks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnWorksClicked();
			}
		});
		col.add(btnWorks);

		Button btnExit = new Button("Salir");
		btnExit.setStyle(GUIStyles.DEFAULT_STYLE);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitClicked();
			}
		});
		col.add(btnExit);

		row.add(col);
		add(row);

	}

	private void btnHomeClicked() {
		app.setNewDesktop(DesktopType.MAIN);
	}

	private void btnSurveysClicked() {

		AutoCoEvaluationPane pane = new AutoCoEvaluationPane();
		app.getDesktop().setCentralPanel(pane);

	}

	private void btnProfileClicked() {
		
	}

	private void btnWorksClicked() {
		// TODO Auto-generated method stub
	}

	private void btnExitClicked() {
		app.setNewDesktop(DesktopType.INDEX);
	}

}
