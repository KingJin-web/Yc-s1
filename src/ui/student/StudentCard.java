package ui.student;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import ui.LoginWin;
import util.DBHelper;
import util.IOHelper;
import bean.Student;
import biz.StudentBiz;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import ui.Login;
import util.SwtHelper;


public class StudentCard {

    private static final String name = LoginWin.returnName();
    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Text text_3;
    private Text text_4;
    private Text text_5;
    private Text text_6;
    private Text text_7;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            StudentCard window = new StudentCard();
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
        shell.setImage(SWTResourceManager.getImage(StudentCard.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
        shell.setSize(675, 371);
        shell.setText("欢迎" + name + "同学");
        shell.setLayout(new BorderLayout(0, 0));

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayoutData(BorderLayout.WEST);
        composite.setLayout(new GridLayout(1, false));

        Label lblNewLabel = new Label(composite, SWT.NONE);
        GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_lblNewLabel.widthHint = 110;
        gd_lblNewLabel.heightHint = 164;
        lblNewLabel.setLayoutData(gd_lblNewLabel);
        lblNewLabel.setImage(SWTResourceManager.getImage("D:\\stuImg\\" + new StudentBiz().RetFile(name)));
        new Label(composite, SWT.NONE);

        Button button_1 = new Button(composite, SWT.NONE);
        button_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                //实现文件选择 并且上传数据库
                StudentBiz sb = new StudentBiz();
                String url = null, fileName = null;
                FileDialog fileselect = new FileDialog(shell);
                fileselect.setFilterPath("Pictures");// 设置默认的路径
                fileselect.setText("选择图片");// 设置对话框的标题
                fileselect.setFilterNames(new String[]{"文本文件 (*.jpg*)", "所有文件(*.*)"});// 设置扩展名
                fileselect.setFilterExtensions(new String[]{"*.jpg", "*.*"});// 设置文件扩展名
                url = fileselect.open();

                try {
                    out:if (url == null || url.isEmpty()) {
                        break out;
                    } else {
                        fileName = IOHelper.retFileName(url);
                        IOHelper.copyFile(url, "D:\\stuImg\\" + fileName);
                        sb.updateImg(url, name, fileName);
                        query(name);
                    }
                } catch (IOException Exception) {
                    SwtHelper.message(Exception.getMessage(), shell);
                } finally {
                    lblNewLabel.setImage(SWTResourceManager.getImage("D:\\stuImg\\" + new StudentBiz().RetFile(name)));
                }
                System.out.println("选择的图片路径：" + url);
            }
        });

        button_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
        button_1.setText("更换照片");
        lblNewLabel.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                // 代码如下 https://blog.csdn.net/qq_39047789/article/details/100503878

                // 获取到控件中的图片
                org.eclipse.swt.graphics.Image image = lblNewLabel.getImage();
                int h = lblNewLabel.getBounds().height; // 获取控件的高
                int w = lblNewLabel.getBounds().width; // 获取控件的宽度
                int height = image.getBounds().height; // 获取原图片的高度
                int width = image.getBounds().width; // 获取原图片的初始宽度
                // 绘制图片，将原图片按照控件的高度和宽度进行重绘
                e.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
            }
        });

        Composite composite_1 = new Composite(shell, SWT.NONE);
        composite_1.setLayoutData(BorderLayout.CENTER);
        composite_1.setLayout(new GridLayout(5, false));
        new Label(composite_1, SWT.NONE);

        Label label = new Label(composite_1, SWT.NONE);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label.setText("姓名");

        text = new Text(composite_1, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);

        Label label_1 = new Label(composite_1, SWT.NONE);
        label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_1.setText("学院");

        text_1 = new Text(composite_1, SWT.BORDER);
        text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label label_5 = new Label(composite_1, SWT.NONE);
        label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_5.setText("性别");

        text_5 = new Text(composite_1, SWT.BORDER);
        text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(composite_1, SWT.NONE);

        Label label_2 = new Label(composite_1, SWT.NONE);
        label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_2.setText("班级");

        text_2 = new Text(composite_1, SWT.BORDER);
        text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label label_6 = new Label(composite_1, SWT.NONE);
        label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_6.setText("年龄");

        text_6 = new Text(composite_1, SWT.BORDER);
        text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(composite_1, SWT.NONE);

        Label label_3 = new Label(composite_1, SWT.NONE);
        label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_3.setText("学号");

        text_3 = new Text(composite_1, SWT.BORDER);
        text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);

        Label label_4 = new Label(composite_1, SWT.NONE);
        label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_4.setText("余额");

        text_4 = new Text(composite_1, SWT.BORDER);
        text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Button button_2 = new Button(composite_1, SWT.NONE);
        button_2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                inMoney();
            }
        });
        button_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        button_2.setText("充值");
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);

        Label label_7 = new Label(composite_1, SWT.NONE);
        label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_7.setText("邮箱");

        text_7 = new Text(composite_1, SWT.BORDER);
        text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Button button = new Button(composite_1, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ChangeEmailDialog cd = new ChangeEmailDialog(shell, SWT.NONE);
                cd.setEmail(text_7.getText());
                cd.setName(name);
                cd.open();
                query(name);
            }
        });
        button.setText("修改邮箱");
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);
        new Label(composite_1, SWT.NONE);

        Button button_3 = new Button(composite_1, SWT.NONE);
        button_3.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                query(name);
            }
        });
        GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_button_3.widthHint = 59;
        button_3.setLayoutData(gd_button_3);
        button_3.setText("刷新");

        Composite composite_2 = new Composite(shell, SWT.NONE);
        composite_2.setLayoutData(BorderLayout.SOUTH);
        composite_2.setLayout(new GridLayout(6, false));

        Composite composite_3 = new Composite(composite_2, SWT.NONE);
        GridData gd_composite_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_3.widthHint = 217;
        composite_3.setLayoutData(gd_composite_3);

        Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
        btnNewButton_2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                StudentCard.this.shell.dispose();
                new CertificateWin().open();

            }
        });
        btnNewButton_2.setText("荣誉查询");
        new Label(composite_2, SWT.NONE);

        Button btnNewButton = new Button(composite_2, SWT.NONE);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                StudentCard.this.shell.dispose();
                new SMessageWin().open();
            }
        });
        btnNewButton.setText("我要吐槽");
        new Label(composite_2, SWT.NONE);

        Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                StudentCard.this.shell.dispose();

                new SEmailWin().open();
            }
        });
        btnNewButton_1.setText("校长信箱");
        query(name);

    }

    protected void inMoney() {
        inMoneyDialog inmg = new inMoneyDialog(shell, SWT.NONE);
        inmg.setName(name);
        inmg.open();
        query(name);
    }

    /**
     * [{sno=103, sname=陈栋, ssex=女, sage=19, sclass=1, smo=98.66,
     * sma=1846132633@qq.com, imgFile=陈栋.JPG, Cna=计信学院}]
     *
     * @param sname
     */
    public void query(String sname) {
        StudentBiz sBiz = new StudentBiz();
        List<Map<String, Object>> list = sBiz.selectByName(sname);
        for (Map<String, Object> map : list) {
            text.setText((String) map.get("sname"));
            text_1.setText((String) map.get("Cna"));
            text_2.setText((String) map.get("sclass"));
            text_3.setText(map.get("sno").toString());
            text_4.setText(map.get("smo").toString());
            text_5.setText((String) map.get("ssex"));
            text_6.setText(map.get("sage").toString());
            text_7.setText((String) map.get("sma"));
        }

    }

    public void changeImg() {
        String url = "", fileName = null;
        FileDialog fileselect = new FileDialog(shell);
        fileselect.setFilterPath("C:\\Users\\King\\Pictures");// 设置默认的路径
        fileselect.setText("选择图片");// 设置对话框的标题
        fileselect.setFilterNames(new String[]{"图片 (*.jpg*)", "所有文件(*.*)"});// 设置扩展名
        fileselect.setFilterExtensions(new String[]{"*.jpg", "*.*"});// 设置文件扩展名


        url = fileselect.open();
        try {
            fileName = IOHelper.retFileName(url);
            IOHelper.copyFile(url, System.getProperty("user.dir") + "\\img\\" + fileName);
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
        System.out.println("选择的图片路径：" + url);
    }

    public static String returnName() {
        return name;
    }

}
