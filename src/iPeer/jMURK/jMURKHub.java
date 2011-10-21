package iPeer.jMURK;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;


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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Close = new JButton("Close");
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(contentPane, "This will only close the jMURK Hub. The game will still be running in the background. " +
						"\nTo properly quit jMURK, please use the Quit button.", "You're about to close the jMURK Hub", JOptionPane.OK_CANCEL_OPTION) == 0) {
					isOpen = false;
					setVisible(false);
				}
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
		
		JButton Quit = new JButton("Quit");
		Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showOptionDialog(contentPane, "Are you sure you want to quit jMURK?", "Confirm Exit", 0, 0, null, null, null) == 0 && Engine.isGameLoaded())
				PlayerHandler.unloadGame();
				dispose();
				System.exit(0);
			}
		});
		Quit.setToolTipText("Quits jMURK after saving.");
		Quit.setBounds(10, 195, 89, 23);
		contentPane.add(Quit);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInventory.setEnabled(false);
		btnInventory.setToolTipText("Open your inventory.");
		btnInventory.setBounds(10, 60, 89, 23);
		contentPane.add(btnInventory);
		
		JButton btnFightNow = new JButton("Fight Now");
		btnFightNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFightNow.setEnabled(false);
		btnFightNow.setToolTipText("Get right down to business, fighting with monsters.");
		btnFightNow.setBounds(10, 26, 89, 23);
		contentPane.add(btnFightNow);
		
		JLabel PlayerName = new JLabel("Player Name");
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
		
	}
	
	public static boolean isOpen = false;
	
}
