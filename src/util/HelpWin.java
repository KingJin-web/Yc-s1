package util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

public class HelpWin {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HelpWin window = new HelpWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * Open the window.
	 * 
	 * @throws IOException
	 */
	public void open() throws IOException {
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
	 * 
	 * @throws IOException
	 */
	protected void createContents() throws IOException {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(HelpWin.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shell.setSize(561, 418);
		shell.setText("操作指南");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text.setText(insert("README.md"));
	}

	public static String insert(String filename) {
		FileReader fileReader;
		BufferedReader bufferedReader = null;
		String sb = new String("");
		try {
			
			fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;

			List<Object> param = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null && line.length() != 0) {
				sb += line + "\n";

			}

			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println(sb);
		return sb;
	}
}
