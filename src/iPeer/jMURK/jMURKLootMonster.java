package iPeer.jMURK;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class jMURKLootMonster extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public jMURKLootMonster() {
		setTitle("Looting !@@MONSTER@@!");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lma = new DefaultListModel();
		
		JList list = new JList(lma);
		list.setBounds(10, 11, 154, 207);
		contentPanel.add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 11, 154, 207);
		contentPanel.add(scrollPane);
		
		lmb = new DefaultListModel();
		
		JList list_1 = new JList(lmb);
		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				JList l = (JList)m.getSource();
				if (m.getClickCount() == 2 && l.getSelectedValue() != null) {
					String i = l.getSelectedValue().toString();
					int q = Integer.parseInt(i.replaceAll("\\)","").split("\\(")[1]), q1 = 0;
					String i2 = i.split("\\(")[0];
					String i3 = i2.substring(0, i2.length() - 1);
					if (q > 4) {
						q1 = Integer.parseInt(JOptionPane.showInputDialog("How many would you like to take?"));
						//q = q1 > q ? q : q1;
					}
					else
						q1 = 1;
					PlayerHandler.addItem(i3, q1);
					if (q1 == q) {
						lmb.remove(l.getSelectedIndex());
					}
					else {
						String t = l.getSelectedValue().toString();
						int s = l.getSelectedIndex();
						lmb.remove(s);
						lmb.add(s, t.replaceAll(Integer.toString(q), Integer.toString(q - q1)));
					}
					lma.clear();
					PlayerHandler.listInventory(lma);
				}
			}
		});
		list_1.setBounds(270, 11, 154, 207);
		contentPanel.add(list_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		scrollPane_1.setBounds(270, 11, 154, 207);
		contentPanel.add(scrollPane_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton done = new JButton("Done");
				done.setToolTipText("Close this dialog");
				done.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you're done looting? Once this dialog closes you cannot return here!", "Done Looting?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
							dispose();
					}
				});
				done.setActionCommand("");
				buttonPane.add(done);
			}
		}
	}
	
	DefaultListModel lma, lmb;
	
}
