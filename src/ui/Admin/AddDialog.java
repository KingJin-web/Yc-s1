package ui.Admin;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import bean.Student;
import biz.AdminBiz;

import util.IOHelper;
import util.ImageUtil;
import util.SwtHelper;
import util.SwtLabelPaintListner;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddDialog extends Dialog {
    private final AdminBiz aBiz = new AdminBiz();
    protected Object result;
    protected Shell shell;
    private Text textNo;
    private Text textName;
    private Text textClass;
    private Text textPwd;
    private Text textEmail;
    String[] college = new String[]{"", "计信学院", "经管学院", "材化学院", "数能学院", "电信学院", "建工学院", "外国语学院", "机械学院"};
    private String imgFile = "D:\\stuImg\\ad.jpg";
    private String imgName = "img-4a2b09624a367d3ba45c17241eb04457.jpg";
    private Label label;

    private int sno;
    private String sname;
    private String ssex;
    private int sage;
    private String sclass;
    private int cid;
    private float smo;
    private String spw;
    private String sma;
    private FileInputStream in;

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public AddDialog(Shell parent, int style) {
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
        shell.setSize(573, 572);
        shell.setText("注册");
        shell.setLayout(new FormLayout());

        Label lblNewLabel = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel = new FormData();
        fd_lblNewLabel.top = new FormAttachment(0, 80);
        fd_lblNewLabel.left = new FormAttachment(0, 93);
        lblNewLabel.setLayoutData(fd_lblNewLabel);
        lblNewLabel.setText("姓名：");

        Label lblNewLabel_1 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_1 = new FormData();
        fd_lblNewLabel_1.top = new FormAttachment(0, 130);
        fd_lblNewLabel_1.left = new FormAttachment(0, 93);
        lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
        lblNewLabel_1.setText("性别：");

        Label lblNewLabel_2 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_2 = new FormData();
        fd_lblNewLabel_2.top = new FormAttachment(0, 178);
        fd_lblNewLabel_2.left = new FormAttachment(0, 93);
        lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
        lblNewLabel_2.setText("年龄：");

        Label lblNewLabel_3 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_3 = new FormData();
        fd_lblNewLabel_3.top = new FormAttachment(0, 226);
        fd_lblNewLabel_3.left = new FormAttachment(0, 93);
        lblNewLabel_3.setLayoutData(fd_lblNewLabel_3);
        lblNewLabel_3.setText("班级：");

        Label lblNewLabel_4 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_4 = new FormData();
        fd_lblNewLabel_4.top = new FormAttachment(0, 272);
        fd_lblNewLabel_4.left = new FormAttachment(0, 93);
        lblNewLabel_4.setLayoutData(fd_lblNewLabel_4);
        lblNewLabel_4.setText("学院：");

        Label lblNewLabel_5 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_5 = new FormData();
        fd_lblNewLabel_5.top = new FormAttachment(0, 316);
        fd_lblNewLabel_5.left = new FormAttachment(0, 93);
        lblNewLabel_5.setLayoutData(fd_lblNewLabel_5);
        lblNewLabel_5.setText("余额：");

        Label lblNewLabel_7 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_7 = new FormData();
        fd_lblNewLabel_7.top = new FormAttachment(0, 32);
        fd_lblNewLabel_7.left = new FormAttachment(0, 93);
        lblNewLabel_7.setLayoutData(fd_lblNewLabel_7);
        lblNewLabel_7.setText("学号：");

        Label lblNewLabel_8 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_8 = new FormData();
        fd_lblNewLabel_8.top = new FormAttachment(0, 357);
        fd_lblNewLabel_8.left = new FormAttachment(0, 93);
        lblNewLabel_8.setLayoutData(fd_lblNewLabel_8);
        lblNewLabel_8.setText("密码：");

        Label lblNewLabel_9 = new Label(shell, SWT.NONE);
        FormData fd_lblNewLabel_9 = new FormData();
        fd_lblNewLabel_9.top = new FormAttachment(0, 398);
        fd_lblNewLabel_9.left = new FormAttachment(0, 93);
        lblNewLabel_9.setLayoutData(fd_lblNewLabel_9);
        lblNewLabel_9.setText("邮箱：");

        Button button = new Button(shell, SWT.NONE);
        FormData fd_button = new FormData();
        fd_button.bottom = new FormAttachment(lblNewLabel_4, 0, SWT.BOTTOM);
        button.setLayoutData(fd_button);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String url = "", fileName = null;
                FileDialog fileselect = new FileDialog(shell);
                fileselect.setFilterPath("H:\\Tencent\\QQ下载\\A4-计科1班\\A4-计科1班");// 设置默认的路径
                fileselect.setText("选择图片");// 设置对话框的标题
                fileselect.setFilterNames(new String[]{"文本文件 (*.jpg*)", "所有文件(*.*)"});// 设置扩展名
                fileselect.setFilterExtensions(new String[]{"*.jpg", "*.*"});// 设置文件扩展名
                url = fileselect.open();


                try {
                    fileName = IOHelper.retFileName(url);
                    label.setImage(SWTResourceManager.getImage(url));

                } catch (Exception ioException) {
                    ioException.printStackTrace();
                } finally {
                    imgFile = url;
                    imgName = fileName;
                }
                System.out.println(url);
            }

        });
        button.setText("照片选择");

        textNo = new Text(shell, SWT.BORDER);
        FormData fd_textNo = new FormData();
        fd_textNo.right = new FormAttachment(0, 377);
        fd_textNo.top = new FormAttachment(0, 29);
        fd_textNo.left = new FormAttachment(0, 144);
        textNo.setLayoutData(fd_textNo);

        textName = new Text(shell, SWT.BORDER);
        FormData fd_textName = new FormData();
        fd_textName.right = new FormAttachment(0, 377);
        fd_textName.top = new FormAttachment(0, 77);
        fd_textName.left = new FormAttachment(0, 144);
        textName.setLayoutData(fd_textName);

        textClass = new Text(shell, SWT.BORDER);
        FormData fd_textClass = new FormData();
        fd_textClass.right = new FormAttachment(0, 377);
        fd_textClass.top = new FormAttachment(0, 223);
        fd_textClass.left = new FormAttachment(0, 144);
        textClass.setLayoutData(fd_textClass);

        textPwd = new Text(shell, SWT.BORDER);
        FormData fd_textPwd = new FormData();
        fd_textPwd.right = new FormAttachment(0, 377);
        fd_textPwd.top = new FormAttachment(0, 354);
        fd_textPwd.left = new FormAttachment(0, 144);
        textPwd.setLayoutData(fd_textPwd);

        textEmail = new Text(shell, SWT.BORDER);
        FormData fd_textEmail = new FormData();
        fd_textEmail.right = new FormAttachment(0, 377);
        fd_textEmail.top = new FormAttachment(0, 395);
        fd_textEmail.left = new FormAttachment(0, 144);
        textEmail.setLayoutData(fd_textEmail);

        Button radio1 = new Button(shell, SWT.RADIO);
        FormData fd_radio1 = new FormData();
        fd_radio1.top = new FormAttachment(0, 130);
        fd_radio1.left = new FormAttachment(0, 155);
        radio1.setLayoutData(fd_radio1);
        radio1.setText("男");
        //radio1.setSelection(true);

        Button radio2 = new Button(shell, SWT.RADIO);
        FormData fd_radio2 = new FormData();
        fd_radio2.top = new FormAttachment(0, 130);
        fd_radio2.left = new FormAttachment(0, 236);
        radio2.setLayoutData(fd_radio2);
        radio2.setText("女");

        Spinner spinnerAge = new Spinner(shell, SWT.BORDER);
        FormData fd_spinnerAge = new FormData();
        fd_spinnerAge.right = new FormAttachment(0, 274);
        fd_spinnerAge.top = new FormAttachment(0, 172);
        fd_spinnerAge.left = new FormAttachment(0, 144);
        spinnerAge.setLayoutData(fd_spinnerAge);

        Combo combo = new Combo(shell, SWT.NONE);
        fd_button.left = new FormAttachment(combo, 52);
        FormData fd_combo = new FormData();
        fd_combo.right = new FormAttachment(0, 377);
        fd_combo.top = new FormAttachment(0, 269);
        fd_combo.left = new FormAttachment(0, 144);
        combo.setLayoutData(fd_combo);
        combo.setItems(college);

        Spinner spinnerMo = new Spinner(shell, SWT.BORDER);
        FormData fd_spinnerMo = new FormData();
        fd_spinnerMo.right = new FormAttachment(0, 278);
        fd_spinnerMo.top = new FormAttachment(0, 313);
        fd_spinnerMo.left = new FormAttachment(0, 144);
        spinnerMo.setLayoutData(fd_spinnerMo);

        Button btnNewButton = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.right = new FormAttachment(0, 204);
        fd_btnNewButton.top = new FormAttachment(0, 485);
        fd_btnNewButton.left = new FormAttachment(0, 114);
        btnNewButton.setLayoutData(fd_btnNewButton);
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String coo = subString(String.valueOf(combo), "{", "}");
                String age = spinnerAge.getText();
                String mo = spinnerMo.getText();
//                 , sclass,
//                        spw,sma, imgName,in
                if (textNo.getText() == null || textNo.getText().trim().isEmpty()) {
                    SwtHelper.message("请输入学号", shell);
                } else {
                    sno = Integer.parseInt(textNo.getText().trim());
                }

                if (textName.getText() == null || textName.getText().trim().isEmpty()) {
                    SwtHelper.message("请输入姓名", shell);
                } else {
                    sname = textName.getText().trim();
                }

                if (radio1.getSelection()) {
                    ssex = radio1.getText();
                }
                if (radio2.getSelection()) {
                    ssex = radio2.getText();
                }

                if (age == null || age.trim().isEmpty()) {
                    SwtHelper.message("请输入年龄", shell);
                } else {
                    sage = Integer.parseInt(age);
                }

                if (coo == null || coo.trim().isEmpty()) {
                    SwtHelper.message("请选择学院", shell);
                } else {
                    cid = findString(coo);
                }

                if (textClass.getText() == null || textClass.getText().trim().isEmpty()){
                    SwtHelper.message("请输入班级",shell);
                }else {
                   sclass =textClass.getText().trim();
                }

                if (textPwd.getText() == null || textPwd.getText().trim().isEmpty()){
                    SwtHelper.message("请输入班级",shell);
                }else {
                    spw =textPwd.getText().trim();
                }

                if (textEmail.getText() == null || textEmail.getText().trim().isEmpty()){
                    SwtHelper.message("请输入邮箱",shell);
                }else {
                    sma =textEmail.getText().trim();
                }
                if (mo == null || mo.trim().isEmpty()) {
                    SwtHelper.message("请输入余额!",shell);
                } else {
                    smo = Float.parseFloat(mo);
                }
                try {
                    IOHelper.copyFile(imgFile, "D:\\stuImg\\" + imgName);
                    in = ImageUtil.readImage(imgFile);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                save(sno, sname, ssex, sage, sclass, cid, smo, sma, spw, imgName, in);
                MessageBox msBox = new MessageBox(shell);
                msBox.setText("系统提示");
                msBox.setMessage("注册成功!");
                msBox.open();
                shell.dispose();
            }
        });
        btnNewButton.setText("确定");

        Button btnNewButton_1 = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.right = new FormAttachment(0, 457);
        fd_btnNewButton_1.top = new FormAttachment(0, 485);
        fd_btnNewButton_1.left = new FormAttachment(0, 361);
        btnNewButton_1.setLayoutData(fd_btnNewButton_1);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        btnNewButton_1.setText("取消");

        label = new Label(shell, SWT.NONE);
        label.setImage(SWTResourceManager.getImage("C:\\Users\\82427\\Desktop\\背景\\img-4a2b09624a367d3ba45c17241eb04457.jpg"));
        FormData fd_label = new FormData();
        fd_label.top = new FormAttachment(lblNewLabel_7, 0, SWT.TOP);
        fd_label.bottom = new FormAttachment(0, 209);
        fd_label.right = new FormAttachment(0, 523);
        fd_label.left = new FormAttachment(0, 404);

        label.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                // 代码如下 https://blog.csdn.net/qq_39047789/article/details/100503878

                // 获取到控件中的图片
                org.eclipse.swt.graphics.Image image = label.getImage();
                int h = label.getBounds().height; // 获取控件的高
                int w = label.getBounds().width; // 获取控件的宽度
                int height = image.getBounds().height; // 获取原图片的高度
                int width = image.getBounds().width; // 获取原图片的初始宽度
                // 绘制图片，将原图片按照控件的高度和宽度进行重绘
                e.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
            }
        });
        label.setLayoutData(fd_label);

        Label label_1 = new Label(shell, SWT.NONE);
        label_1.setImage(SWTResourceManager.getImage(AddDialog.class, "/imges/img-a3b05d93f12ce1e1b33dfe83ffd6481b.jpg"));
        label_1.setLayoutData(new FormData());
        label_1.addPaintListener(new SwtLabelPaintListner());
    }

    private void save(int sno, String sname, String ssex, int sage, String sclass, int cid, float smo, String sma, String spw, String imgName, FileInputStream in) {
        aBiz.insert(sno, sname, ssex, sage, sclass, cid, smo,
                spw, sma, imgName, in);
    }

    protected void save(int a, String ssex, int sage, float smo, String imgName) {
        aBiz.insert(Integer.parseInt(textNo.getText()), textName.getText(), ssex, sage, textClass.getText(), a, smo,
                textPwd.getText(), textEmail.getText(), imgName);

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
        return str.substring(strStartIndex, strEndIndex).substring(strStart.length());
    }

    public int findString(String key) {
        int i;
        for (i = 0; i < college.length; i++) {
            if (college[i].equals(key)) {
                return i;
            }
        }
        return i;

    }
}
