package ui.student;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import Util.sql.DBHelper;
import biz.BizException;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 充值界面
 * 
 * @author King
 *
 */
public class inMoneyDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String name;
	private Text text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public inMoneyDialog(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(inMoneyDialog.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shell.setSize(450, 300);
		shell.setText("充值");
		shell.setLayout(new FormLayout());

		Label label = new Label(shell, SWT.WRAP);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI Light", 13, SWT.BOLD | SWT.ITALIC));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label.setToolTipText("");
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 77);
		fd_label.right = new FormAttachment(0, 294);
		fd_label.top = new FormAttachment(0, 36);
		fd_label.left = new FormAttachment(0, 92);
		label.setLayoutData(fd_label);
		label.setText("欢迎" + getName() + "同学");

		Label label_1 = new Label(shell, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(label, 37);
		fd_label_1.left = new FormAttachment(0, 44);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("输入充值金额");

		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(label_1, -3, SWT.TOP);
		fd_text.right = new FormAttachment(100, -38);
		fd_text.left = new FormAttachment(100, -207);
		text.setLayoutData(fd_text);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					inMoney();
				} catch (BizException e1) {
					MessageBox msBox = new MessageBox(shell);
					msBox.setText("系统提示");
					msBox.setMessage(e1.getMessage());
					msBox.open();
				}
				
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(0, 162);
		fd_btnNewButton.bottom = new FormAttachment(100, -31);
		fd_btnNewButton.left = new FormAttachment(0, 80);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("确认");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.left = new FormAttachment(100, -156);
		fd_btnNewButton_1.bottom = new FormAttachment(100, -31);
		fd_btnNewButton_1.right = new FormAttachment(100, -74);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setText("返回");

	}

	protected void inMoney() throws BizException {
		DBHelper dbh = new DBHelper();
		String money = text.getText().trim();
		if (isNumber(money)) {
			String sql = "update student set smo = smo + ? where sname = ?";
			dbh.update(sql, money, getName());
			MessageBox msBox = new MessageBox(shell);
			msBox.setText("系统提示");
			msBox.setMessage("充值成功");
			msBox.open();
			//new StudentCard().query(name);
			shell.dispose();
		} else {
			throw new BizException("请输入正确的金额 ! ");
		}

	}

	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher((CharSequence) str);
		boolean result = matcher.matches();
		if (result) {
			return true;
		} else {
			return false;
		}

	}
}
