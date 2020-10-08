package ui.teacher;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import ui.LoginWin;

import org.eclipse.swt.widgets.Composite;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import bean.Student;
import biz.TeacherBiz;
import dao.StuDao;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import util.DBHelper;

public class MainWin {

    public static String sname;
    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Table table;
    private Text text_4;
    String[] college = new String[]{"", "计信学院", "经管学院", "材化学院", "数能学院", "电信学院", "建工学院", "外国语学院", "机械学院"};

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            MainWin window = new MainWin();
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
        shell.setImage(SWTResourceManager.getImage(MainWin.class, "/imges/laoshi.jpg"));
        shell.setSize(879, 492);
        shell.setText("教师界面");
        shell.setLayout(new BorderLayout(0, 0));

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayoutData(BorderLayout.NORTH);
        composite.setLayout(new GridLayout(7, false));

        Label lblNewLabel = new Label(composite, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel.setText("姓名:");

        text = new Text(composite, SWT.BORDER);
        GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_text.widthHint = 130;
        text.setLayoutData(gd_text);

        Label label = new Label(composite, SWT.NONE);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label.setText("学号:");

        text_1 = new Text(composite, SWT.BORDER);
        GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_text_1.widthHint = 130;
        text_1.setLayoutData(gd_text_1);

        Button btnNewButton_3 = new Button(composite, SWT.NONE);
        btnNewButton_3.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                LookCer();
            }
        });
        GridData gd_btnNewButton_3 = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
        gd_btnNewButton_3.widthHint = 66;
        btnNewButton_3.setLayoutData(gd_btnNewButton_3);
        btnNewButton_3.setText("证书审批");
        btnNewButton_3.setToolTipText("鼠标选中学生姓名，点击进行查看。");

        Label lblNewLabel_1 = new Label(composite, SWT.NONE);
        lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel_1.setText("班级:");

        text_2 = new Text(composite, SWT.BORDER);
        GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_text_2.widthHint = 130;
        text_2.setLayoutData(gd_text_2);

        Label label_1 = new Label(composite, SWT.NONE);
        label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_1.setText("学院:");

        Combo combo = new Combo(composite, SWT.NONE);
        GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
        gd_combo.widthHint = 120;
        combo.setLayoutData(gd_combo);
        combo.setItems(college);
        System.out.println(combo);

        Composite composite_1 = new Composite(shell, SWT.NONE);
        composite_1.setLayoutData(BorderLayout.CENTER);
        composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

        table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tableColumn = new TableColumn(table, SWT.NONE);
        tableColumn.setWidth(100);
        tableColumn.setText("学号");

        TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
        tableColumn_1.setWidth(100);
        tableColumn_1.setText("姓名");

        TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
        tableColumn_2.setWidth(100);
        tableColumn_2.setText("性别");

        TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
        tableColumn_3.setWidth(100);
        tableColumn_3.setText("年龄");

        TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
        tableColumn_4.setWidth(100);
        tableColumn_4.setText("班级");

        TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
        tableColumn_5.setWidth(100);
        tableColumn_5.setText("学院");

        TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
        tableColumn_6.setWidth(100);
        tableColumn_6.setText("余额");

        TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
        tableColumn_7.setWidth(100);
        tableColumn_7.setText("邮箱");

        Button btnNewButton_1 = new Button(composite, SWT.NONE);
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println(subString(String.valueOf(combo), "{", "}"));
                String coo = subString(String.valueOf(combo), "{", "}");

                int index = 0;
                if (coo == null || coo.trim().isEmpty()) {
                    index = 0;
                } else {
                    index = findString(coo);
                }

                System.out.println(index);
                query(text.getText(), text_1.getText(), text_2.getText(), index);

            }
        });
        GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_btnNewButton_1.widthHint = 66;
        btnNewButton_1.setLayoutData(gd_btnNewButton_1);
        btnNewButton_1.setText("查询");
        new Label(composite, SWT.NONE);

        Button btnNewButton_4 = new Button(composite, SWT.NONE);
        btnNewButton_4.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                MainWin.this.shell.dispose();
                new LoginWin().open();
            }
        });
        GridData gd_btnNewButton_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_btnNewButton_4.widthHint = 66;
        btnNewButton_4.setLayoutData(gd_btnNewButton_4);
        btnNewButton_4.setText("返回");

        Composite composite_3 = new Composite(shell, SWT.NONE);
        composite_3.setLayoutData(BorderLayout.SOUTH);
        composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

        text_4 = new Text(composite_3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        StuDao sDao = new StuDao();
        text_4.setText(sDao.showMessage());

        getStuInfo();

    }

    protected void LookCer() {

        TableItem[] items = table.getSelection();
        if (items == null || items.length == 0) {
            // 创建消息框
            MessageBox mb = new MessageBox(shell);
            mb.setMessage("请选择要查看的记录!");
            mb.setText("系统提示");
            mb.open();
            return;
        }
        // 将 item 传入  EmpEidtDialog
        TableItem item = items[0];
        System.out.println(item.getText(1));
        sname = item.getText(1);
        CerDialog cd = new CerDialog(shell, SWT.NONE, sname);
        //cd.open();
        cd.setItem(item);
        if (cd.open() != null) {
            getname();
        }

    }

    public String getname() {
        // TODO Auto-generated method stub
        return sname;
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     */
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

    protected void query(String sname, String stuno, String sclass, int stucid) {
        try {

            int sno = 0;
            if (stuno == null || stuno.trim().isEmpty()) {
                sno = 0;
            } else {
                sno = Integer.parseInt(stuno);
            }

            Student stu = new Student();
            stu.setSname(sname);
            stu.setSno(sno);
            stu.setSclass(sclass);
            stu.setCid(stucid);

            table.removeAll();
            TeacherBiz tb = new TeacherBiz();
            List<Student> list = tb.selectByStu(stu);

            for (Student stu1 : list) {
                TableItem tbItem = new TableItem(table, SWT.NONE);
                tbItem.setText(new String[]{"" + stu1.getSno(), "" + stu1.getSname(), "" + stu1.getSsex(),
                        "" + stu1.getSage(), "" + stu1.getSclass(), "" + college[stu1.getCid()], "" + stu1.getSmo(),
                        "" + stu1.getSma()});

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getStuInfo() {

        try {
            String sql = "select * from student where 1 = 1 and state <> 2";
            DBHelper dbh = new DBHelper();
            List<Student> list = dbh.query(sql, Student.class);
            table.removeAll();

            for (Student stu : list) {
                TableItem tbItem = new TableItem(table, SWT.NONE);
                tbItem.setText(new String[]{"" + stu.getSno(), "" + stu.getSname(), "" + stu.getSsex(),
                        "" + stu.getSage(), "" + stu.getSclass(), "" + college[stu.getCid()], "" + stu.getSmo(),
                        "" + stu.getSma()});

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int findString(String key) {

        for (int i = 0; i < college.length; i++) {
            if (college[i].equals(key)) {
                return i;
            }
        }
        return 0;

    }


}
