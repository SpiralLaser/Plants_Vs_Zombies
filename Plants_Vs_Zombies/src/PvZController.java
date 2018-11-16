import java.awt.event.*;
import java.awt.*;

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
		model.play()
	}
}
