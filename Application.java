import controller.BankController;
import view.GUI;

public class Application {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// initialize controller (loads persisted bank if present)
					BankController.init();
					GUI.login.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
