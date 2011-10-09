package iPeer.jMURK;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

class GuiButton extends JComponent implements MouseListener {


	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
	
    public void mouseClicked(MouseEvent m) { }
    public void mouseEntered(MouseEvent m) { }
    public void mouseExited(MouseEvent m) { }
    public void mousePressed(MouseEvent m) { }
    public void mouseReleased(MouseEvent m) { }
    
    public Dimension getPreferredsSize() {
    	return new Dimension(getWidth(), getHeight());
    }
    
    public Dimension getMinimumSize() {
    	return getPreferredSize();
    }
    public Dimension getMaximumSize() {
    	return getPreferredSize();
    }
    
    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
    public boolean me = false, mp = false;
}