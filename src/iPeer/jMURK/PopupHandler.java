package iPeer.jMURK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;

import javax.swing.*;

public class PopupHandler implements ActionListener {

	public PopupHandler() { }

	public void displaySavingNotification() {
		JWindow p = new JWindow();
		p.setBounds(100, 100, 134, 36);

		JLabel lblAutosaving = new JLabel("Autosaving...");
		lblAutosaving.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(lblAutosaving, BorderLayout.CENTER);
		showJWindow(p);
	}

	private void showJWindow(JWindow j) {
		Dimension r = Utils.resolution();
		int sw = r.width;
		int sh = r.height;
		j.setLocation((0 - j.getWidth()), 0);

		int dialogpos = j.getX();
		while ((sw - j.getWidth()) / 2 + dialogpos >= 0) {	
			j.setLocation(dialogpos, (sh - j.getHeight()) / 2);
			dialogpos -=10;
		}
		//t.schedule(arg0, arg1);

	}

	Timer t = new Timer();

	public void actionPerformed(ActionEvent arg0) {	}
	
}
