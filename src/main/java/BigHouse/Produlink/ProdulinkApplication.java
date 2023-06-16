package BigHouse.Produlink;

import BigHouse.Produlink.LibraryGUIProdulink.FormLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class ProdulinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdulinkApplication.class, args);
		/*if (!GraphicsEnvironment.isHeadless()) {
			startFormLogin();
		}*/
	}
/*
	private static void startFormLogin() {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new FormLogin().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
*/
}
