package Util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwtHelper {

	/**
	 * 将指定窗口居中显示
	 * @param shell
	 */
	public static void center(Shell shell) {
		shell.setLocation((Display.getCurrent().getClientArea().width - shell.getSize().x) / 2,
				(Display.getCurrent().getClientArea().height - shell.getSize().y) / 2);
	}

	/**
	 * 返回多选框选组或单选组中的值数组
	 * @param
	 * @return
	 */
	public static String[] getCheckedButton(Button... btns) {
		ArrayList<String> list = new ArrayList<String>();
		for (Button b : btns) {
			if (Boolean.TRUE.equals(b.getSelection())) {
				list.add(b.getText());
			}
		}
		return list.toArray(new String[0]);
	}

	/**
	 * 根据输入值(values),设置多选框选组或单选组选中状态
	 * @param
	 * @return
	 */
	public static void setCheckedButton(Button[] btns, String... values) {
		for (Button b : btns) {
			boolean isSelected = false;
			for (String value : values) {
				if (b.getText().equals(value)) {
					b.setSelection(true);
					isSelected = true;
					break;
				}
			}
			if (isSelected == false) {
				b.setSelection(false);
			}
		}
	}

	/**
	 * 使用数据库查询出的结果集集合, 构建下列列表的选项列表
	 * @param combo			下列列表对象
	 * @param dataList		结果集集合
	 * @param valueColumn	值列列名
	 * @param displayColumn 显示值列名
	 */
	public static void buildComboItems(Combo combo, List<Map<String, Object>> dataList, String valueColumn,
			String displayColumn) {
		List<String> items = new ArrayList<String>();
		for (Map<String, Object> row : dataList) {
			Object value = row.get(valueColumn);
			String display = row.get(displayColumn) + "";
			// 转换成字符串的简单方法
			items.add(display);
			combo.setData(display, value);
		}
		// 将集合转换成数组
		String[] itemArray = items.toArray(new String[items.size()]);
		combo.setItems(itemArray);

	}

	/**
	 * 显示提示信息
	 * @param title 标题
	 * @param msg 内容
	 */
	public static void mssage(String title, String msg, Shell shell) {
		MessageBox mb = new MessageBox(shell);
		mb.setText(title);
		mb.setMessage(msg);
		mb.open();
	}

	/**
	 * 显示提问信息
	 * @param title 标题
	 * @param msg 内容
	 */
	public static boolean confirm(String title, String msg, Shell shell) {
		MessageBox mb = new MessageBox(shell, SWT.YES | SWT.NO);
		mb.setText(title);
		mb.setMessage(msg);
		return mb.open() == SWT.YES;
	}

	public static boolean confirm(String msg, Shell shell) {
		return confirm("系统提示", msg, shell);
	}

	public static void mssage(String msg, Shell shell) {
		mssage("系统提示", msg, shell);
	}

	/**
	 * 根据传入的 value 让 combo 控件选中指定选项
	 * onlyEqualsValue == false 表示, 如果 value == null 那么就用显示值进行判断 ,
	 * 因为很多时间 combo 没有指定真实值, 那么显示值就是真实值
	 * @param combo
	 * @param value
	 * @param onlyEqualsValue 设置值匹配真实值
	 */
	public static void selectItem(Combo combo, Object value, boolean onlyEqualsValue) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			String item = combo.getItems()[i];
			Object itemValue = combo.getData(item);
			// 如果没有真实值,则使用显示值进行比较
			if (itemValue == null && onlyEqualsValue == false) {
				itemValue = item;
			}
			// 如果类型相同比较内容
			if (value.getClass().equals(itemValue.getClass())) {
				if (value.equals(itemValue)) {
					combo.select(i);
					return;
				}
			} else {
				// 类型不同,转换成字符串再比较
				if (value.equals("" + itemValue)) {
					combo.select(i);
					return;
				}
			}

		}
	}

	public static Date getDate(DateTime dateTime) {
		return Date.valueOf(dateTime.getYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay());
	}

}
