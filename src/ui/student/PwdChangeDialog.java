package ui.student;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import util.SwtHelper;
import biz.BizException;
import biz.StudentBiz;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class PwdChangeDialog extends Dialog {

    protected Object result;
    protected Shell shell;
    private Text textName;
    private Text textPwd;
    private Text textPwd2;
    private Text textYzm;

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public PwdChangeDialog(Shell parent, int style) {
        super(parent, style);
        setText("修改密码");
    }

    /**
     * Open the dialog.
     *
     * @return the result
     */
    public Object open() {
        createContents();
        SwtHelper.center(shell);
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
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
        shell.setText("忘记密码");
        shell.setSize(422, 364);
        shell.setLayout(new FormLayout());
        SwtHelper.center(shell);

        Label label = new Label(shell, SWT.NONE);
        FormData fd_label = new FormData();
        fd_label.left = new FormAttachment(0, 102);
        fd_label.top = new FormAttachment(0, 35);
        label.setLayoutData(fd_label);
        label.setBounds(63, 31, 54, 20);
        label.setText("用户名:");

        Label label_1 = new Label(shell, SWT.NONE);
        FormData fd_label_1 = new FormData();
        fd_label_1.top = new FormAttachment(label, 42);
        fd_label_1.left = new FormAttachment(label, 0, SWT.LEFT);
        label_1.setLayoutData(fd_label_1);
        label_1.setText("新密码:");
        label_1.setBounds(63, 88, 54, 20);

        Label label_1_1 = new Label(shell, SWT.NONE);
        FormData fd_label_1_1 = new FormData();
        fd_label_1_1.right = new FormAttachment(label, 0, SWT.RIGHT);
        label_1_1.setLayoutData(fd_label_1_1);
        label_1_1.setText("确认密码:");
        label_1_1.setBounds(63, 142, 71, 20);

        Label label_1_2 = new Label(shell, SWT.NONE);
        fd_label_1_1.bottom = new FormAttachment(label_1_2, -40);
        FormData fd_label_1_2 = new FormData();
        fd_label_1_2.right = new FormAttachment(label, 0, SWT.RIGHT);
        label_1_2.setLayoutData(fd_label_1_2);
        label_1_2.setText("验证码:");
        label_1_2.setBounds(63, 194, 54, 20);

        textName = new Text(shell, SWT.BORDER);
        FormData fd_textName = new FormData();
        fd_textName.left = new FormAttachment(label, 6);
        fd_textName.right = new FormAttachment(label, 148, SWT.RIGHT);
        fd_textName.top = new FormAttachment(label, -3, SWT.TOP);
        textName.setLayoutData(fd_textName);
        textName.setBounds(123, 25, 73, 26);

        textPwd = new Text(shell, SWT.BORDER);
        FormData fd_textPwd = new FormData();
        fd_textPwd.right = new FormAttachment(textName, 0, SWT.RIGHT);
        fd_textPwd.left = new FormAttachment(label_1, 6);
        fd_textPwd.top = new FormAttachment(label_1, -3, SWT.TOP);
        textPwd.setLayoutData(fd_textPwd);
        textPwd.setBounds(123, 82, 73, 26);

        textPwd2 = new Text(shell, SWT.BORDER);
        FormData fd_textPwd2 = new FormData();
        fd_textPwd2.top = new FormAttachment(label_1_1, -3, SWT.TOP);
        fd_textPwd2.left = new FormAttachment(label_1_1, 6);
        fd_textPwd2.right = new FormAttachment(100, -117);
        textPwd2.setLayoutData(fd_textPwd2);
        textPwd2.setBounds(140, 136, 73, 26);

        textYzm = new Text(shell, SWT.BORDER);
        fd_label_1_2.top = new FormAttachment(0, 218);
        FormData fd_textYzm = new FormData();
        fd_textYzm.right = new FormAttachment(label_1_2, 117, SWT.RIGHT);
        fd_textYzm.left = new FormAttachment(label_1_2, 6);
        fd_textYzm.top = new FormAttachment(label_1_2, -3, SWT.TOP);
        textYzm.setLayoutData(fd_textYzm);
        textYzm.setBounds(140, 191, 73, 26);

        Button button = new Button(shell, SWT.NONE);
        FormData fd_button = new FormData();
        fd_button.top = new FormAttachment(label_1_2, -5, SWT.TOP);
        fd_button.right = new FormAttachment(100, -22);
        button.setLayoutData(fd_button);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                StudentBiz sb = new StudentBiz();
                sb.YanZhengma(textName.getText());
            }
        });
        button.setBounds(246, 189, 98, 30);
        button.setText("获取验证码");

        Button button_1 = new Button(shell, SWT.NONE);
        button_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean a = false;
                int yzm = Integer.parseInt(textYzm.getText());
                StudentBiz sb = new StudentBiz();
                try {
                    try {
                        a = sb.changePw(textName.getText(), textPwd.getText(), textPwd2.getText(), yzm);
                    } catch (NumberFormatException e1) {
                        e1.printStackTrace();
                    }
                } catch (BizException e1) {
                    SwtHelper.message(e1.getMessage(),shell);
                } finally {
                    if (a) {
                        shell.dispose();
                    }
                }
            }
        });
        FormData fd_button_1 = new FormData();
        fd_button_1.left = new FormAttachment(0, 75);
        button_1.setLayoutData(fd_button_1);
        button_1.setBounds(57, 246, 98, 30);
        button_1.setText("确认");

        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        fd_button_1.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
        fd_button_1.right = new FormAttachment(btnNewButton, -68);
        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.bottom = new FormAttachment(100, -21);
        fd_btnNewButton.right = new FormAttachment(100, -64);
        fd_btnNewButton.left = new FormAttachment(0, 243);
        btnNewButton.setLayoutData(fd_btnNewButton);
        btnNewButton.setBounds(246, 246, 98, 30);
        btnNewButton.setText("取消");

    }
}
