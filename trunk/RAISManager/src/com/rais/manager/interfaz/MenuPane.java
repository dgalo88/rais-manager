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
import com.rais.manager.controller.Data;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class MenuPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;

	private Column col;

	// --------------------------------------------------------------------------------

	public MenuPane() {

		user = Data.loadUser(app.getUser().getId());
		initGui();

	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		Row row = new Row();
		row.setInsets(new Insets(0, 10, 0, 0));
		row.setAlignment(Alignment.ALIGN_LEFT);

		col = new Column();

		Button btnHome = new Button("Inicio");
		btnHome.setStyle(GUIStyles.BUTTON_STYLE);
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnHomeClicked();
			}
		});
		col.add(btnHome);

		Button btnProfile = new Button("Perfil");
		btnProfile.setStyle(GUIStyles.BUTTON_STYLE);
		btnProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnProfileClicked();
			}
		});
		col.add(btnProfile);

		if ((user.getTeacherRef() != null) || (user.getStudentRef() == null)) {

			Button btnAddStudent = new Button("Agregar Estudiantes");
			btnAddStudent.setStyle(GUIStyles.BUTTON_STYLE);
			btnAddStudent.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					btnAddStudentClicked();
				}
			});
			col.add(btnAddStudent);

			Button btnAddTeacher = new Button("Agregar Profesores");
			btnAddTeacher.setStyle(GUIStyles.BUTTON_STYLE);
			btnAddTeacher.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					btnAddTeacherClicked();
				}
			});
			col.add(btnAddTeacher);

		}
//		Button btnWorks = new Button("Tareas");
//		btnWorks.setStyle(GUIStyles.BUTTON_STYLE);
//		btnWorks.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent evt) {
//				btnWorksClicked();
//			}
//		});
//		col.add(btnWorks);

		Button btnExit = new Button("Salir");
		btnExit.setStyle(GUIStyles.BUTTON_STYLE);
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

	// --------------------------------------------------------------------------------

	private void btnHomeClicked() {

		MainPane panel = new MainPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnProfileClicked() {

		ProfilePane panel = new ProfilePane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnAddStudentClicked() {

		AddStudentPane panel = new AddStudentPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnAddTeacherClicked() {

		AddTeacherPane panel = new AddTeacherPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

//	private void btnWorksClicked() {
//
//		WorksPane panel = new WorksPane();
//		app.getDesktop().setCentralPanel(panel);
//
//	}

	// --------------------------------------------------------------------------------

	private void btnExitClicked() {

		app.setUser(null);
		app.setNewDesktop(DesktopType.INDEX);

	}

	// --------------------------------------------------------------------------------

}
