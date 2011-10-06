package iPeer.jMURK;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class jMURKTestDialog implements ActionListener {
	
	public jMURKTestDialog(JFrame parent) {
		f = new JDialog(parent, "TEST DIALOG!!11!");
		b = new JButton("Close Me");
		b2 = new JButton("WriteINI");
		b3 = new JButton("ReadINI");
		l = new JLabel("Looks like you found my seekrit little test dialog, aren't you sneaky!");
	}
	
	public void create() {
		f.setLayout(null);
		b.addActionListener(this);
		f.add(b);
		Insets i = f.getInsets();
		Dimension s = b.getPreferredSize();
		// i.left,273,400,s.height);
		b.setBounds(i.left,90,378,s.height);
		b2.addActionListener(this);
		f.add(b2);
		s = b2.getPreferredSize();
		b2.setBounds(i.left,63,378,s.height);
		b3.addActionListener(this);
		f.add(b3);
		s = b3.getPreferredSize();
		b3.setBounds(i.left,36,378,s.height);
		f.add(l);
		s = l.getPreferredSize();
		l.setBounds(0+i.left,20,380+i.right,s.height);
		f.setSize(385,150);
		f.setVisible(true);
		System.out.println("SEEKRIT DIALOG FOUND, RUN FER DA 'ILLS!!1!");
	}
	
	public void actionPerformed(ActionEvent a) {
		ClickHandler.ClickJDialog("jMURKTestDialog", a, f);
	}

	public JDialog f;
	public JButton b, b2, b3;
	public JLabel l;
	
	
}
