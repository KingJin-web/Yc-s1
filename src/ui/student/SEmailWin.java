package ui.student;

import org.eclipse.swt.widgets.Display;


import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import util.SwtHelper;
import util.DBHelper;
import bean.PEmail;
import biz.BizException;

import org.eclipse.swt.layout.FillLayout;

import java.util.List;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * 校长信箱 (学生界面)
 *
 * @author King
 */
public class SEmailWin {
    private static String name = StudentCard.returnName();
    protected Shell shell;
    private Text text;
    private Table table;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SEmailWin window = new SEmailWin();
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
        shell.setToolTipText("");
        shell.setImage(SWTResourceManager.getImage(SEmailWin.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
        shell.setSize(620, 523);
        shell.setText("校长信箱");
        shell.setLayout(new GridLayout(1, false));

        Label label = new Label(shell, SWT.NONE);
        label.setText("写信");

        Composite composite_1 = new Composite(shell, SWT.NONE);
        composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
        GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_1.heightHint = 116;
        gd_composite_1.widthHint = 599;
        composite_1.setLayoutData(gd_composite_1);

        text = new Text(composite_1, SWT.BORDER);
        new Label(shell, SWT.NONE);

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayout(new FormLayout());
        GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite.widthHint = 597;
        composite.setLayoutData(gd_composite);

        Button btnNewButton_1 = new Button(composite, SWT.NONE);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SEmailWin.this.shell.dispose();
                new StudentCard().open();
            }
        });
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.right = new FormAttachment(100, -74);
        fd_btnNewButton_1.left = new FormAttachment(100, -138);
        btnNewButton_1.setLayoutData(fd_btnNewButton_1);
        btnNewButton_1.setText("返回");

        Button btnNewButton_2 = new Button(composite, SWT.NONE);
        fd_btnNewButton_1.top = new FormAttachment(btnNewButton_2, 0, SWT.TOP);
        btnNewButton_2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    setEmail(text.getText());
                } catch (BizException e1) {
                    SwtHelper.message(e1.getMessage(), shell);
                    return;
                } finally {
                    getEmail();
                }
            }
        });
        FormData fd_btnNewButton_2 = new FormData();
        fd_btnNewButton_2.right = new FormAttachment(btnNewButton_1, -182);
        fd_btnNewButton_2.left = new FormAttachment(0, 207);
        fd_btnNewButton_2.top = new FormAttachment(0);
        btnNewButton_2.setLayoutData(fd_btnNewButton_2);
        btnNewButton_2.setText("提交");

        Label lblNull = new Label(shell, SWT.NONE);
        lblNull.setText("提示 ：显示为 null 就目前是没有回复");

        Composite composite_2 = new Composite(shell, SWT.NONE);
        composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
        GridData gd_composite_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_2.heightHint = 196;
        gd_composite_2.widthHint = 602;
        composite_2.setLayoutData(gd_composite_2);

        table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("留言时间");

        TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_1.setWidth(200);
        tblclmnNewColumn_1.setText("留言内容");

        TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_2.setWidth(100);
        tblclmnNewColumn_2.setText("回复时间");

        TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_3.setWidth(200);
        tblclmnNewColumn_3.setText("回复内容");

        Composite composite_3 = new Composite(shell, SWT.NONE);
        composite_3.setLayout(new FormLayout());
        GridData gd_composite_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_3.widthHint = 599;
        gd_composite_3.heightHint = 36;
        composite_3.setLayoutData(gd_composite_3);

        Button button = new Button(composite_3, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                LookMail();
            }
        });
        FormData fd_button = new FormData();
        fd_button.top = new FormAttachment(0);
        fd_button.right = new FormAttachment(100, -60);
        button.setLayoutData(fd_button);
        button.setText("查看详情");
        button.setToolTipText("鼠标选中一行，点击查看详情。");
        getEmail();

    }

    /**
     * 写信 提交留言 INSERT INTO 表名称[(列1,列2,列3,…)]VALUES(值1,值2,值3,…);
     *
     * @throws BizException
     */
    public void setEmail(String message) throws BizException {
        if (message == null || message.trim().isEmpty()) {
            throw new BizException("请输入要提交内容 ! ");
        }
        DBHelper dbh = new DBHelper();

        String sql = "insert into pmail (sname,smessage,mtime) values (?,?,now())";
        int i = dbh.update(sql, name, message);
        if (i == 1) {
            throw new BizException("提交成功! ");
        } else {
            throw new BizException("提交失败 ");
        }

    }

    /**
     * private String sname; private String smessage; private String pPreply;
     * private Date mTime; private Date pTime;
     */
    public void getEmail() {

        try {
            String sql = "select smessage,ppreply,mtime,ptime from pmail where sname = ? order by id desc";
            DBHelper dbh = new DBHelper();
            List<PEmail> list = dbh.query(sql, PEmail.class, name);
            table.removeAll();
            for (PEmail pe : list) {
                TableItem tbItem = new TableItem(table, SWT.NONE);
                tbItem.setText(new String[]{"" + pe.getmtime(), "" + pe.getSmessage(), "" + pe.getptime(),
                        "" + pe.getpPreply()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看留言详情
     */
    private void LookMail() {
        // 获取当前选择的表格中的行
        TableItem[] items = table.getSelection();
        if (items == null || items.length == 0) {
            SwtHelper.message("请选择要查看的记录!", shell);
            return;
        }
        // 将 item 传入  EmpEidtDialog
        TableItem item = items[0];
        SEmailDialog eed = new SEmailDialog(shell, SWT.NONE);
        eed.setItem(item);
        if (eed.open() != null) {
            getEmail();
        }

    }
}
