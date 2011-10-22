package com.rais.manager.interfaz;

import java.util.List;

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
import nextapp.echo.app.RadioButton;
import nextapp.echo.app.Row;
import nextapp.echo.app.TextArea;
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

	private List<String> partnersList;

	private Column col;
	private Row buttonRow;
	private Button btnNext;
	private Row autoEvaluation;
	private Row coEvaluation;
	private Row questions;

	public AutoCoEvaluationPane() {

		setInsets(new Insets(5, 5, 5, 5));
		setAlignment(Alignment.ALIGN_CENTER);

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		col = new Column();
		col.setCellSpacing(new Extent(10));

		Row headerRow = new Row();
		headerRow.setAlignment(Alignment.ALIGN_CENTER);
		headerRow.setCellSpacing(new Extent(30));

		headerRow.add(Constructor.initTopRow("Cuestionario de Auto y Coevaluación", 14));

		Button btnHelp = new Button("?");
		btnHelp.setToolTipText("Ver Instrucciones");
		btnHelp.setStyle(GUIStyles.DEFAULT_STYLE);
		btnHelp.setHeight(new Extent(15));
		btnHelp.setWidth(new Extent(15));
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnHelpClicked();
			}
		});
		headerRow.add(btnHelp);
		col.add(headerRow);

		autoEvaluation = autoEvaluation();
		col.add(autoEvaluation);

		buttonRow = new Row();
		buttonRow.setAlignment(Alignment.ALIGN_CENTER);

		btnNext = new Button("Siguiente");
		btnNext.setStyle(GUIStyles.DEFAULT_STYLE);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnNext1Clicked();
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

		Label lblElement = new Label("Elemento");
		GUIStyles.setFont(lblElement, GUIStyles.BOLD, 12);
		centerRow.add(lblElement);
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

		Label lblElement1_1 = new Label("¿Cómo evalúa el producto desarrollado por su compañía? " +
				"\n(considere calidad del producto, logro de los objetivos, " +
				"cantidad de funcionalidad implementada, etcétera)");
		GUIStyles.setFont(lblElement1_1, GUIStyles.NORMAL, 12);
		centerRow.add(lblElement1_1);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnA1 = new RadioButton();
		centerRow.add(btnA1);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnB1 = new RadioButton();
		centerRow.add(btnB1);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnC1 = new RadioButton();
		centerRow.add(btnC1);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnD1 = new RadioButton();
		centerRow.add(btnD1);
		grid.add(centerRow);

		ButtonGroup btnGroup1 = new ButtonGroup();
		btnA1.setGroup(btnGroup1);
		btnB1.setGroup(btnGroup1);
		btnC1.setGroup(btnGroup1);
		btnD1.setGroup(btnGroup1);

		//Row 2

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_2 = new Label("1.2");
		GUIStyles.setFont(lbl1_2, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_2);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		Label lblElement1_2 = new Label("¿Cómo evalúa el desempeño de su compañía? " +
				"(considere el logro de objetivos, eficiencia, etcétera)");
		GUIStyles.setFont(lblElement1_2, GUIStyles.NORMAL, 12);
		centerRow.add(lblElement1_2);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnA2 = new RadioButton();
		centerRow.add(btnA2);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnB2 = new RadioButton();
		centerRow.add(btnB2);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnC2 = new RadioButton();
		centerRow.add(btnC2);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnD2 = new RadioButton();
		centerRow.add(btnD2);
		grid.add(centerRow);

		ButtonGroup btnGroup2 = new ButtonGroup();
		btnA2.setGroup(btnGroup2);
		btnB2.setGroup(btnGroup2);
		btnC2.setGroup(btnGroup2);
		btnD2.setGroup(btnGroup2);

		//Row 3

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_3 = new Label("1.3");
		GUIStyles.setFont(lbl1_3, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_3);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		Label lblElement1_3 = new Label("¿Cómo evalúa SU desempeño dentro de su compañía? " +
				"(considere el logro de objetivos, eficiencia, " +
				"participación, aportes, responsabilidad, etcétera)");
		GUIStyles.setFont(lblElement1_3, GUIStyles.NORMAL, 12);
		centerRow.add(lblElement1_3);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnA3 = new RadioButton();
		centerRow.add(btnA3);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnB3 = new RadioButton();
		centerRow.add(btnB3);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnC3 = new RadioButton();
		centerRow.add(btnC3);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnD3 = new RadioButton();
		centerRow.add(btnD3);
		grid.add(centerRow);

		ButtonGroup btnGroup3 = new ButtonGroup();
		btnA3.setGroup(btnGroup3);
		btnB3.setGroup(btnGroup3);
		btnC3.setGroup(btnGroup3);
		btnD3.setGroup(btnGroup3);

		//Row 4

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_4 = new Label("1.4");
		GUIStyles.setFont(lbl1_4, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_4);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		Label lblElement1_4 = new Label("¿Cómo evalúa de forma global el desempeño " +
				"de sus compañeros en su compañía? " +
				"(considere logro de objetivos, eficiencia, " +
				"participación, aportes, responsabilidad, etcétera)");
		GUIStyles.setFont(lblElement1_4, GUIStyles.NORMAL, 12);
		centerRow.add(lblElement1_4);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnA4 = new RadioButton();
		centerRow.add(btnA4);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnB4 = new RadioButton();
		centerRow.add(btnB4);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnC4 = new RadioButton();
		centerRow.add(btnC4);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnD4 = new RadioButton();
		centerRow.add(btnD4);
		grid.add(centerRow);

		ButtonGroup btnGroup4 = new ButtonGroup();
		btnA4.setGroup(btnGroup4);
		btnB4.setGroup(btnGroup4);
		btnC4.setGroup(btnGroup4);
		btnD4.setGroup(btnGroup4);

		//Row 5

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lbl1_5 = new Label("1.5");
		GUIStyles.setFont(lbl1_5, GUIStyles.NORMAL, 12);
		centerRow.add(lbl1_5);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_LEFT);

		Label lblElement1_5 = new Label("¿Cuál considera que fue su dedicación " +
				"de tiempo al desarrollo del producto?");
		GUIStyles.setFont(lblElement1_5, GUIStyles.NORMAL, 12);
		centerRow.add(lblElement1_5);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnA5 = new RadioButton();
		centerRow.add(btnA5);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnB5 = new RadioButton();
		centerRow.add(btnB5);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnC5 = new RadioButton();
		centerRow.add(btnC5);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		RadioButton btnD5 = new RadioButton();
		centerRow.add(btnD5);
		grid.add(centerRow);

		ButtonGroup btnGroup5 = new ButtonGroup();
		btnA5.setGroup(btnGroup5);
		btnB5.setGroup(btnGroup5);
		btnC5.setGroup(btnGroup5);
		btnD5.setGroup(btnGroup5);

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
		partnersList = Polls.loadPartnersData();

		for (int i = 0; i < partnersList.size(); i++) {

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);

			Label lblPartner = new Label(partnersList.get(i));
			GUIStyles.setFont(lblPartner, GUIStyles.NORMAL, 12);
			centerRow.add(lblPartner);
			grid.add(centerRow);

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);

			RadioButton btnA = new RadioButton();
			centerRow.add(btnA);
			grid.add(centerRow);

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);

			RadioButton btnB = new RadioButton();
			centerRow.add(btnB);
			grid.add(centerRow);

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);

			RadioButton btnC = new RadioButton();
			centerRow.add(btnC);
			grid.add(centerRow);

			centerRow = new Row();
			centerRow.setAlignment(Alignment.ALIGN_CENTER);

			RadioButton btnD = new RadioButton();
			centerRow.add(btnD);
			grid.add(centerRow);

			ButtonGroup btnGroup = new ButtonGroup();
			btnA.setGroup(btnGroup);
			btnB.setGroup(btnGroup);
			btnC.setGroup(btnGroup);
			btnD.setGroup(btnGroup);

		}

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		centerRow.add(grid);
		col.add(centerRow);
		row.add(col);

		return row;

	}

	// --------------------------------------------------------------------------------

	private Row questions() {

		int widthPercent = 100;
		int height = 100;

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		Column col = new Column();
		col.setCellSpacing(new Extent(15));

		Label lblQuestion1 = new Label("3. ¿Considera que el desempeño del gerente " +
				"de la compañía ha sido adecuado? " +
				"Si la respuesta es negativa, explique de forma breve " +
				"la razón y proponga un nuevo gerente.");
		GUIStyles.setFont(lblQuestion1, GUIStyles.NORMAL);
		col.add(lblQuestion1);

		TextArea txtAnswer1 = new TextArea();
		GUIStyles.setFont(txtAnswer1, GUIStyles.NORMAL);
		txtAnswer1.setWidth(new Extent(widthPercent, Extent.PERCENT));
		txtAnswer1.setHeight(new Extent(height));
		col.add(txtAnswer1);

		Label lblQuestion2 = new Label("4. ¿Considera que el desempeño del director " +
				"de la compañía ha sido adecuado? Si la respuesta es negativa, " +
				"explique de forma breve la razón y proponga un nuevo director.");
		GUIStyles.setFont(lblQuestion2, GUIStyles.NORMAL);
		col.add(lblQuestion2);

		TextArea txtAnswer2 = new TextArea();
		GUIStyles.setFont(txtAnswer2, GUIStyles.NORMAL);
		txtAnswer2.setWidth(new Extent(widthPercent, Extent.PERCENT));
		txtAnswer2.setHeight(new Extent(height));
		col.add(txtAnswer2);

		Label lblQuestion3 = new Label("5. Si pudiera retroceder en el tiempo " +
				"y cambiar algo en relación al desarrollo del producto, " +
				"la interacción con sus compañeros, su participación " +
				"en el curso/producto, etcétera, ¿Qué sería?");
		GUIStyles.setFont(lblQuestion3, GUIStyles.NORMAL);
		col.add(lblQuestion3);

		TextArea txtAnswer3 = new TextArea();
		GUIStyles.setFont(txtAnswer3, GUIStyles.NORMAL);
		txtAnswer3.setWidth(new Extent(widthPercent, Extent.PERCENT));
		txtAnswer3.setHeight(new Extent(height));
		col.add(txtAnswer3);

		row.add(col);
		return row;

	}

	// --------------------------------------------------------------------------------

	private void btnHelpClicked() {

		AutoCoInstructionsPane pane = new AutoCoInstructionsPane();
		app.getDesktop().setInstructionWindow(pane);

	}

	// --------------------------------------------------------------------------------

	private void btnNext1Clicked() {

		col.remove(buttonRow);
		col.remove(autoEvaluation);

		coEvaluation = coEvaluation();
		col.add(coEvaluation);

		buttonRow = new Row();
		buttonRow.setAlignment(Alignment.ALIGN_CENTER);

		btnNext = new Button("Siguiente");
		btnNext.setStyle(GUIStyles.DEFAULT_STYLE);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
//				btnNext2Clicked();
				sendPoll();
			}
		});
		buttonRow.add(btnNext);
		col.add(buttonRow);

	}

	// --------------------------------------------------------------------------------

	@SuppressWarnings("unused")
	private void btnNext2Clicked() {

		col.remove(buttonRow);
		col.remove(coEvaluation);

		questions = questions();
		col.add(questions);

		buttonRow = new Row();
		buttonRow.setAlignment(Alignment.ALIGN_CENTER);

		btnNext = new Button("Enviar");
		btnNext.setStyle(GUIStyles.DEFAULT_STYLE);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				sendPoll();
			}
		});
		buttonRow.add(btnNext);
		col.add(buttonRow);

	}

	// --------------------------------------------------------------------------------

	private void sendPoll() {

		//TODO
		MainPane pane = new MainPane();
		app.getDesktop().setCentralPanel(pane);

	}

	// --------------------------------------------------------------------------------

	public List<String> getPartnersList() {
		return partnersList;
	}

	public void setPartnersList(List<String> partnersList) {
		this.partnersList = partnersList;
	}

	// --------------------------------------------------------------------------------

}
