package com.rais.manager.interfaz;

import java.util.List;

import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.Panel;
import nextapp.echo.app.ResourceImageReference;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.rais.manager.RaisManagerApp;
import com.rais.manager.TestTableModel;
import com.rais.manager.controller.Data;
import com.rais.manager.controller.Polls;
import com.rais.manager.database.Poll;
import com.rais.manager.database.User;
import com.rais.manager.styles.GUIStyles;

@SuppressWarnings("serial")
public class MainPane extends Panel {

	private RaisManagerApp app = (RaisManagerApp) //
			RaisManagerApp.getActive();

	private User user;
	private int pollNum;

	private TestTableModel tableDtaModel;
	private List<Poll> pollList;

	private Row tableRow;

	// --------------------------------------------------------------------------------

	public MainPane() {

		user = Data.loadUser(app.getUser().getId());
		initGui();

	}

	// --------------------------------------------------------------------------------

	private void initGui() {

		setStyle(GUIStyles.CENTER_PANEL_STYLE);

		Column col = new Column();
		col.setCellSpacing(new Extent(30));

		col.add(Constructor.initTopRow("Hola " + user.getName() + ", Bienvenido!"));

		if (user.getTeacherRef() == null) {
			col.add(studentRow());
		} else {
			col.add(teacherComponent());
		}

		add(col);

	}

	// --------------------------------------------------------------------------------

	private Row studentRow() {

		pollNum = Polls.checkPendingPolls(user);

		if (pollNum == 0) {
			return Constructor.initTopRow( //
					"No tienes encuestas pendiente", 14);
		}

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);
		row.setCellSpacing(new Extent(30));

		row.add(Constructor.initTopRow( //
				"Tienes " + pollNum + " encuesta pendiente", 14));

		Button btnAnswerPoll = new Button();
		btnAnswerPoll.setToolTipText("Responder encuesta");
		btnAnswerPoll.setHeight(new Extent(30));
		btnAnswerPoll.setWidth(new Extent(30));
		btnAnswerPoll.setBackgroundImage(new FillImage( //
				new ResourceImageReference("/com/rais/manager/images/notification.png", //
						new Extent(30), new Extent(30)), //
						new Extent(0), new Extent(0), FillImage.NO_REPEAT));
		btnAnswerPoll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnAnswerPollClicked();
			}
		});
		row.add(btnAnswerPoll);

		return row;

	}

	// --------------------------------------------------------------------------------

	private Component teacherComponent() {

		Column col = new Column();

		// ----------------------------------------
		// Cargar encuestas sin contestar
		// ----------------------------------------

		tableDtaModel = new TestTableModel();
		try {
			Polls.getNoAnsweredPolls(this, tableDtaModel, pollList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (pollList.isEmpty()) {
			return col;
		}

		Row row = new Row();
		row.setStyle(GUIStyles.CENTER_ROW_STYLE);
		row.setCellSpacing(new Extent(30));

		tableRow = new Row();
		tableRow.setStyle(GUIStyles.CENTER_ROW_STYLE);
		tableRow.setVisible(false);

		row.add(Constructor.initTopRow( //
				"Hay " + pollList.size() + " encuestas sin contestar", 14));

		Button btnDetails = new Button();
		btnDetails.setToolTipText("Ver detalles");
		btnDetails.setHeight(new Extent(30));
		btnDetails.setWidth(new Extent(30));
		btnDetails.setBackgroundImage(new FillImage( //
				new ResourceImageReference("/com/rais/manager/images/notification.png", //
						new Extent(30), new Extent(30)), //
						new Extent(0), new Extent(0), FillImage.NO_REPEAT));
		btnDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				tableRow.setVisible(!tableRow.isVisible());
			}
		});
		row.add(btnDetails);
		col.add(row);

		tableRow.add(Constructor.initTable(tableDtaModel, initTableColModel(), true));

		col.add(tableRow);
		return col;

	}

	// --------------------------------------------------------------------------------

	private TableColModel initTableColModel() {

		TableColModel tableColModel = new TableColModel();
		TableColumn tableColumn;

		tableColumn = new TableColumn() {
			@Override
			public Object getValue(ETable table, Object element) {
				Poll poll = (Poll) element;
				return poll.getId();
			}
		};
		tableColumn.setWidth(new Extent(30));
		tableColumn.setHeadValue("");
		tableColumn.setHeadCellRenderer(new LabelCellRenderer());
		tableColumn.setDataCellRenderer(new LabelCellRenderer());
		tableColModel.getTableColumnList().add(tableColumn);

		tableColumn = new TableColumn() {
			@Override
			public Object getValue(ETable table, Object element) {
				Poll poll = (Poll) element;
				return poll.getStudentRef().getUserRef().getName();
			}
		};
		tableColumn.setWidth(new Extent(200));
		tableColumn.setHeadValue("");
		tableColumn.setHeadCellRenderer(new LabelCellRenderer());
		tableColumn.setDataCellRenderer(new LabelCellRenderer());
		tableColModel.getTableColumnList().add(tableColumn);

		return tableColModel;

	}

	// --------------------------------------------------------------------------------

	private void btnAnswerPollClicked() {

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

	public List<Poll> getPollList() {
		return pollList;
	}

	public void setPollList(List<Poll> pollList) {
		this.pollList = pollList;
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
