package com.rais.manager.interfaz;

import java.util.ArrayList;
import java.util.List;

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
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Import;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class ImportStudentsPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private String message = "";

	private TextField txtPathFile;

	private List<User> userList;
	private List<String> names;
	private List<String> cedulas;
	private List<String> companies;

	private Column col;
	private Row tableRow;

	// --------------------------------------------------------------------------------

	public ImportStudentsPane() {
		initGui();
	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		col = new Column();
		col.setCellSpacing(new Extent(10));

		col.add(Constructor.initTopRow( //
				"Ingrese la dirección del archivo csv " //
				+ "con la información de los estudiantes:", 12));

		row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		txtPathFile = new TextField();
		GUIStyles.setFont(txtPathFile, GUIStyles.NORMAL);
		txtPathFile.setWidth(new Extent(350));
		row.add(txtPathFile);
		col.add(row);

		row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Button btnAcept = new Button("Aceptar");
		btnAcept.setStyle(GUIStyles.BUTTON_STYLE);
		btnAcept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnAceptClicked();
			}
		});
		row.add(btnAcept);
		col.add(row);

		row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	private void btnAceptClicked() {

		if (txtPathFile.getText().isEmpty()) {
			app.getDesktop().setWindowPaneEmergente( //
					"Ingrese la dirección del archivo csv " //
					+ "con la información de los estudiantes:");
			return;
		}

		col.removeAll();
		addStudentTable();

	}

	// --------------------------------------------------------------------------------

	private void addStudentTable() {

		// ----------------------------------------
		// Cargar datos de los estudiantes
		// ----------------------------------------

		names = new ArrayList<String>();
		cedulas = new ArrayList<String>();
		companies = new ArrayList<String>();

		try {
			Import.loadData(txtPathFile.getText(), //
					names, cedulas, companies);
		} catch (Exception e) {
			e.printStackTrace();
		}

		tableRow = new Row();
		tableRow.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Grid grid = new Grid(3);
		grid.setBackground(Color.WHITE);
		grid.setColumnWidth(0, new Extent(200));
		grid.setColumnWidth(1, new Extent(100));
		grid.setColumnWidth(2, new Extent(150));
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border( //
				new Extent(1), Color.BLACK, Border.STYLE_INSET));

		col.add(Constructor.initTopRow("Lista de estudiantes para registrar", 12));

		Row centerRow = new Row();
		centerRow.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Label lblName = new Label("Nombre");
		GUIStyles.setFont(lblName, GUIStyles.BOLD);
		centerRow.add(lblName);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Label lblCedula = new Label("Cédula");
		GUIStyles.setFont(lblCedula, GUIStyles.BOLD);
		centerRow.add(lblCedula);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setStyle(GUIStyles.CENTER_ROW_STYLE);

		Label lblCompany = new Label("Compañía");
		GUIStyles.setFont(lblCompany, GUIStyles.BOLD);
		centerRow.add(lblCompany);
		grid.add(centerRow);

		for (int i = 0; i < names.size(); i++) {

			lblName = new Label(names.get(i));
			GUIStyles.setFont(lblName, GUIStyles.NORMAL);
			grid.add(lblName);

			lblCedula = new Label(cedulas.get(i));
			GUIStyles.setFont(lblCedula, GUIStyles.NORMAL);
			grid.add(lblCedula);

			lblCompany = new Label(companies.get(i));
			GUIStyles.setFont(lblCompany, GUIStyles.NORMAL);
			grid.add(lblCompany);

		}
		tableRow.add(grid);

		col.add(tableRow);

		centerRow = new Row();
		centerRow.setStyle(GUIStyles.CENTER_ROW_STYLE);
		centerRow.setCellSpacing(new Extent(5));
		centerRow.setInsets(new Insets(5, 5, 5, 5));

		Button btnBack = new Button("Atrás");
		btnBack.setStyle(GUIStyles.BUTTON_STYLE);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnBackClicked();
			}
		});
		centerRow.add(btnBack);
		col.add(centerRow);

		Button btnSend = new Button("Enviar");
		btnSend.setStyle(GUIStyles.BUTTON_STYLE);
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSendClicked();
			}
		});
		centerRow.add(btnSend);
		col.add(centerRow);

	}

	// --------------------------------------------------------------------------------

	private void btnBackClicked() {

		ImportStudentsPane panel = new ImportStudentsPane();
		app.getDesktop().setCentralPanel(panel);

	}

	// --------------------------------------------------------------------------------

	private void btnSendClicked() {

		userList = new ArrayList<User>();
		try {
			Import.loadStudents(this, userList);
		} catch (Exception e) {
			message = "Ha ocurrido un error. " //
					+ "Los estudiantes no han sido registrados";
			e.printStackTrace();
		}

		app.getDesktop().setWindowPaneEmergente(message);

		AddStudentPane panel = new AddStudentPane();
		app.getDesktop().setCentralPanel(panel);

	}


	// --------------------------------------------------------------------------------

	public TextField getTxtPathFile() {
		return txtPathFile;
	}

	public void setTxtPathFile(TextField txtPathFile) {
		this.txtPathFile = txtPathFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// --------------------------------------------------------------------------------

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getCedulas() {
		return cedulas;
	}

	public void setCedulas(List<String> cedulas) {
		this.cedulas = cedulas;
	}

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	// --------------------------------------------------------------------------------

}
