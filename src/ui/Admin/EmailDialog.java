package ui.Admin;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.List;

import org.eclipse.swt.SWT;
import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import bean.PEmail;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import util.DBHelper;

public class EmailDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private Text text;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public EmailDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shell.setImage(SWTResourceManager.getImage(EmailDialog.class, "/imges/youxiang.jpg"));
		shell.setSize(423, 321);
		shell.setText("信箱回复");
		shell.setLayout(new BorderLayout(0, 0));

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("学生姓名");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("写信内容");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(195);
		tblclmnNewColumn_2.setText("写信时间");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setBounds(0, 0, 76, 20);
		lblNewLabel.setText("回复内容:");

		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 345;
		text.setLayoutData(gd_text);

		Composite composite_1 = new Composite(composite, SWT.NONE);
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 100;
		composite_1.setLayoutData(gd_composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reEmail();
				query();
			}
		});
		btnNewButton.setText("回复");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 100;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("返回");

		query();
	}

	protected void reEmail() {
		// 获取当前选择的表格中的行
		TableItem[] items = table.getSelection();
		if (items == null || items.length == 0) {
			// 创建消息框
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("请选择要回复的学生");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		TableItem item = items[0];
		back(text.getText(),item.getText(1));
		MessageBox mb = new MessageBox(shell);
		mb.setMessage("回复成功!");
		mb.setText("系统提示");
		mb.open();
		return;
	}

	public void query() {
		try {
			String sql = "select * from pmail where ppreply is null order by mTime desc";
			DBHelper dbh = new DBHelper();
			List<PEmail> list = dbh.query(sql, PEmail.class);
			table.removeAll();
			for (PEmail pe : list) {
				TableItem tbItem = new TableItem(table, SWT.NONE);
				tbItem.setText(new String[] { "" + pe.getSname(), "" + pe.getSmessage(), "" + pe.getMtime() });

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back(String nr,String message) {
		String sql = "update pmail set ppreply=? , ptime=now() where smessage=?";
		DBHelper dbh = new DBHelper();
		dbh.update(sql, nr,message);
		
	}
}
