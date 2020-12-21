package ui.student;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.security.GeneralSecurityException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import util.EmailHelper;
import util.DBHelper;
import biz.BizException;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChangeEmailDialog extends Dialog {

    protected Object result;
    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;
    private int radomInt = new Random().nextInt(999999);
    ;

    private String email;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public ChangeEmailDialog(Shell parent, int style) {
        super(parent, style);
        setText("修改邮箱");
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
        shell.setSize(450, 353);
        shell.setText("修改邮箱");
        shell.setLayout(new FormLayout());

        Label lblNewLabel = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel = new FormData();
        fd_lblNewLabel.top = new FormAttachment(0, 31);
        fd_lblNewLabel.left = new FormAttachment(0, 50);
        lblNewLabel.setLayoutData(fd_lblNewLabel);
        lblNewLabel.setText("旧邮箱地址");

        text = new Text(shell, SWT.BORDER);
        FormData fd_text = new FormData();
        fd_text.left = new FormAttachment(lblNewLabel, 62);
        fd_text.top = new FormAttachment(0, 31);
        text.setLayoutData(fd_text);

        Label label = new Label(shell, SWT.NONE);
        FormData fd_label = new FormData();
        fd_label.right = new FormAttachment(0, 126);
        fd_label.top = new FormAttachment(0, 99);
        fd_label.left = new FormAttachment(0, 50);
        label.setLayoutData(fd_label);
        label.setText("验证码");

        text_1 = new Text(shell, SWT.BORDER);
        FormData fd_text_1 = new FormData();
        fd_text_1.left = new FormAttachment(text, 0, SWT.LEFT);
        fd_text_1.right = new FormAttachment(100, -47);
        fd_text_1.top = new FormAttachment(label, -3, SWT.TOP);
        text_1.setLayoutData(fd_text_1);

        Button button = new Button(shell, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                YanZhengma();

            }
        });
        FormData fd_button = new FormData();
        fd_button.right = new FormAttachment(100, -10);
        button.setLayoutData(fd_button);
        button.setText("获取验证码");

        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    if (changeEmail()) {
                        shell.dispose();
                    }

                } catch (BizException e1) {
                    MessageBox msBox = new MessageBox(shell);
                    msBox.setText("系统提示");
                    msBox.setMessage(e1.getMessage());
                    msBox.open();

                }
            }
        });
        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.right = new FormAttachment(0, 178);
        fd_btnNewButton.bottom = new FormAttachment(100, -10);
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
        fd_text.right = new FormAttachment(btnNewButton_1, 0, SWT.RIGHT);
        fd_button.left = new FormAttachment(btnNewButton_1, 49, SWT.LEFT);
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.bottom = new FormAttachment(100, -10);
        fd_btnNewButton_1.right = new FormAttachment(100, -47);
        fd_btnNewButton_1.left = new FormAttachment(100, -157);
        btnNewButton_1.setLayoutData(fd_btnNewButton_1);
        btnNewButton_1.setText("返回");

        Label label_1 = new Label(shell, SWT.NONE);
        FormData fd_label_1 = new FormData();
        fd_label_1.top = new FormAttachment(label, 49);
        fd_label_1.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
        label_1.setLayoutData(fd_label_1);
        label_1.setText("新邮箱地址");

        text_2 = new Text(shell, SWT.BORDER);
        fd_button.top = new FormAttachment(text_2, 19);
        FormData fd_text_2 = new FormData();
        fd_text_2.left = new FormAttachment(text, 0, SWT.LEFT);
        fd_text_2.right = new FormAttachment(100, -47);
        fd_text_2.top = new FormAttachment(label_1, -3, SWT.TOP);
        text_2.setLayoutData(fd_text_2);

        text.setText(email);
    }

    //发送验证码获取验证码
    public void YanZhengma() {

        try {
            radomInt = new Random().nextInt(999999);
            EmailHelper eh = new EmailHelper();
            System.out.println("邮箱：" + email + "验证码： " + radomInt);
            eh.email2(email, String.valueOf(radomInt), name);
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

    public boolean changeEmail() throws BizException {

        String email = text_2.getText().trim();
        String yzm = text_1.getText().trim();
        if (yzm == null || yzm.isEmpty()) {
            throw new BizException("请输入验证码 !");
        }
        int YanZhengma = Integer.parseInt(yzm);
        if (isEmail(email)) {
            if (radomInt != YanZhengma) {
                throw new BizException("验证码不一致请重新获取 ! ");
            } else {
                String sql = "update student set sma = ? where sname = ?";
                new DBHelper().update(sql, email, name);
                shell.dispose();
                throw new BizException("修改成功");
            }
        } else {
            throw new BizException("请输入合法的邮箱 ! ");
        }

    }

    // 判断Email合法性
    public static boolean isEmail(String email) throws BizException {
        if (email == null || email.trim().isEmpty())
            return false;
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;

    }

}
