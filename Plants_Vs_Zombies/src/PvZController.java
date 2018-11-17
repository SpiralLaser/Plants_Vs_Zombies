import java.awt.event.*;
import java.awt.*;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class PvZController implements ActionListener {

	private PvZModel model;
	private int x;
	private int y;

	public PvZController(PvZModel model, int x, int y) {
		this.model = model;
		this.x = x;
		this.y = y;

	}

	public void actionPerformed(ActionEvent e) {
		
		if (x != 10 && y != 10) {
			model.placePlantAt(x, y);
			}
		
		Object o = e.getSource();

		if(((JButton) o).getText().equals("S")) {
			model.isClicked("S");
		}
		else if(((JButton) o).getText().equals("P")) {
			model.isClicked("P");
		}
		else if(((JButton) o).getText().equals("End Turn")) {
			model.endTurn();
		}
	}
}
