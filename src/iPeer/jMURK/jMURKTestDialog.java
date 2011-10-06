package iPeer.jMURK;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class jMURKTestDialog implements ActionListener {
	
	public jMURKTestDialog(JFrame parent) {
		f = new JDialog(parent, "TEST DIALOG!!11!");
		b = new JButton("Close Me");
		l = new JLabel("Looks like you found my seekrit little test dialog, aren't you sneaky!");
	}
	
	public void create() {
		f.setLayout(null);
		b.addActionListener(this);
		f.add(b);
		Insets i = f.getInsets();
		Dimension s = b.getPreferredSize();
		// i.left,273,400,s.height);
		b.setBounds(i.left,30,378,s.height);;
		f.add(l);
		s = l.getPreferredSize();
		l.setBounds(0+i.left,10,380+i.right,s.height);
		f.setSize(385,90);
		f.setVisible(true);
		System.out.println("SEEKRIT DIALOG FOUND, RUN FER DA 'ILLS!!1!");
	}
	
	public void actionPerformed(ActionEvent a) {
		ClickHandler.ClickJDialog("jMURKTestDialog", a, f);
	}

	public JDialog f;
	public JButton b;
	public JLabel l;
	
	
}
