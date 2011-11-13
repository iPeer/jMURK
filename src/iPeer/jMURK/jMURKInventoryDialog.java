package iPeer.jMURK;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class jMURKInventoryDialog extends JDialog {

	public JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			jMURKInventoryDialog dialog = new jMURKInventoryDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public jMURKInventoryDialog() {
		setTitle("jMURK: Player Inventory");
		setResizable(false);
		setBounds(100, 100, 650, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.setToolTipText("Close the Inventory screen");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				{
					JButton btnRefresh = new JButton("Refresh Inventory");
					btnRefresh.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// TODO
						}
					});
					buttonPane.add(btnRefresh);
				}
				closeButton.setActionCommand("");
				buttonPane.add(closeButton);
			}
		}
	}

}
