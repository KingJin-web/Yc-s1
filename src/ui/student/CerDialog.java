package ui.student;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import Util.io.IOHelper;
import Util.sql.DBHelper;
import biz.BizException;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;


public class CerDialog extends Dialog {

	protected Object result;
	private String name = CertificateWin.returnName();
	protected Shell shell;
	private Text text;
	private String imgFile;
	private String imgName;
	Label label_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public CerDialog(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.BORDER | SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.RESIZE);
		shell.setSize(450, 300);
		shell.setText("证书申请");
		shell.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 423;
		composite.setLayoutData(gd_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("申请内容");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new GridLayout(5, false));
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.heightHint = 120;
		gd_composite_1.widthHint = 422;
		composite_1.setLayoutData(gd_composite_1);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		label_1 = new Label(composite_1, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage("D:\\stuImg\\默认图片\\2Q__.jpg"));
		GridData gd_label_1 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2);
		gd_label_1.heightHint = 111;
		gd_label_1.widthHint = 233;
		label_1.setLayoutData(gd_label_1);
		label_1.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// 代码如下 https://blog.csdn.net/qq_39047789/article/details/100503878

				// 获取到控件中的图片
				org.eclipse.swt.graphics.Image image = label_1.getImage();
				int h = label_1.getBounds().height; // 获取控件的高
				int w = label_1.getBounds().width; // 获取控件的宽度
				int height = image.getBounds().height; // 获取原图片的高度
				int width = image.getBounds().width; // 获取原图片的初始宽度
				// 绘制图片，将原图片按照控件的高度和宽度进行重绘
				e.gc.drawImage(image, 0, 0, width, height, 0, 0, w, h);
			}
		});

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("证明材料提交");
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		Composite composite_2 = new Composite(shell, SWT.NONE);
		composite_2.setLayout(new FormLayout());
		GridData gd_composite_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite_2.heightHint = 78;
		gd_composite_2.widthHint = 422;
		composite_2.setLayoutData(gd_composite_2);

		Button button = new Button(composite_2, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeImg();
			}
		});
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0);
		fd_button.right = new FormAttachment(100, -75);
		button.setLayoutData(fd_button);
		button.setText("选择材料");

		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 提交申请
				try {
					intoCer();
				} catch (BizException | IOException e1) {
					MessageBox msBox = new MessageBox(shell);
					msBox.setText("系统提示");
					msBox.setMessage(e1.getMessage());
					msBox.open();
				}
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.right = new FormAttachment(0, 131);
		fd_btnNewButton.bottom = new FormAttachment(100, -10);
		fd_btnNewButton.left = new FormAttachment(0, 41);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("提交");

		Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		FormData fd_button_1 = new FormData();
		fd_button_1.bottom = new FormAttachment(100, -10);
		fd_button_1.right = new FormAttachment(100, -57);
		fd_button_1.left = new FormAttachment(100, -147);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("返回");

	}

	/**
	 * INSERT INTO 表名 （字段名1，字段名2，...） VALUES(值1，值2，...);
	 * @throws BizException 
	 * 
	 * @throws IOException
	 */
	protected void intoCer() throws BizException, IOException {

		if (imgFile.equals(null)) {
			throw new BizException("请选择证书 ! ");
		}
		
		if (text.getText() == null || text.getText().trim().isEmpty() ) {
			throw new BizException("请输入证书名称 ! ");
		}
		String sql = "insert into certificate (zname,sname,time,zsimg) values(?,?,now(),?)";
		DBHelper dbh = new DBHelper();

		IOHelper.copyFile(imgFile, "D:\\stuImg\\" + imgName);
		dbh.update(sql, text.getText(), name, imgName);
		MessageBox msBox = new MessageBox(shell);
		msBox.setText("系统提示");
		msBox.setMessage("提交成功");
		msBox.open();
		shell.dispose();

	}

	public void changeImg() {
		try {
			String url = "";
			FileDialog fileselect = new FileDialog(shell);
			fileselect.setFilterPath("F:\\java\\eclipse-workspace\\Yc-s1\\src\\img");// 设置默认的路径
			fileselect.setText("选择图片");// 设置对话框的标题
			fileselect.setFilterNames(new String[] { "文本文件 (*.jpg*)", "所有文件(*.*)" });// 设置扩展名
			fileselect.setFilterExtensions(new String[] { "*.jpg", "*.*" });// 设置文件扩展名
			url = fileselect.open();

			imgName = IOHelper.retFileName(url);
			imgFile = url;

			label_1.setImage(SWTResourceManager.getImage(imgFile));

		} catch (Exception ioException) {
			ioException.printStackTrace();
		} finally {
			System.out.println(imgFile + imgName);
		}

	}

}
