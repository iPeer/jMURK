package iPeer.jMURK;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class jMURKInventoryDialog extends JDialog {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public jMURKInventoryDialog() {
		Utils.fixFont(new Font("Tahoma", Font.PLAIN, 11));
		setTitle("jMURK: Player Inventory");
		setResizable(false);
		setBounds(100, 100, 554, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{

			JPanel panel = new JPanel();
			panel.setBounds(185, 388, 363, 23);
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton closeButton = new JButton("Close");
				panel.add(closeButton);
				closeButton.setToolTipText("Close the Inventory screen");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				closeButton.setActionCommand("");
			}
			JButton btnRefresh = new JButton("Refresh Inventory");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO: Fix NPE.
					PlayerHandler.listInventory((new jMURKInventoryDialog()), Weapons.isSelected(), Armour.isSelected(), Aid.isSelected(), Pendant.isSelected(), Misc.isSelected());
				}
			});
			panel.add(btnRefresh);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(0, 388, 182, 23);
				contentPanel.add(panel_1);
				{
					progressBar = new JProgressBar();
					progressBar.setToolTipText("Percentage of inventory loaded.");
					panel_1.add(progressBar);
				}
			}

			lm = new DefaultListModel();
			list = new JList(lm);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(list);

			JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setBounds(10, 33, 197, 320);
			contentPanel.add(scrollPane);

			JButton btnArmoury = new JButton("Open Armoury");
			btnArmoury.setBounds(435, 33, 103, 23);
			contentPanel.add(btnArmoury);

			JButton btnTrade = new JButton("Buy/Sell Items");
			btnTrade.setBounds(435, 67, 103, 23);
			contentPanel.add(btnTrade);

			JLabel lblShow = new JLabel("Show:");
			lblShow.setBounds(217, 33, 46, 14);
			contentPanel.add(lblShow);

			Weapons = new JCheckBox("Weapons");
			Weapons.setSelected(true);
			Weapons.setBounds(227, 54, 97, 23);
			contentPanel.add(Weapons);

			Armour = new JCheckBox("Armour");
			Armour.setSelected(true);
			Armour.setBounds(227, 80, 97, 23);
			contentPanel.add(Armour);

			Aid = new JCheckBox("Aid");
			Aid.setSelected(true);
			Aid.setBounds(227, 106, 97, 23);
			contentPanel.add(Aid);

			JCheckBox chckbxPendant = new JCheckBox("Pendants");
			chckbxPendant.setSelected(true);
			chckbxPendant.setBounds(227, 132, 97, 23);
			contentPanel.add(chckbxPendant);

			Misc = new JCheckBox("Misc");
			Misc.setSelected(true);
			Misc.setBounds(227, 158, 97, 23);
			contentPanel.add(Misc);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(227, 188, 311, 14);
			contentPanel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));

			JLabel lblItemInfo = new JLabel("Item Information");
			lblItemInfo.setHorizontalAlignment(SwingConstants.CENTER);
			panel_1.add(lblItemInfo, BorderLayout.NORTH);

			table = new JTable();
			table.setRowSelectionAllowed(false);
			table.setBounds(227, 214, 311, 139);
			contentPanel.add(table);
			PlayerHandler.listInventory(this, true, true, true, true, true);
		}
	}

	@SuppressWarnings("rawtypes")
	public static DefaultListModel lm;
	public JPanel contentPanel = new JPanel();
	public JTable table;
	@SuppressWarnings("rawtypes")
	public JList list;
	public JProgressBar progressBar;
	private JCheckBox Weapons, Armour, Aid, Pendant, Misc;
}
