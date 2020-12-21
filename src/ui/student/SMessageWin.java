package ui.student;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import bean.Message;
import biz.BizException;
import util.SwtHelper;
import util.DBHelper;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

/**
 * 学生留言板
 *
 * @author King
 */
public class SMessageWin {

    private static final String name = StudentCard.returnName();
    protected Shell shell;
    private Text text;
    private Text text_1;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SMessageWin window = new SMessageWin();
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
        shell.setImage(SWTResourceManager.getImage(SMessageWin.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
        shell.setSize(668, 495);
        shell.setText("学生留言");
        shell.setLayout(new GridLayout(1, false));

        Label label = new Label(shell, SWT.NONE);
        label.setText("写留言");

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));
        GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 3);
        gd_composite.heightHint = 190;
        gd_composite.widthHint = 643;
        composite.setLayoutData(gd_composite);

        text = new Text(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
        GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        gd_text.heightHint = 156;
        text.setLayoutData(gd_text);

        Composite composite_2 = new Composite(composite, SWT.NONE);
        GridData gd_composite_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_2.heightHint = 113;
        gd_composite_2.widthHint = 103;
        composite_2.setLayoutData(gd_composite_2);
        composite_2.setLayout(new GridLayout(1, false));

        Button btnNewButton = new Button(composite_2, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    setMessage();
                } catch (BizException e1) {
                    SwtHelper.message(e1.getMessage(), shell);
                } finally {
                    text_1.setText(showMessage());
                }
            }
        });
        btnNewButton.setText("提交吐槽");

        Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
        btnNewButton_2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    setMessage2();
                } catch (BizException e1) {
                    SwtHelper.message(e1.getMessage(), shell);
                } finally {
                    text_1.setText(showMessage());

                }
            }
        });
        btnNewButton_2.setText("匿名吐槽");

        Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SMessageWin.this.shell.dispose();
                new StudentCard().open();
            }
        });
        GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_btnNewButton_1.widthHint = 72;
        btnNewButton_1.setLayoutData(gd_btnNewButton_1);
        btnNewButton_1.setText("返回");

        Label label_1 = new Label(shell, SWT.NONE);
        label_1.setText("留言板");

        Composite composite_1 = new Composite(shell, SWT.NONE);
        composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
        GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_1.widthHint = 643;
        gd_composite_1.heightHint = 201;
        composite_1.setLayoutData(gd_composite_1);

        text_1 = new Text(composite_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
        text_1.setText(showMessage());

    }

    /**
     * 显示所有留言
     *
     * @return
     */
    public String showMessage() {
        DBHelper dbh = new DBHelper();
        String sql = "select sname,mdate,message from messageboard order by Mdate desc ";
        List<Message> list = dbh.query(sql, Message.class);
        String ms = "";
        for (Message mss : list) {
            ms = ms + String.valueOf(mss);
        }

        System.out.println(ms);
        return ms;
    }

    /**
     * 提交留言
     * INSERT INTO 表名称[(列1,列2,列3,…)]VALUES(值1,值2,值3,…);
     *
     * @throws BizException
     */
    public void setMessage() throws BizException {
        DBHelper dbh = new DBHelper();
        String sql = "insert into messageboard(sname,message) values(?,?)";
        int i = dbh.update(sql, name, text.getText());
        if (i == 1) {
            throw new BizException("提交成功! ");
        } else {
            throw new BizException("提交失败 ");
        }

    }

    /**
     * 提交留言 匿名
     * INSERT INTO 表名称[(列1,列2,列3,…)]VALUES(值1,值2,值3,…); 匿名
     *
     * @throws BizException
     */
    public void setMessage2() throws BizException {
        DBHelper dbh = new DBHelper();
        String sql = "insert into messageboard (sname,message) values(?,?)";
        int i = dbh.update(sql, "匿名", text.getText());
        if (i == 1) {
            throw new BizException("提交成功! ");
        } else {
            throw new BizException("提交失败 ");
        }

    }
}
