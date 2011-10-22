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

@SuppressWarnings("serial")
public class jMURKLoadGame extends JDialog {

	public jMURKLoadGame(JFrame f) {
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
		Utils.getFilesAndFolders(saves, new File("saves\\"));
		/*JTree */tree = new JTree(saves);
		tree.setBounds(10, 11, 165, 300);
		getContentPane().add(tree);
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] pa = tree.getSelectionPath().getPath();
				if (tree.getSelectionPath().getPathCount() < 4) {
					JOptionPane.showMessageDialog(getContentPane(), "The File you selected is not a valid save file.", "Errorous save file selected", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String fileString = pa[1]+"/"+pa[2]+"/"+pa[3];
				File f = new File(fileString);
				PlayerHandler pl = new PlayerHandler();
				setVisible(false);
				jMURKStartDialog.hide();
				InterfaceHandler.jMURKHub();
				pl.load(f);
			}
		});
		btnLoadGame.setToolTipText("Load the selected save file.");
		btnLoadGame.setBounds(185, 8, 89, 23);
		getContentPane().add(btnLoadGame);
		
		JButton btnDeleteGame = new JButton("Delete");
		btnDeleteGame.setEnabled(false);
		btnDeleteGame.setToolTipText("Delete the selected save file.");
		btnDeleteGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
