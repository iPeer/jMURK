package iPeer.jMURK;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class jMURKLoadGame extends JDialog {

	public jMURKLoadGame(final JFrame f) {
		super(f);

		Utils.fixFont(new Font("Tahoma", Font.PLAIN, 11));

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("jMURK: Load Game");
		setBounds(100, 100, 288, 356);
		setLocation((Utils.resolution().width - getWidth()) / 2, (Utils.resolution().height - getHeight()) / 2);
		getContentPane().setLayout(null);

		DefaultMutableTreeNode saves;
		saves = new DefaultMutableTreeNode("Saves", true);
		Utils.listSaves(saves, new File("saves\\"));
		tree = new JTree(saves);
		tree.setBounds(10, 11, 165, 300);
		getContentPane().add(tree);

		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] pa = tree.getSelectionPath().getPath();
				int f2 = tree.getSelectionPath().getPathCount();
				File f;
				if (f2 < 3) {
					if (f2 < 2) {
						JOptionPane.showMessageDialog(getContentPane(), "The file you selected is not a valid save file.", "Errorous save file selected", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						f = Engine.getMostRecentSave(pa[1].toString());
					}
				}
				else { 
					f = new File(pa[0]+"/"+pa[1]+"/"+pa[2]);
				}
				PlayerHandler pl = new PlayerHandler();
				setVisible(false);
				jMURKStartDialog.hide();
				pl.load(f);
				InterfaceHandler.jMURKHub();
				jMURKHub.updatejMURKHub(Engine.getTimeOfDayFromTicks(GameTick.tickTime)+", "+Engine.getTicksAsGameTime(GameTick.tickTime));
			}
		});
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(10, 11, 165, 300);
		getContentPane().add(scrollPane);
		btnLoadGame.setToolTipText("Load the selected save file.");
		btnLoadGame.setBounds(185, 8, 89, 23);
		getContentPane().add(btnLoadGame);

		JButton btnDeleteGame = new JButton("Delete");
		btnDeleteGame.setToolTipText("Delete the selected save file.");
		btnDeleteGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tree.getSelectionPath() == null || tree.getSelectionPath().getPathCount() < 2) {
					JOptionPane.showMessageDialog(getContentPane(), "Please select a valid save file");
					return;
				}
				if (JOptionPane.showOptionDialog(f, "Are you sure you want to delete this file?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) {
					Object[] p = tree.getSelectionPath().getPath();
					String s = p[0]+"/"+p[1];
					File[] f1 = {new File(s, "hash"), new File(s, "save.msf"), new File(s, "autosave.msf"), new File(s)}; 
					for (int d = 0;d<f1.length;d++) {
						if (f1[d].exists())
							if (!f1[d].delete()) { Debug.p("Unable to delete file."); EH.e(2, "Unable to delete file: "+f1[d]); return; }
					}
					new jMURKLoadGame(f).setVisible(true);
					dispose();	
				}
			}
		});
		btnDeleteGame.setBounds(185, 42, 89, 23);
		getContentPane().add(btnDeleteGame);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setToolTipText("Cancel loading a game.");
		btnCancel.setBounds(185, 76, 89, 23);
		getContentPane().add(btnCancel);

	}

	final JTree tree;
}
