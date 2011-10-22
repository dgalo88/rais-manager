package com.rais.manager.interfaz;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.Desktop;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.database.Estudiante;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class RegisterTeacherPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();
	private Desktop desktop;

	private String matter;
	private SelectField selectMatter;
	private String[] mattersMenu;

	private Grid grid;
	private Column col;
	private Row errorRow;

	public RegisterTeacherPane() {

		desktop = app.getDesktop();
		initGui();

	}

	private void initGui() {

		setInsets(new Insets(5, 5, 5, 5));
		setAlignment(Alignment.ALIGN_CENTER);

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		col = new Column();
		col.setCellSpacing(new Extent(5));

		grid = new Grid();
		grid.setBackground(Color.WHITE);
		grid.setColumnWidth(0, new Extent(180));
		grid.setColumnWidth(1, new Extent(300));
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		col.add(Constructor.initTopRow("Registro de Usuario", 14));
		col.add(Constructor.initTopRow("Datos del Profesor:", 13));

		Label lblCompany = new Label("Seleccione materia:");
		GUIStyles.setFont(lblCompany, GUIStyles.NORMAL);
		grid.add(lblCompany);

		mattersMenu = new String[3];
		mattersMenu[0] = "Seleccione materia";
		mattersMenu[1] = "Ingenier�a de Software";
		mattersMenu[2] = "Bases de Datos";

		selectMatter = new SelectField(getMattersMenu());
		selectMatter.setHeight(new Extent(25));
		selectMatter.setWidth(new Extent(305));
		GUIStyles.setFont(selectMatter, GUIStyles.NORMAL);
		selectMatter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				matterSelected(selectMatter.getSelectedIndex());
			}
		});
		grid.add(selectMatter);

		col.add(grid);

		Row rowButtons = new Row();
		rowButtons.setCellSpacing(new Extent(5));
		rowButtons.setInsets(new Insets(5, 5, 5, 5));
		rowButtons.setAlignment(Alignment.ALIGN_CENTER);

		Button btnBack = new Button("Atr�s");
		btnBack.setStyle(GUIStyles.DEFAULT_STYLE);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnBackClicked();
			}
		});
		rowButtons.add(btnBack);

		Button btnRegister = new Button("Registrarse");
		btnRegister.setStyle(GUIStyles.DEFAULT_STYLE);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnRegisterClicked();
			}
		});
		rowButtons.add(btnRegister);

		col.add(rowButtons);
		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	protected void matterSelected(int selectedIndex) {

		if (selectedIndex == 0) {
			return;
		}
		setMatter(mattersMenu[selectedIndex]);

	}

	// --------------------------------------------------------------------------------

	private void btnBackClicked() {

		RegisterPane pane = new RegisterPane(new Estudiante());
		desktop.setCentralPanel(pane);

	}

	// --------------------------------------------------------------------------------

	private void btnRegisterClicked() {

		if (!checkEmptyFields()) {

			LoginPane pane = new LoginPane();
			desktop.setCentralPanel(pane);

		}

	}

	// --------------------------------------------------------------------------------

	private boolean checkEmptyFields() {

		boolean flg = false;

		if (selectMatter.getSelectedIndex() == 0) {
			selectMatter.set(PROPERTY_BACKGROUND, GUIStyles.ERRORCOLOR);
			flg = true;
		}

		if (flg) {

			if (col.getComponentCount() > 3) {
				col.remove(errorRow);
			}

			errorRow = new Row();
			Label lblError = new Label("Todos los campos son obligatorios");
			GUIStyles.setFont(lblError, GUIStyles.ITALIC);
			errorRow.add(lblError);
			errorRow.setAlignment(Alignment.ALIGN_CENTER);
			col.add(errorRow);

			return true;

		}

		return false;

	}

	// --------------------------------------------------------------------------------

	public String [] getMattersMenu() {
		return mattersMenu;
	}

	public void setMattersMenu(String [] mattersMenu) {
		this.mattersMenu = mattersMenu;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	// --------------------------------------------------------------------------------

}
