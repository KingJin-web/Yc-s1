package ui.teacher;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import bean.Certificate;
import util.DBHelper;
import util.SwtLabelPaintListner;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class LookCerDialog extends Dialog {

    private int choose = 0;
    private String zname = null;
    private String sname = null;
    protected Object result;
    protected Shell shell;
    private Text textName;
    protected TableItem item;
    private Text text;
    private Button button_1;
    private Button button;
    private Button button_3;
    private Label label_3;

    public TableItem getItem() {
        return item;
    }

    public void setItem(TableItem item) {
        this.item = item;
    }

    Label zsimg;

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public LookCerDialog(Shell parent, int style, String zname) {
        super(parent, style);
        setText("SWT Dialog");
        this.zname = zname;
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
        shell.setSize(450, 593);
        shell.setText("证书详情");
        shell.setLayout(new FormLayout());

        zsimg = new Label(shell, SWT.NONE);
        zsimg.setImage(SWTResourceManager.getImage(LookCerDialog.class, "/javax/swing/plaf/basic/icons/image-delayed.png"));
        FormData fd_zsimg = new FormData();
        fd_zsimg.top = new FormAttachment(0, 22);
        fd_zsimg.right = new FormAttachment(100, -30);
        fd_zsimg.left = new FormAttachment(0, 31);
        zsimg.setLayoutData(fd_zsimg);
        zsimg.addPaintListener(new SwtLabelPaintListner());

        Label label = new Label(shell, SWT.NONE);
        FormData fd_label = new FormData();
        label.setLayoutData(fd_label);
        label.setText("获得者：");

        textName = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
        fd_label.bottom = new FormAttachment(textName, 0, SWT.BOTTOM);
        fd_zsimg.bottom = new FormAttachment(100, -279);
        FormData fd_textName = new FormData();
        fd_textName.left = new FormAttachment(0, 219);
        fd_textName.right = new FormAttachment(100, -68);
        fd_textName.top = new FormAttachment(zsimg, 10);
        textName.setLayoutData(fd_textName);

        Label label_1 = new Label(shell, SWT.NONE);
        fd_label.left = new FormAttachment(label_1, 0, SWT.LEFT);
        FormData fd_label_1 = new FormData();
        fd_label_1.top = new FormAttachment(label, 42);
        label_1.setLayoutData(fd_label_1);
        label_1.setText("申请时间：");

        Label label_2 = new Label(shell, SWT.NONE);
        label_2.setText("审批状态：");
        FormData fd_label_2 = new FormData();
        fd_label_2.top = new FormAttachment(label_1, 46);
        label_2.setLayoutData(fd_label_2);

        // 当数据库状态为0时，未审核，之后不能点击
        button = new Button(shell, SWT.RADIO);
        FormData fd_button = new FormData();
        fd_button.left = new FormAttachment(textName, 0, SWT.LEFT);
        button.setLayoutData(fd_button);
        button.setText("暂未审核");
        button.setEnabled(false);

        button_1 = new Button(shell, SWT.RADIO);
        fd_button.top = new FormAttachment(button_1, 23);
        button_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                choose = 1;
            }
        });
        fd_label_2.right = new FormAttachment(button_1, -41);
        FormData fd_button_1 = new FormData();
        fd_button_1.top = new FormAttachment(textName, 108);
        fd_button_1.right = new FormAttachment(100, -171);
        button_1.setLayoutData(fd_button_1);
        button_1.setText("批准");

        Button button_2 = new Button(shell, SWT.NONE);
        button_2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChanegZt();
            }
        });
        button_2.setText("确认");
        FormData fd_button_2 = new FormData();
        fd_button_2.bottom = new FormAttachment(100, -10);
        fd_button_2.left = new FormAttachment(label, 0, SWT.LEFT);
        fd_button_2.top = new FormAttachment(100, -38);
        button_2.setLayoutData(fd_button_2);

        Button button_2_1 = new Button(shell, SWT.NONE);
        button_2_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        fd_button_2.right = new FormAttachment(100, -250);
        button_2_1.setText("返回");
        FormData fd_button_2_1 = new FormData();
        fd_button_2_1.top = new FormAttachment(button_2, -1, SWT.TOP);
        fd_button_2_1.left = new FormAttachment(button_2, 64);
        fd_button_2_1.right = new FormAttachment(100, -95);
        button_2_1.setLayoutData(fd_button_2_1);
        fd_label_1.right = new FormAttachment(100, -266);

        text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
        FormData fd_text = new FormData();
        fd_text.right = new FormAttachment(textName, 0, SWT.RIGHT);
        fd_text.top = new FormAttachment(textName, 36);
        fd_text.left = new FormAttachment(label_1, 41);
        text.setLayoutData(fd_text);

        button_3 = new Button(shell, SWT.RADIO);
        button_3.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                choose = 2;
            }
        });
        FormData fd_button_3 = new FormData();
        fd_button_3.bottom = new FormAttachment(label_2, 0, SWT.BOTTOM);
        fd_button_3.right = new FormAttachment(zsimg, 0, SWT.RIGHT);
        button_3.setLayoutData(fd_button_3);
        button_3.setText("不允通过");

//        label_3 = new Label(shell, SWT.NONE);
//        label_3.setImage(SWTResourceManager.getImage(LookCerDialog.class, "/imges/rio.jpg"));
//        label_3.setLayoutData(new FormData());
//        label_3.addPaintListener(new SwtLabelPaintListner());

        getCerSname();
    }

    protected void ChanegZt() {
        // TODO Auto-generated method stub
        try {
            String sql = "update Certificate set zt = ?,sptime = now() where zname = ? and sname = ?";
            DBHelper dbh = new DBHelper();
            System.out.println("传入的名字" + sname);
            dbh.update(sql, choose, zname, sname);
            MessageBox mb = new MessageBox(shell);
            mb.setMessage("审核完毕");
            mb.setText("系统提示");
            mb.open();
            shell.dispose();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void getCerSname() {
        try {
            String sql = "select * from certificate where zname = ?";
            DBHelper dbh = new DBHelper();
            List<Certificate> list = dbh.query(sql, Certificate.class, zname);

            for (Certificate cer : list) {
                textName.setText(cer.getSname());
                text.setText(String.valueOf(cer.getTime()));
                if (cer.getZt() == 1) {
                    button_1.setSelection(true);
                } else if (cer.getZt() == 0) {
                    button.setSelection(true);
                } else if (cer.getZt() == 2) {
                    button_3.setSelection(true);
                }
                zsimg.setImage(SWTResourceManager.getImage("D:\\stuImg\\" + cer.getZsimg()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        sname = textName.getText();
    }
}
