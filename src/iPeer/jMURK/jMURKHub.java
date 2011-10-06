package iPeer.jMURK;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class jMURKHub implements ActionListener {
	
	public jMURKHub() { 
		
		// Window title and button crap goes here...
		
		f = new JFrame("MURK Hub");
		b = new JButton("Test! :)");
		b2 = new JButton("Close");
	}
	
	public void create() {
		f.setLayout(null);
		b.addActionListener(this);
		f.add(b);
		Insets i = f.getInsets();
		Dimension s = b.getPreferredSize();
		b.setBounds(20+i.left,s.width+i.top,s.width,s.height);
		b2.addActionListener(this);
		f.add(b2);
		s = b2.getPreferredSize();
		b2.setBounds(i.left,273,400,s.height);
		
		
		
		// Everything's done being handled 'n stuff, make the window visible.
		f.setSize(408,333);
		f.setVisible(true);
		System.out.println("Started!");
		f.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
			System.out.println("Closed!");
			System.exit(0);
		}
		});
	}
	
	public void close() {
		ExitHandler.closeJFrame(f);
	}
	
	public void actionPerformed(ActionEvent a) {
		System.out.println(a);
		ClickHandler.ClickJFrame("jMURKHub", a, f);
	}
	public JFrame f;
	public JButton b, b2;
}