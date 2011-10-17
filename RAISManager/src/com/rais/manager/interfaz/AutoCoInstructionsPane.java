package com.rais.manager.interfaz;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;

import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class AutoCoInstructionsPane extends Panel {

	public AutoCoInstructionsPane() {

		setInsets(new Insets(5, 5, 5, 5));
		setAlignment(Alignment.ALIGN_CENTER);

		Row row = new Row();
		row.setAlignment(Alignment.ALIGN_CENTER);
		GUIStyles.setFont(row, GUIStyles.NORMAL, 12);

		Column col = new Column();
		col.setCellSpacing(new Extent(15));

		col.add(Constructor.initTopRow("Instrucciones"));

		Label lbl1 = new Label("Por favor lea cuidadosamente cada una " +
				"de las preguntas planteadas y responda según sea su nivel " +
				"de apreciación con una sola opción.");
		GUIStyles.setFont(lbl1, GUIStyles.NORMAL);
		col.add(lbl1);

		Label lbl2 = new Label("Las posibles opciones para las respuestas son:");
		GUIStyles.setFont(lbl2, GUIStyles.NORMAL);
		col.add(lbl2);

		Row centerGrid = new Row();
		centerGrid.setAlignment(Alignment.ALIGN_CENTER);

		Grid grid = new Grid(4);
		grid.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
		grid.setWidth(new Extent(300));
		grid.setColumnWidth(0, new Extent(25, Extent.PERCENT));
		grid.setColumnWidth(1, new Extent(25, Extent.PERCENT));
		grid.setColumnWidth(2, new Extent(25, Extent.PERCENT));
		grid.setColumnWidth(3, new Extent(25, Extent.PERCENT));

		Row centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblA = new Label("A");
		GUIStyles.setFont(lblA, GUIStyles.NORMAL);
		centerRow.add(lblA);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblB = new Label("B");
		GUIStyles.setFont(lblB, GUIStyles.NORMAL);
		centerRow.add(lblB);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblC = new Label("C");
		GUIStyles.setFont(lblC, GUIStyles.NORMAL);
		centerRow.add(lblC);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblD = new Label("D");
		GUIStyles.setFont(lblD, GUIStyles.NORMAL);
		centerRow.add(lblD);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblExcelent = new Label("Excelente");
		GUIStyles.setFont(lblExcelent, GUIStyles.NORMAL);
		centerRow.add(lblExcelent);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblGood = new Label("Bueno");
		GUIStyles.setFont(lblGood, GUIStyles.NORMAL);
		centerRow.add(lblGood);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblRegular = new Label("Regular");
		GUIStyles.setFont(lblRegular, GUIStyles.NORMAL);
		centerRow.add(lblRegular);
		grid.add(centerRow);

		centerRow = new Row();
		centerRow.setAlignment(Alignment.ALIGN_CENTER);

		Label lblBad = new Label("Malo");
		GUIStyles.setFont(lblBad, GUIStyles.NORMAL);
		centerRow.add(lblBad);
		grid.add(centerRow);

		centerGrid.add(grid);
		col.add(centerGrid);

		Label lbl3 = new Label("Sea sincero en sus respuestas, tenga en mente " +
				"que esta información se cruza con la aportada por sus compañeros " +
				"y con la establecida en base al criterio del Jefe Ejecutivo.");
		GUIStyles.setFont(lbl3, GUIStyles.NORMAL);
		col.add(lbl3);

		Label lbl4 = new Label("Considere también que a pesar de no ser anónima " +
				"la información de esta encuesta es confidencial y sólo será vista " +
				"y utilizada por el Jefe Ejecutivo.");
		GUIStyles.setFont(lbl4, GUIStyles.NORMAL);
		col.add(lbl4);

		row.add(col);
		add(row);

	}

}
