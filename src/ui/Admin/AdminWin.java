package ui.Admin;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import ui.LoginWin;

import org.eclipse.swt.widgets.Composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import bean.Student;
import biz.AdminBiz;
import biz.BizException;
import dao.StuDao;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import util.DBHelper;
import util.SwtHelper;

public class AdminWin {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Table table;
	String[] college = new String[] { "", "计信学院", "经管学院", "材化学院", "数能学院", "电信学院", "建工学院", "外国语学院", "机械学院" };
	String[] state = new String[] { "正常", "挂失中", "已注销" };

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminWin window = new AdminWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		SwtHelper.center(shell);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(AdminWin.class, "/imges/guanliyuan.jpg"));
		shell.setSize(969, 492);
		shell.setText("管理员界面");
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new GridLayout(8, false));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("姓名:");

		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 130;
		text.setLayoutData(gd_text);

		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("学号:");

		text_1 = new Text(composite, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 130;
		text_1.setLayoutData(gd_text_1);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddDialog ad = new AddDialog(shell, SWT.NONE);
				ad.open();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 66;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("注册");

		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modify();
			}
		});
		GridData gd_btnNewButton_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_2.widthHint = 66;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		btnNewButton_2.setText("修改");

		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lost();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 66;
		button.setLayoutData(gd_button);
		button.setText("挂失");

		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reback();
			}
		});
		GridData gd_btnNewButton_5 = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton_5.widthHint = 66;
		btnNewButton_5.setLayoutData(gd_btnNewButton_5);
		btnNewButton_5.setText("补办");
		btnNewButton_5.setToolTipText("需收补办费用10元");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("班级:");

		text_2 = new Text(composite, SWT.BORDER);
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 130;
		text_2.setLayoutData(gd_text_2);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("学院:");

		Combo combo = new Combo(composite, SWT.NONE);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 116;
		combo.setLayoutData(gd_combo);
		combo.setItems(college);
		System.out.println(combo);

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(subString(String.valueOf(combo), "{", "}"));
				String coo = subString(String.valueOf(combo), "{", "}");

				int index = 0;
				if (coo == null || coo.trim().isEmpty()) {

				} else {
					index = findString(coo);
				}

				System.out.println("aaa " + index);
				query(text.getText(), text_1.getText(), text_2.getText(), index);

			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 66;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("查询");

		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				logout();
			}
		});
		GridData gd_btnNewButton_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_3.widthHint = 66;
		btnNewButton_3.setLayoutData(gd_btnNewButton_3);
		btnNewButton_3.setText("注销");

		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AdminWin.this.shell.dispose();
				new LoginWin().open();
			}
		});
		GridData gd_btnNewButton_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_4.widthHint = 66;
		btnNewButton_4.setLayoutData(gd_btnNewButton_4);
		btnNewButton_4.setText("返回");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EmailDialog ed = new EmailDialog(shell, SWT.NONE);
				ed.open();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 66;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("信箱");

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("学号");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("姓名");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("性别");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("年龄");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("班级");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("学院");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("余额");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("邮箱");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("状态");
		StuDao sDao = new StuDao();

		getStuInfo();
	}

	protected void reback() {
		// 获取当前选择的表格中的行
		TableItem[] items = table.getSelection();
		if (items == null || items.length == 0) {
			// 创建消息框
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("请选择要挂失的学生");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		TableItem item = items[0];
		int sno = Integer.valueOf(item.getText(0));
		int state = findString1(item.getText(8));
		if (state == 0) {
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("此卡不需要补办");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		AdminBiz aBiz = new AdminBiz();
		try {
			aBiz.update(0.0f, 0, sno);
		} catch (BizException e) {
			MessageBox mb = new MessageBox(shell);
			mb.setText("系统提示");
			mb.setMessage(e.getMessage());
			mb.open();
		}
		MessageBox mb = new MessageBox(shell);
		mb.setMessage("挂失成功!");
		mb.setText("系统提示");
		mb.open();
		getStuInfo();

	}

	protected void lost() {
		// 获取当前选择的表格中的行
		TableItem[] items = table.getSelection();
		if (items == null || items.length == 0) {
			// 创建消息框
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("请选择要挂失的学生");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		TableItem item = items[0];
		int sno = Integer.valueOf(item.getText(0));
		int state = findString1(item.getText(8));
		if (state == 2) {
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("此卡已注销");
			mb.setText("系统提示");
			mb.open();
			return;
		} else if (state == 1) {
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("此卡已挂失");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		AdminBiz aBiz = new AdminBiz();
		aBiz.update(1, sno);
		MessageBox mb = new MessageBox(shell);
		mb.setMessage("挂失成功!");
		mb.setText("系统提示");
		mb.open();
		getStuInfo();
	}

	protected void logout() {
		// 获取当前选择的表格中的行
		TableItem[] items = table.getSelection();
		if (items == null || items.length == 0) {
			// 创建消息框
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("请选择要注销的学生");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		TableItem item = items[0];
		int sno = Integer.valueOf(item.getText(0));
		int state = findString1(item.getText(8));
		if (state == 2) {
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("此卡已注销");
			mb.setText("系统提示");
			mb.open();
			return;
		} else if (state == 1) {
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("此卡已挂失");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		AdminBiz aBiz = new AdminBiz();
		aBiz.update(2, sno);
		MessageBox mb = new MessageBox(shell);
		mb.setMessage("注销成功!");
		mb.setText("系统提示");
		mb.open();
		getStuInfo();

	}

	protected void modify() {
		// 获取当前选择的表格中的行
		TableItem[] items = table.getSelection();
		if (items == null || items.length == 0) {
			// 创建消息框
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("请选择要修改的学生");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		TableItem item = items[0];
		ModDialog md = new ModDialog(shell, SWT.NONE);
		md.setItem(item);
		md.open();
		getStuInfo();
	}

	/**
	 * 截取字符串str中指定字符 strStart、strEnd之间的字符串
	 * @return
	 */
	public static String subString(String str, String strStart, String strEnd) {

		/* 找出指定的2个字符在 该字符串里面的 位置 */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index 为负数 即表示该字符串中 没有该字符 */
		if (strStartIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
		}
		if (strEndIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
		}
		/* 开始截取 */
		return str.substring(strStartIndex, strEndIndex).substring(strStart.length());
	}

	protected void query(String sname, String stuno, String sclass, int stucid) {
		try {

			int sno = 0;
			if (stuno == null || stuno.trim().isEmpty()) {
				sno = 0;
			} else {
				sno = Integer.parseInt(stuno);
			}

			Student stu = new Student();
			stu.setSname(sname);
			stu.setSno(sno);
			stu.setSclass(sclass);
			stu.setCid(stucid);

			table.removeAll();
			AdminBiz tb = new AdminBiz();
			List<Student> list = tb.selectByStu(stu);

			for (Student stu1 : list) {
				TableItem tbItem = new TableItem(table, SWT.NONE);
				tbItem.setText(new String[] { "" + stu1.getSno(), "" + stu1.getSname(), "" + stu1.getSsex(),
						"" + stu1.getSage(), "" + stu1.getSclass(), "" + college[stu1.getCid()], "" + stu1.getSmo(),
						"" + stu1.getSma(), "" + state[stu1.getState()] });

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getStuInfo() {
		try {
			String sql = "select * from student where 1 = 1";
			DBHelper dbh = new DBHelper();
			List<Student> list = dbh.query(sql, Student.class);
			table.removeAll();
			for (Student stu : list) {
				TableItem tbItem = new TableItem(table, SWT.NONE);
				tbItem.setText(new String[] { "" + stu.getSno(), "" + stu.getSname(), "" + stu.getSsex(),
						"" + stu.getSage(), "" + stu.getSclass(), "" + college[stu.getCid()], "" + stu.getSmo(),
						"" + stu.getSma(), "" + state[stu.getState()] });

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int findString(String key) {
		int i;
		for (i = 0; i < college.length; i++) {
			if (college[i].equals(key)) {
				System.out.println(i);
				return i;
			}
		}
		return i;

	}

	public int findString1(String key) {
		int i;
		for (i = 0; i < state.length; i++) {
			if (state[i].equals(key)) {
				System.out.println(i);
				return i;
			}
		}
		return i;

	}
}
