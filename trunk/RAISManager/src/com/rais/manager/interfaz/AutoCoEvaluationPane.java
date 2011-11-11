package com.rais.manager.interfaz;

import java.io.IOException;
import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.RadioButton;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.app.Row;
import nextapp.echo.app.button.ButtonGroup;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.RaisManagerApp;
import com.rais.manager.controller.Polls;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class AutoCoEvaluationPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private Label[] lblElement = new Label[5];
	private List<String> partnersList;
	private RadioButton[][] autoRadioBtn = new RadioButton[5][4];
	private ButtonGroup[] autoBtnGroup = new ButtonGroup[5];
	private ButtonGroup[] coBtnGroup;
	private RadioButton[][] coRadioBtn;

	private Column col;
	private Row buttonRow;
	private Button btnNext;
	private Row autoEvaluation;
	private Row coEvaluation;

	public AutoCoEvaluationPane() {
		initGui();
	}

	public void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);

		col = new Column();
		col.setCellSpacing(new Extent(10));

		Row headerRow = new Row();
		headerRow.setAlignment(Alignment.ALIGN_CENTER);
		headerRow.setCellSpacing(new Extent(30));

		headerRow.add(Constructor.initTopRow("Cuestionario de Auto y Coevaluación", 14));

		Button btnHelp = new Button();
		btnHelp.setToolTipText("Ver Instrucciones");
		btnHelp.setHeight(new Extent(20));
		btnHelp.setWidth(new Extent(20));
		btnHelp.setBackgroundImage(new FillImage( //
				new ResourceImageReference("/com/rais/manager/images/button-help.png", //
						new Extent(20), new Extent(20)), //
						new Extent(0), new Extent(0), FillImage.NO_REPEAT));
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				showWindowHelp();
			}
		});
		headerRow.add(btnHelp);
		col.add(headerRow);

		autoEvaluation = autoEvaluation();
		col.add(autoEvaluation);

		buttonRow = new Row();
		buttonRow.setAlignment(Alignment.ALIGN_CENTER);

		btnNext = new Button("Siguiente");
		btnNext.setStyle(GUIStyles.BUTTON_STYLE);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnNextClicked();
			}
		});
		buttonRow.add(btnNext);
		col.add(buttonRow);

		row.add(col);
		add(row);

	}

	// --------------------------------------------------------------------------------

	private Row autoEvaluation() {

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		Column col = new Column();
		col.setCellSpacing(new Extent(15));

		Row centerGrid = new Row();
		centerGrid.setAlignment(Alignment.ALIGN_CENTER);

		Grid grid = new Grid(6);
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
		grid.setColumnWidth(0, new Extent(10, Extent.PERCENT));
		grid.setColumnWidth(1, new Extent(70, Extent.PERCENT));
		grid.setColumnWidth(2, new Extent(5, Extent.PERCENT));
		grid.setColumnWidth(3, new Extent(5, Extent.PERCENT));
		grid.setColumnWidth(4, new Extent(5, Extent.PERCENT));
		grid.setColumnWidth(5, new Extent(5, Extent.PERCENT));

		Row centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblAuto = new Label("1. Auto-evaluación");
		GUIStyles.setFont(lblAuto, GUIStyles.BOLD, 12);
		centerRow.add(lblAuto);
		col.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		//Header Row

		Label lblNo = new Label("No.");
		GUIStyles.setFont(lblNo, GUIStyles.BOLD, 12);
		centerRow.add(lblNo);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblElementHead = new Label("Elemento");
		GUIStyles.setFont(lblElementHead, GUIStyles.BOLD, 12);
		centerRow.add(lblElementHead);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblA = new Label("A");
		GUIStyles.setFont(lblA, GUIStyles.BOLD, 12);
		centerRow.add(lblA);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblB = new Label("B");
		GUIStyles.setFont(lblB, GUIStyles.BOLD, 12);
		centerRow.add(lblB);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblC = new Label("C");
		GUIStyles.setFont(lblC, GUIStyles.BOLD, 12);
		centerRow.add(lblC);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblD = new Label("D");
		GUIStyles.setFont(lblD, GUIStyles.BOLD, 12);
		centerRow.add(lblD);
		grid.add(centerRow);

		//Row 1

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_1 = new Label("1.1");
		GUIStyles.setFont(lbl1_1, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_1);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		lblElement[0] = new Label("¿Cómo evalúa el producto desarrollado por su compañía? " +
				"\n(considere calidad del producto, logro de los objetivos, " +
				"cantidad de funcionalidad implementada, etcétera)");
		GUIStyles.setFont(lblElement[0], GUIStyles.NORMAL, 12);
		centerRow.add(lblElement[0]);
		grid.add(centerRow);

		autoBtnGroup[0] = new ButtonGroup();

		for (int j = 0; j < 4; j++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);
			autoRadioBtn[0][j] = new RadioButton();
			centerRow.add(autoRadioBtn[0][j]);
			grid.add(centerRow);

			autoRadioBtn[0][j].setGroup(autoBtnGroup[0]);

		}

		//Row 2

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_2 = new Label("1.2");
		GUIStyles.setFont(lbl1_2, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_2);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		lblElement[1] = new Label("¿Cómo evalúa el desempeño de su compañía? " +
				"\n(considere el logro de objetivos, eficiencia, etcétera)");
		GUIStyles.setFont(lblElement[1], GUIStyles.NORMAL, 12);
		centerRow.add(lblElement[1]);
		grid.add(centerRow);

		autoBtnGroup[1] = new ButtonGroup();

		for (int j = 0; j < 4; j++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);
			autoRadioBtn[1][j] = new RadioButton();
			centerRow.add(autoRadioBtn[1][j]);
			grid.add(centerRow);

			autoRadioBtn[1][j].setGroup(autoBtnGroup[1]);

		}

		//Row 3

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_3 = new Label("1.3");
		GUIStyles.setFont(lbl1_3, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_3);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		lblElement[2] = new Label("¿Cómo evalúa SU desempeño dentro de su compañía? " +
				"\n(considere el logro de objetivos, eficiencia, " +
				"participación, aportes, responsabilidad, etcétera)");
		GUIStyles.setFont(lblElement[2], GUIStyles.NORMAL, 12);
		centerRow.add(lblElement[2]);
		grid.add(centerRow);

		autoBtnGroup[2] = new ButtonGroup();

		for (int j = 0; j < 4; j++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);
			autoRadioBtn[2][j] = new RadioButton();
			centerRow.add(autoRadioBtn[2][j]);
			grid.add(centerRow);

			autoRadioBtn[2][j].setGroup(autoBtnGroup[2]);

		}

		//Row 4

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_4 = new Label("1.4");
		GUIStyles.setFont(lbl1_4, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_4);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		lblElement[3] = new Label("¿Cómo evalúa de forma global el desempeño " +
				"de sus compañeros en su compañía? " +
				"\n(considere logro de objetivos, eficiencia, " +
				"participación, aportes, responsabilidad, etcétera)");
		GUIStyles.setFont(lblElement[3], GUIStyles.NORMAL, 12);
		centerRow.add(lblElement[3]);
		grid.add(centerRow);

		autoBtnGroup[3] = new ButtonGroup();

		for (int j = 0; j < 4; j++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);
			autoRadioBtn[3][j] = new RadioButton();
			centerRow.add(autoRadioBtn[3][j]);
			grid.add(centerRow);

			autoRadioBtn[3][j].setGroup(autoBtnGroup[3]);

		}

		//Row 5

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_5 = new Label("1.5");
		GUIStyles.setFont(lbl1_5, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_5);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		lblElement[4] = new Label("¿Cuál considera que fue su dedicación " +
				"de tiempo al desarrollo del producto?");
		GUIStyles.setFont(lblElement[4], GUIStyles.NORMAL, 12);
		centerRow.add(lblElement[4]);
		grid.add(centerRow);

		autoBtnGroup[4] = new ButtonGroup();

		for (int j = 0; j < 4; j++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);
			autoRadioBtn[4][j] = new RadioButton();
			centerRow.add(autoRadioBtn[4][j]);
			grid.add(centerRow);

			autoRadioBtn[4][j].setGroup(autoBtnGroup[4]);

		}

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		centerRow.add(grid);
		col.add(centerRow);
		row.add(col);

		return row;

	}

	// --------------------------------------------------------------------------------

	private Row coEvaluation() {

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		Column col = new Column();
		col.setCellSpacing(new Extent(15));

		Row centerGrid = new Row();
		centerGrid.setAlignment(Alignment.ALIGN_CENTER);

		Grid grid = new Grid(5);
		grid.setInsets(new Insets(5, 5, 5, 5));
		grid.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
		grid.setColumnWidth(0, new Extent(80, Extent.PERCENT));
		grid.setColumnWidth(1, new Extent(5, Extent.PERCENT));
		grid.setColumnWidth(2, new Extent(5, Extent.PERCENT));
		grid.setColumnWidth(3, new Extent(5, Extent.PERCENT));
		grid.setColumnWidth(4, new Extent(5, Extent.PERCENT));

		Row centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblAuto = new Label("2. Co-evaluación");
		GUIStyles.setFont(lblAuto, GUIStyles.BOLD, 12);
		centerRow.add(lblAuto);
		col.add(centerRow);

		Label lblText = new Label("Evalúe el desempeño que usted considera que tuvieron " +
				"dentro de la compañía cada uno de sus integrantes.");
		GUIStyles.setFont(lblText, GUIStyles.BOLD, 12);
		col.add(lblText);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		//Header Row

		Label lblName = new Label("Nombre");
		GUIStyles.setFont(lblName, GUIStyles.BOLD, 12);
		centerRow.add(lblName);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblA = new Label("A");
		GUIStyles.setFont(lblA, GUIStyles.BOLD, 12);
		centerRow.add(lblA);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblB = new Label("B");
		GUIStyles.setFont(lblB, GUIStyles.BOLD, 12);
		centerRow.add(lblB);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblC = new Label("C");
		GUIStyles.setFont(lblC, GUIStyles.BOLD, 12);
		centerRow.add(lblC);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblD = new Label("D");
		GUIStyles.setFont(lblD, GUIStyles.BOLD, 12);
		centerRow.add(lblD);
		grid.add(centerRow);

		//Cargar Compañeros
		partnersList = Polls.loadPartnersData(app.getUser());

		coBtnGroup = new ButtonGroup[partnersList.size()];
		coRadioBtn = new RadioButton[partnersList.size()][4];

		for (int i = 0; i < partnersList.size(); i++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);

			Label lblPartner = new Label(partnersList.get(i));
			GUIStyles.setFont(lblPartner, GUIStyles.NORMAL, 12);
			centerRow.add(lblPartner);
			grid.add(centerRow);

			coBtnGroup[i] = new ButtonGroup();

			for (int j = 0; j < 4; j++) {

				centerRow = new Row();
				centerRow.setAlignment(Alignment.ALIGN_CENTER);
				coRadioBtn[i][j] = new RadioButton();
				centerRow.add(coRadioBtn[i][j]);
				grid.add(centerRow);

				coRadioBtn[i][j].setGroup(coBtnGroup[i]);

			}

		}

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		centerRow.add(grid);
		col.add(centerRow);
		row.add(col);

		return row;

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unused")
	private boolean checkOptionSelected(ButtonGroup buttonGroup) {

		RadioButton[] buttons = buttonGroup.getButtons();

		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].isSelected()) {
				return true;
			}
		}

		return false;

	}

	// --------------------------------------------------------------------------------

	private void showWindowHelp() {

		AutoCoInstructionsPane pane = new AutoCoInstructionsPane();
		app.getDesktop().setInstructionWindow(pane);

	}

	// --------------------------------------------------------------------------------

	private void btnNextClicked() {

		//TODO

		try {
			Polls.exportAutoEvaluation(this, app.getUser());
		} catch (IOException e) {
			e.printStackTrace();
		}

		col.remove(buttonRow);
		col.remove(autoEvaluation);

		coEvaluation = coEvaluation();
		col.add(coEvaluation);

		buttonRow = new Row();
		buttonRow.setAlignment(Alignment.ALIGN_CENTER);

		btnNext = new Button("Enviar");
		btnNext.setStyle(GUIStyles.BUTTON_STYLE);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSendClicked();
			}
		});
		buttonRow.add(btnNext);
		col.add(buttonRow);

	}

	// --------------------------------------------------------------------------------

	private void btnSendClicked() {

		try {
			Polls.exportCoEvaluation(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MainPane pane = new MainPane();
		app.getDesktop().setCentralPanel(pane);

	}

	// --------------------------------------------------------------------------------

	public Label[] getLblElement() {
		return lblElement;
	}

	public void setLblElement1(Label[] lblElement) {
		this.lblElement = lblElement;
	}

	// --------------------------------------------------------------------------------

	public List<String> getPartnersList() {
		return partnersList;
	}

	public void setPartnersList(List<String> partnersList) {
		this.partnersList = partnersList;
	}

	// --------------------------------------------------------------------------------

	public ButtonGroup[] getBtnGroupAuto() {
		return autoBtnGroup;
	}

	public void setBtnGroupAuto(ButtonGroup[] btnGroupAuto) {
		this.autoBtnGroup = btnGroupAuto;
	}

	public ButtonGroup[] getBtnGroupCo() {
		return coBtnGroup;
	}

	public void setBtnGroupCo(ButtonGroup[] btnGroupCo) {
		this.coBtnGroup = btnGroupCo;
	}

	// --------------------------------------------------------------------------------

	public RadioButton[][] getCoRadioBtn() {
		return coRadioBtn;
	}

	public void setCoRadioBtn(RadioButton[][] coRadioBtn) {
		this.coRadioBtn = coRadioBtn;
	}

	public RadioButton[][] getAutoRadioBtn() {
		return autoRadioBtn;
	}

	public void setAutoRadioBtn(RadioButton[][] autoRadioBtn) {
		this.autoRadioBtn = autoRadioBtn;
	}

	// --------------------------------------------------------------------------------

}
