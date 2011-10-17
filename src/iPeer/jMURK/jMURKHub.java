package iPeer.jMURK;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class jMURKHub implements ActionListener {

	public jMURKHub() { 

		System.out.println("InterfaceHandler: jMURKHub");

		// Window title and button crap goes here...

		f = new JFrame("jMURK Hub");
		b = new JButton("Options");
		close = new JButton("Close");
	}

	public void create() {
		f.setLayout(null);
		b.addActionListener(this);
		f.add(b);
		Insets i = f.getInsets();
		Dimension s = b.getPreferredSize();
		//b.setBounds(20+i.left,s.width+i.top,s.width,s.height);
		b.setBounds(87, 239, s.width, 20);
		/*b.setBackground(Color.black);
		b.setBorderPainted(false);
		b.setForeground(Color.white);*/
		close.addActionListener(this);
		f.add(close);
		s = close.getPreferredSize();
		close.setBounds((f.getWidth() - close.getWidth()) /2,273,400,s.height);



		// Everything's done being handled 'n stuff, make the window visible.
		f.setSize(408,333);
		f.setVisible(true);
		System.out.println("Started!");
		f.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
				if (Engine.isGameLoaded()) { PlayerHandler.unloadGame(); }
				System.out.println("Closed!");
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent a) {
		ClickHandler.ClickJFrame("jMURKHub", a, f);
	}
	public JFrame f;
	public JButton close, b, b2;
}