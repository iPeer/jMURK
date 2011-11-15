package iPeer.jMURK;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class jMURKHub extends JFrame {

	private JPanel contentPane;

	public jMURKHub() {
		Utils.fixFont(new Font("Tahoma", Font.PLAIN, 11));
		isOpen = true;
		setResizable(false);
		setTitle("jMURK Hub");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 284);
		setLocation((Utils.resolution().width - getWidth()) / 2, (Utils.resolution().height - getHeight()) / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Close = new JButton("Close");
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showOptionDialog(contentPane, "Are you sure you want to quit jMURK?", "Confirm Exit", JOptionPane.YES_NO_OPTION, 0, null, null, null) == JOptionPane.YES_OPTION && Engine.isGameLoaded())
				PlayerHandler.unloadGame();
				dispose();
				System.exit(0);
			}
		});
		Close.setToolTipText("Close the jMURK Hub dialog");
		Close.setBounds(0, 229, 402, 23);
		contentPane.add(Close);
		
		JLabel UpdateStatus = new JLabel("No updates available");
		UpdateStatus.setBounds(301, 0, 101, 14);
		contentPane.add(UpdateStatus);
		
		JButton Options = new JButton("Options");
		Options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Options.setToolTipText("Confirgure jMURK.");
		Options.setBounds(10, 127, 89, 23);
		contentPane.add(Options);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Debug.p("Trying to open Inventory");
					jMURKInventoryDialog i = new jMURKInventoryDialog();
					i.setVisible(true);
				}
				catch (Exception e) {
					ErrorHandler.e(1, "Unable to open player Inventory");
					Debug.p(e);
				}
			}
		});
		btnInventory.setToolTipText("Open your inventory.");
		btnInventory.setBounds(10, 60, 89, 23);
		contentPane.add(btnInventory);
		
		JButton btnFightNow = new JButton("Fight Now");
		btnFightNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CombatHandler.combatInit(1);
				} catch (Exception e) {
					ErrorHandler.e(1, "Unable to initiate combat!");
					e.printStackTrace();
				} 
			}
		});
		btnFightNow.setToolTipText("Get right down to business, fighting with monsters.");
		btnFightNow.setBounds(10, 26, 89, 23);
		contentPane.add(btnFightNow);
		
		JLabel PlayerName = new JLabel(Engine.getPlayerName());
		PlayerName.setFont(new Font("Tahoma", Font.BOLD, 11));
		PlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerName.setBounds(202, 25, 172, 14);
		contentPane.add(PlayerName);
		
		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.setEnabled(Engine.isGameLoaded());
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Engine.isGameLoaded()) { PlayerHandler.save(0); }
			}
		});
		btnSaveGame.setBounds(10, 161, 89, 23);
		contentPane.add(btnSaveGame);
		
		JLabel lblStatsPlaceholder = new JLabel("Stats placeholder");
		lblStatsPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatsPlaceholder.setBounds(224, 91, 133, 87);
		contentPane.add(lblStatsPlaceholder);
		
		GameTime = new JLabel();
		GameTime.setHorizontalAlignment(SwingConstants.CENTER);
		GameTime.setBounds(10, 97, 89, 14);
		contentPane.add(GameTime);
		
	}
	
	public static void updatejMURKHub(String t) {
		if (isOpen) { GameTime.setText(t); }
	}
	
	public static boolean isOpen = false;
	private static JLabel GameTime;
}
