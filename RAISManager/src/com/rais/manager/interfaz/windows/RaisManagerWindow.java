package com.rais.manager.interfaz.windows;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImageBorder;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Panel;
import nextapp.echo.app.Row;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class RaisManagerWindow extends WindowPane {

	// --------------------------------------------------------------------------------

	public RaisManagerWindow() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	public RaisManagerWindow(String info) {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);
		setTitle("Información");
		setWidth(new Extent(300));
		setHeight(new Extent(150));
		setModal(true);
		setTitleFont(new Font(Font.MONOSPACE, Font.PLAIN, new Extent(14)));
		GUIStyles.setFont(this, GUIStyles.NORMAL);
		setTitleBackground(GUIStyles.COLOR_DEFAULT);
		setBackground(Color.WHITE);
		setBorder(new FillImageBorder(Color.BLACK, new Insets(2), new Insets(2)));

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));

		Row row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		Label lblInfo = new Label(info);
		lblInfo.setTextAlignment(Alignment.ALIGN_CENTER);
		row.add(lblInfo);
		col.add(row);

		row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		Button aceptBtn = new Button("Aceptar");
		aceptBtn.setStyle(GUIStyles.BUTTON_STYLE);
		aceptBtn.setWidth(new Extent(90));
		aceptBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				userClose();
			}
		});
		row.add(aceptBtn);

		col.add(row);
		add(col);

	}

	// --------------------------------------------------------------------------------

	public RaisManagerWindow(Panel panel) {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);
		setTitle("Instrucciones");
		setWidth(new Extent(500));
		setHeight(new Extent(400));
		setModal(true);
		setTitleFont(new Font(Font.MONOSPACE, Font.PLAIN, new Extent(14)));
		GUIStyles.setFont(this, GUIStyles.NORMAL);
		setTitleBackground(GUIStyles.COLOR_DEFAULT);
		setBackground(Color.WHITE);
		setBorder(new FillImageBorder(Color.BLACK, new Insets(2), new Insets(2)));

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));

		Row row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		row.add(panel);
		col.add(row);

		row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		Button aceptBtn = new Button("Aceptar");
		aceptBtn.setStyle(GUIStyles.BUTTON_STYLE);
		aceptBtn.setWidth(new Extent(90));
		aceptBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				userClose();
			}
		});
		row.add(aceptBtn);

		col.add(row);
		add(col);

	}

	// --------------------------------------------------------------------------------

	public RaisManagerWindow(Panel panel, int width, int height) {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);
		setTitle("Instrucciones");
		setWidth(new Extent(width));
		setHeight(new Extent(height));
		setModal(true);
		setTitleFont(new Font(Font.MONOSPACE, Font.PLAIN, new Extent(14)));
		GUIStyles.setFont(this, GUIStyles.NORMAL);
		setTitleBackground(GUIStyles.COLOR_DEFAULT);
		setBackground(Color.WHITE);
		setBorder(new FillImageBorder(Color.BLACK, new Insets(2), new Insets(2)));

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));

		Row row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		row.add(panel);
		col.add(row);

		row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		Button aceptBtn = new Button("Aceptar");
		aceptBtn.setStyle(GUIStyles.BUTTON_STYLE);
		aceptBtn.setWidth(new Extent(90));
		aceptBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				userClose();
			}
		});
		row.add(aceptBtn);

		col.add(row);
		add(col);

	}

	// --------------------------------------------------------------------------------

	public RaisManagerWindow(Panel panel, boolean withOkButton) {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);
		setTitle("Instrucciones");
		setWidth(new Extent(500));
		setHeight(new Extent(400));
		setModal(true);
		setTitleFont(new Font(Font.MONOSPACE, Font.PLAIN, new Extent(14)));
		GUIStyles.setFont(this, GUIStyles.NORMAL);
		setTitleBackground(GUIStyles.COLOR_DEFAULT);
		setBackground(Color.WHITE);
		setBorder(new FillImageBorder(Color.BLACK, new Insets(2), new Insets(2)));

		Column col = new Column();
		col.setInsets(new Insets(10, 10, 10, 10));
		col.setCellSpacing(new Extent(10));

		Row row = new Row();
		GUIStyles.setFont(row, GUIStyles.NORMAL);
		row.setAlignment(Alignment.ALIGN_CENTER);

		row.add(panel);
		col.add(row);

		if (withOkButton) {

			row = new Row();
			GUIStyles.setFont(row, GUIStyles.NORMAL);
			row.setAlignment(Alignment.ALIGN_CENTER);

			Button aceptBtn = new Button("Aceptar");
			aceptBtn.setStyle(GUIStyles.BUTTON_STYLE);
			aceptBtn.setWidth(new Extent(90));
			aceptBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					userClose();
				}
			});
			row.add(aceptBtn);
			col.add(row);

		}

		add(col);

	}

	// --------------------------------------------------------------------------------

}
