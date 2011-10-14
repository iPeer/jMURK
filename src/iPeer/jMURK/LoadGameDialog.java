package iPeer.jMURK;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoadGameDialog implements ActionListener {

	public LoadGameDialog(JFrame parent) {
		System.out.println("InterfaceHandler: LoadGameDialog");
		f = new JDialog(parent, "Load Game");
		b = new JButton("Load Game");
		b2 = new JButton("Nevermind");
		File f = new File("saves/");
		File[] fl = f.listFiles();
		l = new JList(fl);
	}
	
	public void create() {
		Dimension screen = Utils.resolution();
		f.setLayout(null);
		f.setSize(250,300);
		b.addActionListener(this);
		f.add(b);
		Dimension s = b.getPreferredSize();
		b.setBounds(10, 20, s.width, s.height);
		f.setResizable(false);
		f.setLocation((screen.width - f.getWidth()) /2, (screen.height - f.getHeight()) / 2);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { 
		
	}
	
	private JDialog f;
	private JButton b, b2;
	private JList l;
	private JScrollBar s;
}
