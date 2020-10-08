package ui.teacher;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Table;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import bean.Certificate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import util.DBHelper;

public class CerDialog extends Dialog {
	
	private String sname = null;
	
	public static String zname;
	protected Object result;
	protected Shell shell;
	private Table table;
	protected TableItem item;
	public TableItem getItem() { return item; }
	public void setItem(TableItem item) { this.item = item; }

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CerDialog(Shell parent, int style,String sname) {
		super(parent, style);
		setText("证书详情");
		this.sname=sname;
	}

	/**
	 * Open the dialog.
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.RESIZE);
		shell.setSize(486, 342);
		shell.setText("证书审批");
		shell.setLayout(new BorderLayout(0, 0));
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(BorderLayout.CENTER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("证书名字");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("获得者");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("申请时间");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(164);
		tableColumn_3.setText("审批时间");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FormLayout());
		
		Button button = new Button(composite, SWT.NONE);
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0, 5);
		button.setLayoutData(fd_button);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LookZs();
			}
		});
		button.setText("查看详情");
		
		Button button_1 = new Button(composite, SWT.NONE);
		fd_button.right = new FormAttachment(100, -119);
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(button, 0, SWT.TOP);
		fd_button_1.left = new FormAttachment(button, 17);
		fd_button_1.right = new FormAttachment(100, -22);
		button_1.setLayoutData(fd_button_1);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button_1.setText("返回");
		
		getStuCer();
		
	}
	
	//查看证书的了解详情
	protected void LookZs() {
		// TODO Auto-generated method stub
		TableItem[] items = table.getSelection();
		if(items == null || items.length==0) {
			// 创建消息框
			MessageBox mb = new MessageBox(shell);
			mb.setMessage("请选择要查看的记录!");
			mb.setText("系统提示");
			mb.open();
			return;
		}
		// 将 item 传入  EmpEidtDialog
		TableItem item = items[0];
		System.out.println(item.getText(0));
		zname = item.getText(0);
		System.out.println(zname);
		LookCerDialog ld = new LookCerDialog(shell, SWT.NONE,zname);
		//cd.open();
		ld.setItem(item);
		if(ld.open() != null) {
			getzsname();
		}
		getStuCer();
	}
	
	public String getzsname() {
		return zname;
	}
	public void getStuCer() {
		try {
			String sql = "select * from certificate where sname like ?";
			DBHelper dbh = new DBHelper();
			List<Certificate> list = dbh.query(sql, Certificate.class,sname);
			table.removeAll();
			
			for(Certificate cer : list) {
				TableItem tbItem = new TableItem(table, SWT.NONE);
				
				tbItem.setText(new String[] {
						""+cer.getZname(),
						""+cer.getSname(),
						""+cer.getTime(),
						""+cer.getSptime()
				});
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void setName(String sname) {
		// TODO Auto-generated method stub
		
	}

}
