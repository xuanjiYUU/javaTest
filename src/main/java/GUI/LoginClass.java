package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * Title: 登录类 
 * Description: 登陆模块，程序的入口类
 * 
 * @author 谢孟辉
 */

public class LoginClass {
	boolean packFrame = false;

	public LoginClass() {
		LoginFrame frame = new LoginFrame();
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}
		// 调整窗体大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		;
		frame.setVisible(true);
	}
}
