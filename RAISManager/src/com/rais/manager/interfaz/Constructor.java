package com.rais.manager.interfaz;

import com.rais.manager.styles.GUIStyles;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;

public class Constructor {

	public static Row initTopRow(String texto) {

		Row row = new Row();
		row.setCellSpacing(new Extent(10));
		row.setAlignment(Alignment.ALIGN_CENTER);
		Label lblTitle = new Label(texto);
		lblTitle.setForeground(Color.BLACK);
		GUIStyles.setFont(lblTitle, GUIStyles.BOLD, 16);
		row.add(lblTitle);
		return row;

	}

	// --------------------------------------------------------------------------------

	public static Row initTopRow(String texto, int size) {

		Row row = new Row();
		row.setCellSpacing(new Extent(10));
		row.setAlignment(Alignment.ALIGN_CENTER);
		Label lblTitle = new Label(texto);
		lblTitle.setForeground(Color.BLACK);
		GUIStyles.setFont(lblTitle, GUIStyles.BOLD, size);
		row.add(lblTitle);
		return row;

	}

	// --------------------------------------------------------------------------------

	public static Row initTopRow(String texto, int size, Color color) {

		Row row = new Row();
		row.setCellSpacing(new Extent(10));
		row.setAlignment(Alignment.ALIGN_CENTER);
		Label lblTitle = new Label(texto);
		lblTitle.setForeground(color);
		GUIStyles.setFont(lblTitle, GUIStyles.BOLD, size);
		row.add(lblTitle);
		return row;

	}

	// --------------------------------------------------------------------------------

/*	public static Component initTable(TestTableModel tableDtaModel, //
			TableColModel initTableColModel, boolean isPageable) {

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));
		col.setBackground(Color.WHITE);

		// ----------------------------------------
		// The table models
		// ----------------------------------------

		TableColModel tableColModel = initTableColModel;
		TableSelModel tableSelModel = new TableSelModel();
		tableDtaModel.setEditable(true);
		tableDtaModel.setPageSize(10);

		// ----------------------------------------
		// The table
		// ----------------------------------------

		ETable table = new ETable();
		table.setTableDtaModel(tableDtaModel);
		table.setTableColModel(tableColModel);
		table.setTableSelModel(tableSelModel);
		table.setEasyview(true);
		table.setBorder(new Border(1, Color.BLACK, Border.STYLE_NONE));
		table.setInsets(new Insets(5, 2, 5, 2));
//		GUIStyles.setFont(table, GUIStyles.NORMAL);
		col.add(table);

		// ----------------------------------------
		// The navigation control
		// ----------------------------------------

		if (isPageable) {

			Row row = new Row();
			row.setAlignment(Alignment.ALIGN_CENTER);

			ETableNavigation tableNavigation = new ETableNavigation(tableDtaModel);
			row.add(tableNavigation);

			col.add(row);

		}

		return col;
	}

	// --------------------------------------------------------------------------------

	public static Component initTable(TestTableModel tableDtaModel, //
			TableColModel initTableColModel, boolean isPageable, int pageSize) {

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));
		col.setBackground(Color.WHITE);

		// ----------------------------------------
		// The table models
		// ----------------------------------------

		TableColModel tableColModel = initTableColModel;
		TableSelModel tableSelModel = new TableSelModel();
		tableDtaModel.setEditable(true);
		tableDtaModel.setPageSize(pageSize);

		// ----------------------------------------
		// The table
		// ----------------------------------------

		ETable table = new ETable();
		table.setTableDtaModel(tableDtaModel);
		table.setTableColModel(tableColModel);
		table.setTableSelModel(tableSelModel);
		table.setEasyview(true);
		table.setBorder(new Border(1, Color.BLACK, Border.STYLE_NONE));
		table.setInsets(new Insets(5, 2, 5, 2));
//		GUIStyles.setFont(table, GUIStyles.NORMAL);
		col.add(table);

		// ----------------------------------------
		// The navigation control
		// ----------------------------------------

		if (isPageable) {

			Row row = new Row();
			row.setAlignment(Alignment.ALIGN_CENTER);

			ETableNavigation tableNavigation = new ETableNavigation(tableDtaModel);
			row.add(tableNavigation);

			col.add(row);

		}

		return col;
	}

	// --------------------------------------------------------------------------------

	public static Component initTable(TestTableModel tableDtaModel, //
			TableColModel initTableColModel, boolean isPageable, //
			int pageSize, int fontSize) {

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));
		col.setBackground(Color.WHITE);

		// ----------------------------------------
		// The table models
		// ----------------------------------------

		TableColModel tableColModel = initTableColModel;
		TableSelModel tableSelModel = new TableSelModel();
		tableDtaModel.setEditable(true);
		tableDtaModel.setPageSize(pageSize);

		// ----------------------------------------
		// The table
		// ----------------------------------------

		ETable table = new ETable();
		table.setTableDtaModel(tableDtaModel);
		table.setTableColModel(tableColModel);
		table.setTableSelModel(tableSelModel);
		table.setEasyview(true);
		table.setBorder(new Border(1, Color.BLACK, Border.STYLE_NONE));
		table.setInsets(new Insets(5, 2, 5, 2));
//		GUIStyles.setFont(table, GUIStyles.NORMAL, fontSize);
		col.add(table);

		// ----------------------------------------
		// The navigation control
		// ----------------------------------------

		if (isPageable) {

			Row row = new Row();
			row.setAlignment(Alignment.ALIGN_CENTER);

			ETableNavigation tableNavigation = new ETableNavigation(tableDtaModel);
			row.add(tableNavigation);

			col.add(row);

		}

		return col;
	}*/

}
