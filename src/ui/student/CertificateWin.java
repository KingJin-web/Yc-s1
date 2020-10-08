package ui.student;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import util.DBHelper;
import bean.Certificate;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 学生申请证书界面
 * 
 * @author King
 *
 */
public class CertificateWin {

	protected Shell shell;
	private Table table;
	protected static String name = StudentCard.returnName();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CertificateWin window = new CertificateWin();
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
		shell.setImage(
				SWTResourceManager.getImage(CertificateWin.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shell.setSize(565, 314);
		shell.setText("证书申请");
		shell.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 189;
		gd_composite.widthHint = 545;
		composite.setLayoutData(gd_composite);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(139);
		tblclmnNewColumn.setText("申请时间");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(150);
		tblclmnNewColumn_1.setText("申请内容");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(140);
		tableColumn.setText("同意时间");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("申请状态");

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.heightHint = 65;
		gd_composite_1.widthHint = 537;
		composite_1.setLayoutData(gd_composite_1);

		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 增加证书
				addCer();
			}
		});
		FormData fd_button_1 = new FormData();
		button_1.setLayoutData(fd_button_1);
		button_1.setText("新增证书");

		Button button_2 = new Button(composite_1, SWT.NONE);
		fd_button_1.top = new FormAttachment(button_2, 0, SWT.TOP);
		fd_button_1.right = new FormAttachment(button_2, -96);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new StudentCard().open();
				
			}
		});
		FormData fd_button_2 = new FormData();
		fd_button_2.top = new FormAttachment(0, 10);
		fd_button_2.right = new FormAttachment(100, -30);
		fd_button_2.left = new FormAttachment(0, 407);
		button_2.setLayoutData(fd_button_2);
		button_2.setText("返回");

		Button button = new Button(composite_1, SWT.NONE);
		fd_button_1.left = new FormAttachment(button, 91);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCer(); 
			}
		});
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0, 10);
		fd_button.left = new FormAttachment(0, 20);
		fd_button.right = new FormAttachment(100, -417);
		button.setLayoutData(fd_button);
		button.setText("刷新");

		
		selectCer();
	}

	protected void addCer() {
		CerDialog cerDialog = new CerDialog(shell, SWT.NONE);
		cerDialog.open();
	}

	public void selectCer() {
		System.out.println("姓名" + name);
		DBHelper dbHelper = new DBHelper();
		String sql = "select * from certificate where sname = ?";
		table.removeAll();
		List<Certificate> list = dbHelper.query(sql, Certificate.class, name);
		for (Certificate c : list) {
			TableItem tbItem = new TableItem(table, SWT.NONE);

			tbItem.setText(new String[] { "" + c.getTime(), "" + c.getZname(), "" + c.getSptime(), "" + c.getZt() });
		}

	}

	public static String returnName() {
		return name;
	}
}
