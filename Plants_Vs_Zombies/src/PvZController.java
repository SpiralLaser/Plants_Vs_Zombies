import java.awt.event.*;
import javax.swing.JButton;

/**
 * The controller component in the MVC model. Tells model what to do when a button is clicked.
 * @author Leo Paz
 *
 */

public class PvZController implements ActionListener {

	private PvZModel model;
	int x;
	int y;

	public PvZController(PvZModel model, int x, int y) {
		this.model = model;
		this.x = x;
		this.y = y;
	}

	/**
	 * What the button should do when it is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if (x != 10 && y != 10) {
			model.placePlantAt(x, y);

		}	else {
			Object o = e.getSource();

			if(((JButton) o).getText().equals("End Turn")) {
				model.endTurn();
			}
			else if(((JButton) o).getName().equals("Undo")) {
				model.popUndo();
			}
			else if(((JButton) o).getName().equals("Redo")) {
				model.popRedo();
			}		
			else if(((JButton) o).getName().equals("Save")) {
				model.saveFeature();
			}
			else if(((JButton) o).getName().equals("Load")) {
				model.loadFeature();
			}
			else if(((JButton) o).getName().equals("S")) {
				model.isClicked("S");
			}
			else if(((JButton) o).getName().equals("P")) {
				model.isClicked("P");
			}
			else if(((JButton) o).getName().equals("R")) {
				model.isClicked("R");
			}
			else if(((JButton) o).getName().equals("T")) {
				model.isClicked("T");
			}
			else if(((JButton) o).getName().equals("W")) {
				model.isClicked("W");
			}
		}
	}
}


