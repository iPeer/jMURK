package iPeer.jMURK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class jMURKStartDialog implements ActionListener {

	public jMURKStartDialog() {
		System.out.println("InterfaceHandler: jMURKStartDialog");
		f = new JFrame("jMURK");
		l = new JLabel("Choose an option below to begin, or not if you pick the quit option...");
		b = new JButton("New Game");
		b2 = new JButton("Load Game");
		b3 = new JButton("Quit jMURK");
		System.out.println(ItemHandler.getItemTypeFromList("Test Weapon"));
		System.out.println(ItemHandler.getItemTypeFromDat("Test Shield", false));
		System.out.println(ItemHandler.getItemTypeFromDat("Test Armour", true));
	}
	
	public void create() {
		Dimension screen = Utils.resolution();
		f.setLayout(null);
		f.setSize(400,150);
		int bposy = (150-60)-3;
		b3.addActionListener(this);
		b2.addActionListener(this);
		b.addActionListener(this);
		f.add(b3);
		f.add(b);
		f.add(b2);
		f.add(l);
		Dimension s = b3.getPreferredSize();
		b3.setBounds(400 - (s.width+8), bposy, s.width, s.height);
		s = b.getPreferredSize();
		b.setBounds(2, bposy, s.width, s.height);
		b.setToolTipText("Start a new game.");
		b3.setToolTipText("Quit jMURK without starting or loading a game.");
		b2.setToolTipText("Load an existing jMURK save file.");
		s = b2.getPreferredSize();
		b2.setBounds((200+b2.getWidth()) / 2, bposy, s.width, s.height);
		s = l.getPreferredSize();
		l.setBounds((f.getWidth() - (s.width +5)) / 2, 20, s.width, s.height);
		f.setLocation((screen.width - f.getWidth()) / 2, (screen.height - f.getHeight()) / 2);
		f.setResizable(false);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {
			System.out.println("Closed!");
			System.exit(0);
		}
		});
		/*int dialogpos = f.getX();
		while ((screen.width - f.getWidth()) / 2 + dialogpos >= 0) {
			f.setLocation(dialogpos, (screen.height - f.getHeight()) / 2);
			dialogpos -=10;
		}*/
	}
	
	public void actionPerformed(ActionEvent e) { 
		ClickHandler.ClickJFrame("jMURKStartDialog", e, f);
	}
	
	private JFrame f;
	private JButton b, b2, b3;
	private JLabel l;
	
}
