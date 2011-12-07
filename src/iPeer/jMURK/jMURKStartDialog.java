package iPeer.jMURK;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class jMURKStartDialog implements ActionListener {

	public jMURKStartDialog() {
		System.out.println("InterfaceHandler: jMURKStartDialog");
		Utils.fixFont(new Font("Tahoma", Font.PLAIN, 11));
		f = new JFrame("jMURK");
		l = new JLabel("Choose an option below to begin, or not if you pick the quit option...");
		b = new JButton("New Game");
		b2 = new JButton("Load Game");
		b3 = new JButton("Quit jMURK");
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
	}
	
	public void actionPerformed(ActionEvent e) { 
		ClickHandler.ClickJFrame("jMURKStartDialog", e, f);
	}
	
	public static void hide() {
		f.setVisible(false);
	}
	
	private static JFrame f;
	private JButton b, b2, b3;
	private JLabel l;
	public static jMURKHub m = new jMURKHub();
	
}
