package iPeer.jMURK;

import java.awt.EventQueue;

import javax.swing.JWindow;

@SuppressWarnings("serial")
public class AutoSave extends JWindow {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoSave frame = new AutoSave();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AutoSave() {
		setBounds(100, 100, 450, 300);

	}

}
