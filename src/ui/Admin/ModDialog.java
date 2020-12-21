package ui.Admin;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

import bean.Student;
import biz.AdminBiz;
import util.SwtLabelPaintListner;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class ModDialog extends Dialog {

	// 结果对象 取消 关闭返回数字 0
	protected Object result = 0;
	// 要修改的记录
	protected TableItem item;
	protected Shell shell;
	private Text textClass;
	private Text textEmail;
	String[] college = new String[] { "", "计信学院", "经管学院", "材化学院", "数能学院", "电信学院", "建工学院", "外国语学院", "机械学院" };
	private Combo combo;
	private Spinner spinnerAge;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ModDialog(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(442, 398);
		shell.setText("修改");
		shell.setLayout(new FormLayout());

		Label lblNewLabel = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 57);
		fd_lblNewLabel.left = new FormAttachment(0, 90);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("年龄:");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(0, 113);
		fd_lblNewLabel_1.left = new FormAttachment(0, 90);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("班级:");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(0, 164);
		fd_lblNewLabel_2.left = new FormAttachment(0, 90);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
		lblNewLabel_2.setText("学院:");

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		FormData fd_lblNewLabel_3 = new FormData();
		fd_lblNewLabel_3.top = new FormAttachment(0, 217);
		fd_lblNewLabel_3.left = new FormAttachment(0, 90);
		lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);
		lblNewLabel_3.setText("邮箱:");

		textClass = new Text(shell, SWT.BORDER);
		FormData fd_textClass = new FormData();
		fd_textClass.right = new FormAttachment(0, 336);
		fd_textClass.top = new FormAttachment(0, 110);
		fd_textClass.left = new FormAttachment(0, 130);
		textClass.setLayoutData(fd_textClass);

		textEmail = new Text(shell, SWT.BORDER);
		FormData fd_textEmail = new FormData();
		fd_textEmail.right = new FormAttachment(0, 336);
		fd_textEmail.top = new FormAttachment(0, 214);
		fd_textEmail.left = new FormAttachment(0, 130);
		textEmail.setLayoutData(fd_textEmail);

		combo = new Combo(shell, SWT.NONE);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(0, 336);
		fd_combo.top = new FormAttachment(0, 156);
		fd_combo.left = new FormAttachment(0, 130);
		combo.setLayoutData(fd_combo);
		combo.setItems(college);

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(0, 361);
		fd_btnNewButton_1.top = new FormAttachment(0, 290);
		fd_btnNewButton_1.left = new FormAttachment(0, 274);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setText("取消");

		spinnerAge = new Spinner(shell, SWT.BORDER);
		FormData fd_spinnerAge = new FormData();
		fd_spinnerAge.right = new FormAttachment(0, 251);
		fd_spinnerAge.top = new FormAttachment(0, 57);
		fd_spinnerAge.left = new FormAttachment(0, 130);
		spinnerAge.setLayoutData(fd_spinnerAge);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(subString(String.valueOf(combo), "{", "}"));
				String coo = subString(String.valueOf(combo), "{", "}");

				int index = 0;
				if (coo == null || coo.trim().isEmpty()) {

				} else {
					index = findString(coo);
				}
				String age = spinnerAge.getText();
				String sclass = textClass.getText();
				String email = textEmail.getText();
				int age1 = Integer.valueOf(age);
				save(age1, sclass, index, email);
				MessageBox msBox = new MessageBox(shell);
				msBox.setText("系统提示");
				msBox.setMessage("修改成功!");
				msBox.open();
				shell.dispose();
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(0, 155);
		fd_btnNewButton.top = new FormAttachment(0, 290);
		fd_btnNewButton.left = new FormAttachment(0, 68);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("保存");
		
		Label label = new Label(shell, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(ModDialog.class, "/imges/yuanqi.jpg"));
		label.setLayoutData(new FormData());
		label.addPaintListener(new SwtLabelPaintListner());

		if (item != null) {
			setStudent();
		}
	}

	public TableItem getItem() {
		return item;
	}

	public void setItem(TableItem item) {
		this.item = item;
	}

	protected void save(int sage, String sclass, int cid, String sma) {
		AdminBiz aBiz = new AdminBiz();
		aBiz.update(sage, sclass, cid, sma, Integer.parseInt(item.getText(0)));
	}

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
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
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

	private void setStudent() {
		String a = item.getText(3);
		int a1 = Integer.valueOf(a);
		spinnerAge.setSelection(a1);
		textClass.setText(item.getText(4));
		combo.setText(item.getText(5));
		textEmail.setText(item.getText(7));
	}
}
