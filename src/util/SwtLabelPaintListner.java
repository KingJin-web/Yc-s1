package util;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;

public class SwtLabelPaintListner implements PaintListener {

	@Override
	public void paintControl(PaintEvent e) {

		Label label = (Label) e.getSource();
		Image image = label.getImage();
		Rectangle bounds = image.getBounds();
		int picwidth = bounds.width; // 图片宽
		int picheight = bounds.height; // 图片高
		double H = label.getSize().y; // label的高
		double W = label.getSize().x; // label的宽
		double r1 = H / picheight;
		double r2 = W / picwidth;
		double ratio = Math.min(r1, r2);// 使用小缩放比率

		// 重新计算大小
		int newwidth = (int) (picwidth * ratio);
		int newheight = (int) (picheight * ratio);
		// 图片居中
		int newx = (label.getSize().x - newwidth) / 2;
		int newy = (label.getSize().y - newheight) / 2;

		e.gc.fillRectangle(0, 0, (int) W, (int) H);
		e.gc.drawImage(image, 0, 0, picwidth, picheight, newx, newy, newwidth, newheight);
	}

}
