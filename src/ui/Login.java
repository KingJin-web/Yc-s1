package ui;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import biz.BizException;
import biz.StudentBiz;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

import util.SwtHelper;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import ui.student.PwdChangeDialog;
import ui.student.StudentCard;

public class Login {

	protected Shell shell;
	private static Text text;
	private Text text_1;
	public static String name;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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

		SwtHelper.center(shell);
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
		shell.setText("登录");
		shell.setImage(SWTResourceManager.getImage(Login.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shell.setSize(328, 314);
		shell.setLayout(new BorderLayout(0, 0));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.CENTER);
		composite.setLayout(new FormLayout());

		Label lblNewLabel = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 33);
		fd_lblNewLabel.left = new FormAttachment(0, 34);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("用户名\t");

		text = new Text(composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(lblNewLabel, -3, SWT.TOP);
		fd_text.left = new FormAttachment(lblNewLabel, 40);
		fd_text.right = new FormAttachment(0, 265);
		text.setLayoutData(fd_text);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(0, 64);
		fd_lblNewLabel_1.left = new FormAttachment(0, 34);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("用户密码\t");

		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(lblNewLabel_1, -3, SWT.TOP);
		fd_text_1.left = new FormAttachment(text, 0, SWT.LEFT);
		fd_text_1.right = new FormAttachment(0, 265);
		text_1.setLayoutData(fd_text_1);

		Label label_1 = new Label(composite, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("权限选择\t");

		Combo combo = new Combo(composite, SWT.NONE);
		fd_label_1.top = new FormAttachment(combo, 3, SWT.TOP);
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(text_1, 31);
		fd_combo.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_combo.left = new FormAttachment(text, 0, SWT.LEFT);
		combo.setLayoutData(fd_combo);

		Button btnNewButton = new Button(composite, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100);
		fd_btnNewButton.left = new FormAttachment(0, 69);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				name = text.getText();
				if (login(name, text_1.getText())) {
					Login.this.shell.dispose();
					new StudentCard().open();
				} else {
					System.out.println("沙雕登录都不会你玩个锤子");
				}
 
			}

		});
		btnNewButton.setText("确认登录");

		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PwdChangeDialog eed = new PwdChangeDialog(shell, SWT.NONE);
				eed.open();
			}
		});
		FormData fd_btnNewButton_2 = new FormData();
		fd_btnNewButton_2.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_2.right = new FormAttachment(100, -10);
		btnNewButton_2.setLayoutData(fd_btnNewButton_2);
		btnNewButton_2.setText("忘记密码");

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnNewButton_1.left = new FormAttachment(text, 0, SWT.LEFT);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("退出登录");

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.WEST);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

	}

	protected boolean login(String sname, String spw) {
		StudentBiz sBiz = new StudentBiz();
		try {
			sBiz.login(sname, spw);
		} catch (BizException e) {
			SwtHelper.message(e.getMessage(),shell);
			return false;
		} finally {
			returnName();
		}
		return true;

	}

	public static String returnName() {
		return name;
	}
}
