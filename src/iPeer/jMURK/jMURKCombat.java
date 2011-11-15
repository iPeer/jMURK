package iPeer.jMURK;

import iPeer.jMURK.err.Item404;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings({ "unchecked", "serial", "static-access" })

public class jMURKCombat extends JDialog {

	private final JPanel d = new JPanel();
	
	public jMURKCombat() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				CombatHandler.playerRun();
				Debug.p("Player X'd out of Combat, treating as run away.");
			}
		});
		Utils.fixFont(new Font("Tahoma", Font.PLAIN, 11));
		setTitle("jMURK: FIGHT!");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		d.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(d, BorderLayout.CENTER);
		d.setLayout(null);
		
		Playername = new JLabel("PlayerName");
		Playername.setToolTipText("You");
		Playername.setHorizontalAlignment(SwingConstants.CENTER);
		Playername.setBounds(49, 11, 70, 14);
		d.add(Playername);
		
		Lvl = new JLabel("LVL: 9999");
		Lvl.setToolTipText("Your level");
		Lvl.setHorizontalAlignment(SwingConstants.CENTER);
		Lvl.setBounds(10, 36, 47, 14);
		d.add(Lvl);
		
		Ap = new JLabel("AP: 9999");
		Ap.setToolTipText("Your Armour points");
		Ap.setHorizontalAlignment(SwingConstants.CENTER);
		Ap.setBounds(61, 36, 46, 14);
		d.add(Ap);
		
		Cc = new JLabel("CC: 9999");
		Cc.setToolTipText("Your Critical Chance");
		Cc.setHorizontalAlignment(SwingConstants.CENTER);
		Cc.setBounds(117, 36, 46, 14);
		d.add(Cc);
		
		Hp = new JLabel("HP: 9999/9999");
		Hp.setToolTipText("Your HP (Remaining/Total)");
		Hp.setHorizontalAlignment(SwingConstants.CENTER);
		Hp.setBounds(49, 61, 72, 14);
		d.add(Hp);
		
		MonsterName = new JLabel("MonsterName");
		MonsterName.setToolTipText("Your opponent");
		MonsterName.setHorizontalAlignment(SwingConstants.CENTER);
		MonsterName.setBounds(318, 11, 70, 14);
		d.add(MonsterName);
		
		MonsterAP = new JLabel("AP: 9999");
		MonsterAP.setToolTipText("Opponent's Armour points");
		MonsterAP.setHorizontalAlignment(SwingConstants.CENTER);
		MonsterAP.setBounds(330, 36, 46, 14);
		d.add(MonsterAP);
		
		MonsterLVL = new JLabel("LVL: 9999");
		MonsterLVL.setToolTipText("Opponent's level");
		MonsterLVL.setHorizontalAlignment(SwingConstants.CENTER);
		MonsterLVL.setBounds(279, 36, 47, 14);
		d.add(MonsterLVL);
		
		MonsterCC = new JLabel("CC: 9999");
		MonsterCC.setToolTipText("Opponent's Critical Chance");
		MonsterCC.setHorizontalAlignment(SwingConstants.CENTER);
		MonsterCC.setBounds(386, 36, 46, 14);
		d.add(MonsterCC);
		
		MonsterHP = new JLabel("HP: 9999/9999");
		MonsterHP.setToolTipText("Opponent's HP (Remaining/Total)");
		MonsterHP.setHorizontalAlignment(SwingConstants.CENTER);
		MonsterHP.setBounds(318, 61, 72, 14);
		d.add(MonsterHP);
		
		JLabel lblVs = new JLabel("vs.");
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setBounds(195, 36, 46, 14);
		d.add(lblVs);
		
		Fight = new JButton("Attack!");
		Fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CombatHandler.playerAttack();
			}
		});
		Fight.setToolTipText("Attack your opponent");
		Fight.setBounds(54, 86, 89, 23);
		d.add(Fight);
		
		lm = new DefaultListModel();
		list = new JList(lm);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent m) {
				JList l = (JList)m.getSource();
				if (m.getClickCount() == 2 && CombatHandler.playerCHP < CombatHandler.playerHP) {
					String[] i = l.getSelectedValue().toString().split("\\(");
					System.out.println(i[0]+i[1]);
					int q = Integer.parseInt(i[1].substring(0, i[1].length() - 1));
					String n = i[0].substring(0, i[0].length() - 1);
					if (q > 0)
						try {
							PlayerHandler.useAid(n, 1);
							CombatHandler.playerCHP = Engine.getPlayerCHP();
							Hp.setText("HP: "+Integer.toString(CombatHandler.playerCHP)+"/"+Integer.toString(CombatHandler.playerHP));
							lm.clear();
							CombatHandler.listAidItems(lm, list);
						} catch (Item404 e) {
							e.printStackTrace();
						}
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(85, 36, 175, 116);
		d.add(list);
		
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(10, 139, 175, 116);
		d.add(scrollPane_1);
		
		lm2 = new DefaultListModel();
		list_1 = new JList(lm2);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setEnabled(false);
		list_1.setBounds(195, 139, 237, 116);
		d.add(list_1);
		
		JScrollPane scrollPane = new JScrollPane(list_1);
		scrollPane.setBounds(195, 139, 237, 116);
		d.add(scrollPane);
		
		JButton inventorybutton = new JButton("Inventory");
		inventorybutton.setToolTipText("Open your Inventory");
		inventorybutton.setEnabled(false);
		inventorybutton.setBounds(153, 86, 89, 23);
		d.add(inventorybutton);
		
		runbutton = new JButton("Run (1337 coins)");
		runbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CombatHandler.playerRun();
				dispose();
			}
		});
		runbutton.setToolTipText("Run from the battle");
		runbutton.setBounds(252, 86, 136, 23);
		d.add(runbutton);
		
		AutoAttack = new JCheckBox("Auto-Attack", Engine.useAutoAttack());
		AutoAttack.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int a = e.getStateChange();
				System.out.println(e.getStateChange());
				PlayerHandler.plyr.p.put("AutoAttack", Integer.toString(a));
			}

		});
		AutoAttack.setToolTipText("You will automatically attack your opponent 1 tick after they have made their attack.");
		AutoAttack.setBounds(57, 109, 83, 23);
		d.add(AutoAttack);
	}
	
	public JList list, list_1;
	public static JLabel Playername, Lvl, Ap, Cc, Hp, MonsterName, MonsterHP, MonsterCC, MonsterAP, MonsterLVL;
	public DefaultListModel lm, lm2;
	public JButton Fight, runbutton;
	public JCheckBox AutoAttack;
}
