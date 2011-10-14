package iPeer.jMURK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class jMURKStartDialog implements ActionListener {

	public jMURKStartDialog() {
		System.out.println("InterfaceHandler: jMURKStartDialog");
		f = new JFrame("jMURK");
		b = new JButton("New Game");
		b2 = new JButton("Load Game");
		b3 = new JButton("Quit jMURK");
	}
	
	public void create() {
		f.setLayout(null);
		f.setSize(400,150);
		int bposy = (150-60)-3;
		b3.addActionListener(this);
		b2.addActionListener(this);
		b.addActionListener(this);
		f.add(b3);
		f.add(b);
		f.add(b2);
		Dimension s = b3.getPreferredSize();
			// Divide by 10 to center... because the proper way doesn't work...
		b3.setBounds(400 - (s.width+8), bposy, s.width, s.height);
		s = b.getPreferredSize();
		b.setBounds(2, bposy, s.width, s.height);
		s = b2.getPreferredSize();
		b2.setBounds((200+b2.getWidth()) / 2, bposy, s.width, s.height);
		f.setLocation(-1, -1);
		f.setResizable(false);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
			System.out.println("Closed!");
			System.exit(0);
		}
		});
	}
	
	public void actionPerformed(ActionEvent e) { 
		ClickHandler.ClickJFrame("jMURKStartDialog", e, f);
	}
	
	private JFrame f;
	private JButton b, b2, b3;
	
}
