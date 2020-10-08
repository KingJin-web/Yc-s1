package ui;

import biz.StudentBiz;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import biz.AdminBiz;
import biz.BizException;
import biz.TeacherBiz;
import ui.Admin.AdminWin;
import ui.student.StudentCard;
import ui.teacher.MainWin;
import util.HelpWin;
import util.SwtLabelPaintListner;
import util.SwtHelper;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginWin {

    protected Shell shell;
    private Text textNo;
    private Text textPwd;
    public static String name;

    private StudentBiz sBiz = new StudentBiz();
    private TeacherBiz tBiz = new TeacherBiz();
    private AdminBiz aBiz = new AdminBiz();

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            LoginWin window = new LoginWin();
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
        shell.setImage(SWTResourceManager.getImage(LoginWin.class, "/imges/login.jpg"));
        shell.setSize(410, 363);
        shell.setText("登录窗口");
        SwtHelper.center(shell);
        shell.setLayout(new FormLayout());

        Label label = new Label(shell, SWT.NONE);
        FormData fd_label = new FormData();
        fd_label.top = new FormAttachment(0, 55);
        fd_label.left = new FormAttachment(0, 65);
        label.setLayoutData(fd_label);
        label.setText("用户名:");

        Label label_1 = new Label(shell, SWT.NONE);
        FormData fd_label_1 = new FormData();
        fd_label_1.right = new FormAttachment(0, 114);
        fd_label_1.top = new FormAttachment(0, 123);
        fd_label_1.left = new FormAttachment(0, 65);
        label_1.setLayoutData(fd_label_1);
        label_1.setText("密   码:");

        textNo = new Text(shell, SWT.BORDER);
        FormData fd_textNo = new FormData();
        fd_textNo.right = new FormAttachment(0, 296);
        fd_textNo.top = new FormAttachment(0, 52);
        fd_textNo.left = new FormAttachment(0, 126);
        textNo.setLayoutData(fd_textNo);

        textPwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
        FormData fd_textPwd = new FormData();
        fd_textPwd.right = new FormAttachment(0, 296);
        fd_textPwd.top = new FormAttachment(0, 120);
        fd_textPwd.left = new FormAttachment(0, 126);
        textPwd.setLayoutData(fd_textPwd);

        Combo combo = new Combo(shell, SWT.NONE);
        FormData fd_combo = new FormData();
        fd_combo.right = new FormAttachment(0, 262);
        fd_combo.top = new FormAttachment(0, 183);
        fd_combo.left = new FormAttachment(0, 155);
        combo.setLayoutData(fd_combo);
        combo.setItems(new String[]{"教师", "管理员", "学生"});

        Label label_2 = new Label(shell, SWT.NONE);
        FormData fd_label_2 = new FormData();
        fd_label_2.top = new FormAttachment(0, 186);
        fd_label_2.left = new FormAttachment(0, 65);
        label_2.setLayoutData(fd_label_2);
        label_2.setText("用户权限:");

        Button btnNewButton = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.right = new FormAttachment(0, 146);
        fd_btnNewButton.top = new FormAttachment(0, 258);
        fd_btnNewButton.left = new FormAttachment(0, 51);
        btnNewButton.setLayoutData(fd_btnNewButton);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                name = textNo.getText();
                String pwd = textPwd.getText();
                try {

                    String str = String.valueOf(combo);
                    if ((str.contains("学生")) && (sBiz.select(name, 1))) {
                        sBiz.login(name, pwd);
                        LoginWin.this.shell.dispose();
                        new StudentCard().open();
                    } else if (str.contains("教师") && tBiz.select(name)) {
                        tBiz.login(name, pwd);
                        LoginWin.this.shell.dispose();
                        new MainWin().open();
                    } else if (str.contains("管理员")) {
                        aBiz.login(name, pwd);
                        LoginWin.this.shell.dispose();
                        new AdminWin().open();
                    } else {
                        MessageBox mb = new MessageBox(shell);
                        mb.setText("系统提示");
                        mb.setMessage("登录失败!");
                        mb.open();
                    }
                } catch (BizException e1) {
                    MessageBox mb = new MessageBox(shell);
                    mb.setText("系统提示");
                    mb.setMessage(e1.getMessage());
                    mb.open();
                } finally {
                    returnName();
                }
            }
        });
        btnNewButton.setText("登录");

        Button btnNewButton_1 = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.right = new FormAttachment(0, 329);
        fd_btnNewButton_1.top = new FormAttachment(0, 258);
        fd_btnNewButton_1.left = new FormAttachment(0, 234);
        btnNewButton_1.setLayoutData(fd_btnNewButton_1);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        btnNewButton_1.setText("取消");

        Button button = new Button(shell, SWT.NONE);
        FormData fd_button = new FormData();
        fd_button.bottom = new FormAttachment(0, 143);
        fd_button.top = new FormAttachment(0, 123);
        fd_button.left = new FormAttachment(0, 302);
        button.setLayoutData(fd_button);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new PwdChangeWin(shell, SWT.NONE).open();
            }
        });

        button.setImage(
                SWTResourceManager.getImage(LoginWin.class, "/org/eclipse/jface/dialogs/images/message_info.png"));
        button.setToolTipText("忘记密码");

        Button button_1 = new Button(shell, SWT.NONE);
        button_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    new HelpWin().open();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        FormData fd_button_1 = new FormData();
        fd_button_1.top = new FormAttachment(0, 276);
        fd_button_1.left = new FormAttachment(0, 353);
        button_1.setLayoutData(fd_button_1);
        button_1.setImage(SWTResourceManager.getImage("C:\\Users\\82427\\Desktop\\img\\wenhao.jpg"));
        button_1.setToolTipText("操作指南");

        Label label_3 = new Label(shell, SWT.NONE);
        FormData fd_label_3 = new FormData();
        fd_label_3.top = new FormAttachment(0);
        fd_label_3.left = new FormAttachment(0);
//        label_3.setLayoutData(fd_label_3);
//        label_3.setImage(SWTResourceManager.getImage(LoginWin.class, "/imges/baishi.jpg"));
//        label_3.addPaintListener(new SwtLabelPaintListner());

    }

    public static String returnName() {
        return name;
    }
}
