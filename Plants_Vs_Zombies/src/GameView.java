import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
/**
 * The View component in the MVC model. Creates the GUI fully and handles events when a button is clicked.
 * @author Tri Nhan
 *
 */

public class GameView implements PvZListener{

	private JLabel pText; // Label for different plants

	private JButton[][] buttons; // List of game space buttons

	private JMenuItem quitItem; // Quit option
	private JMenuItem newItem; // Reset/create a new game

	private JLabel sPoints; // sunlight point label
	private JLabel urLabel;

	private JTextField sunlight;  //The actual value display

	private JButton undoB, redoB;
	private JButton save, load;
	private JButton nextTurnButton; //Button to enter next game turn
	private JButton peaPlant, sunPlant, wallPlant, twinPlant, repPlant;
	
	int numZombies, zombieHealth;
	String input;
	JFrame tFrame;

	PvZModel model;
	Timer timer; //Timer to simulate real time

	/**
	 * Constructor for the Game view. displays the 5x8 board with animated plants and 
	 * zombies
	 */
	
	public GameView(){
		numZombies = 0;
		zombieHealth = 0;
		input = "";
		
		//Input Dialog box to get the user's choice of how many zombies to spawn
		while (input == null || !isInteger(input) || numZombies <= 0 ) {

			input= JOptionPane.showInputDialog("Put in how many zombies you want spawned");
			//if user clicks cancel, input is null
			if (input == null) {
				System.exit(1);
			}
			//if user actually types in an integer, save it, otherwise prompt user again
			if (isInteger(input)) {
				numZombies = Integer.parseInt(input);
			}
		}

		input = "";
		while (input == null || !isInteger(input) || zombieHealth <= 0  ) {
			input= JOptionPane.showInputDialog("Put in how much health the zombies should have");
			if (input == null) {
				System.exit(1);
			}
			if (isInteger(input)) {
				zombieHealth = Integer.parseInt(input);
			}
			
		}
		model = new PvZModel(numZombies, zombieHealth);
		model.addPvZListener(this);

		buttons = new JButton[5][8];   

		tFrame = new JFrame("Plants Vs. Zombies");
		tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //quit program when closing

		Container contentPane = tFrame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Box layout to lay 
		//out everything vertically

		JMenuBar menuBar = new JMenuBar(); 
		tFrame.setJMenuBar(menuBar); // Add menu bar to frame

		//Create the menu and add it to the menu bar
		JMenu fileMenu = new JMenu("File"); 
		menuBar.add(fileMenu);

		//Create menu item "Quit" and add to the menu
		quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    System.exit(1);
			  } 
		});
		fileMenu.add(quitItem);

		newItem = new JMenuItem("New/Reset Game");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				tFrame.dispose();
			    GameView view = new GameView();
			  } 
		});
		fileMenu.add(newItem);  


		// Create layout for the the upper UI
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 2));

		// Create Labels
		sPoints = new JLabel("Sunlight");
		sPoints.setVerticalAlignment(JLabel.BOTTOM);
		sPoints.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(sPoints);

		urLabel = new JLabel("Time Manipulation");
		urLabel.setVerticalAlignment(JLabel.BOTTOM);
		urLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(urLabel);

		// Displays current sunlight
		sunlight = new JTextField(3);
		sunlight.setEditable(false); // This value cannot be edited
		sunlight.setFont(new Font(null, Font.BOLD, 14));
		sunlight.setHorizontalAlignment(JTextField.CENTER);
		sunlight.setText("100");
		topPanel.add(sunlight);

		//create undo/redo button leyout
		JPanel urPanel = new JPanel();
		urPanel.setLayout(new GridLayout(1, 4));

		save = new JButton("Save");
		save.setName("Save");
		save.addActionListener(new PvZController(model, 10, 10));
		urPanel.add(save);

		load = new JButton("Load");
		load.setName("Load");
		load.addActionListener(new PvZController(model, 10, 10));
		urPanel.add(load);

		// add undo and redo buttons
		undoB = new JButton("Undo Turn");
		undoB.setName("Undo");
		undoB.addActionListener(new PvZController(model, 10, 10));
		urPanel.add(undoB);

		redoB = new JButton("Redo Turn");
		redoB.setName("Redo");
		redoB.addActionListener(new PvZController(model, 10, 10));
		urPanel.add(redoB);

		//add undo and redo buttons
		topPanel.add(urPanel);
		contentPane.add(topPanel); // Add the the upper level UI to the content pane

		// Create the map layout for the actual game
		JPanel tPanel = new JPanel();
		tPanel.setLayout(new GridLayout(5, 8));

		// For loop to continously add the buttons to the arraylist for GUI
		for (int r = 0; r < 5; r++)
		{
			for (int c = 0; c < 8; c++) {
				buttons[r][c] = new JButton("");
				buttons[r][c].setBackground(new Color(150, 200, 0));
				tPanel.add(buttons[r][c]);
				buttons[r][c].addActionListener(new PvZController(model, r, c));
			}
		}

		tPanel.setPreferredSize(new Dimension(600, 700)); // Set size of the actual game window and add it
		contentPane.add(tPanel);

		// Ignore this was trying to play around with images, will prob add myself to a future version
		// Turns out trying nicely add a strike through the images is a lot more work than expected
		//BufferedImage x = ImageIO.read(new File("images/X.png"));

		// Had to use this in order to get the 'Plants' label in the right spot
		JPanel lPanel = new JPanel();
		lPanel.setLayout(new GridLayout(1,1));

		//Create plants label for plant selection
		pText = new JLabel("Plants");
		pText.setHorizontalAlignment(JLabel.LEFT);
		lPanel.add(pText);
		contentPane.add(lPanel);

		//Create all images for each plants' respective cards
		ImageIcon sunC = new ImageIcon("Images/Sunflower_Card.png");
		ImageIcon peaC = new ImageIcon("Images/Peashooter_Card.png");
		ImageIcon wallC = new ImageIcon("Images/Wallnut_Card.png");
		ImageIcon twinC = new ImageIcon("Images/TwinSunflower_Card.png");
		ImageIcon repC = new ImageIcon("Images/Repeater_Card.png");

		//Create layout for the plants buttons and the next turn button, 
		//Also creates the icons for the plant buttons
		JPanel plantPanel = new JPanel();
		plantPanel.setLayout(new GridLayout(1, 5));
		sunPlant = new JButton();
		sunPlant.setName("S");
		sunPlant.setIcon(sunC);
		sunPlant.addActionListener(new PvZController(model, 10, 10));

		peaPlant = new JButton();
		peaPlant.setName("P");
		peaPlant.setIcon(peaC);
		peaPlant.addActionListener(new PvZController(model, 10, 10));

		wallPlant = new JButton();
		wallPlant.setName("W");
		wallPlant.setIcon(wallC);
		wallPlant.addActionListener(new PvZController(model, 10, 10));

		twinPlant = new JButton();
		twinPlant.setName("T");
		twinPlant.setIcon(twinC);
		twinPlant.addActionListener(new PvZController(model, 10, 10));

		repPlant = new JButton();
		repPlant.setName("R");
		repPlant.setIcon(repC);
		repPlant.addActionListener(new PvZController(model, 10, 10));

		//Add all plant buttons to the GUI
		plantPanel.add(sunPlant);
		plantPanel.add(peaPlant);
		plantPanel.add(wallPlant);
		plantPanel.add(twinPlant);
		plantPanel.add(repPlant);

		// Create button layout 
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));

		buttonPanel.add(plantPanel);
		nextTurnButton = new JButton("End Turn");
		nextTurnButton.addActionListener(new PvZController(model, 10, 10));
		buttonPanel.add(nextTurnButton);

		contentPane.add(buttonPanel);

		tFrame.setResizable(false); // Don't let the board to be resized to look too ugly
		tFrame.setVisible(true);
		tFrame.pack();

		timer = new Timer();
		//schedules a new RemindTask starting at 5 seconds, every 5 seconds
		timer.schedule(new RemindTask(), 5000,5000);

	}

	/**
	 * @author Kevin Sun
	 */
	class RemindTask extends TimerTask {
		public void run() {
			model.endTurn(); //game will progress every 5 s
		}
	}
	
	/**
	 * Event handler for different status' of the game to update the GUI
	 * @param e
	 */
	public void handlePvZEvent(PvZEvent e) {

		Status s = e.getStatus();
		int tRow = e.getRow();
		int tCol = e.getColumn();
		switch(s) {

		case REMOVE_PLANT: { 
			buttons[tRow][tCol].setName("");
			buttons[tRow][tCol].setIcon(null);
			buttons[tRow][tCol].setText("");
			break;

		}
		case PLANT_PLACED:  {
			buttons[tRow][tCol].setName(e.getType()); 
			//Load up animation image,scale it, and set scaled image to the button
			if(buttons[tRow][tCol].getName().equals("S")) { // Plant type check
				ImageIcon temp = new ImageIcon("Images/Sunflower_Idle.gif");
				Image sunI = temp.getImage().getScaledInstance(
						buttons[tRow][tCol].getWidth(),
						buttons[tRow][tCol].getHeight(),
						Image.SCALE_DEFAULT);
				ImageIcon sunP = new ImageIcon(sunI);

				buttons[tRow][tCol].setIcon(sunP);
			}
			else if(buttons[tRow][tCol].getName().equals("P")) {
				ImageIcon temp = new ImageIcon("Images/Peashooter_Idle.gif");
				Image peaI = temp.getImage().getScaledInstance(
						buttons[tRow][tCol].getWidth(),
						buttons[tRow][tCol].getHeight(),
						Image.SCALE_DEFAULT);
				ImageIcon peaP = new ImageIcon(peaI);

				buttons[tRow][tCol].setIcon(peaP);
			}
			else if(buttons[tRow][tCol].getName().equals("W")) {
				ImageIcon temp = new ImageIcon("Images/Wallnut_Idle.png");
				Image wallI = temp.getImage().getScaledInstance(
						buttons[tRow][tCol].getWidth(),
						buttons[tRow][tCol].getHeight(),
						Image.SCALE_DEFAULT);
				ImageIcon wallP = new ImageIcon(wallI);

				buttons[tRow][tCol].setIcon(wallP);
			}
			else if(buttons[tRow][tCol].getName().equals("T")) {
				ImageIcon temp = new ImageIcon("Images/TwinSunflower_Idle.gif");
				Image twinI = temp.getImage().getScaledInstance(
						buttons[tRow][tCol].getWidth(),
						buttons[tRow][tCol].getHeight(),
						Image.SCALE_DEFAULT);
				ImageIcon twinP = new ImageIcon(twinI);

				buttons[tRow][tCol].setIcon(twinP);
			}
			else if(buttons[tRow][tCol].getName().equals("R")) {
				ImageIcon temp = new ImageIcon("Images/Repeater_Still.png");
				Image repI = temp.getImage().getScaledInstance(
						buttons[tRow][tCol].getWidth(),
						buttons[tRow][tCol].getHeight(),
						Image.SCALE_DEFAULT);
				ImageIcon repP = new ImageIcon(repI);

				buttons[tRow][tCol].setIcon(repP);
				break;
			}
		}
		case ZOMBIE_MOVING: 
		{
			buttons[tRow][tCol].setText(e.getType()); 
			if (e.getColumn() != 7)
			{
				buttons[tRow][tCol + 1].setText("");
			}

			break;
		}
		case WON: JOptionPane.showMessageDialog(null, "You Won!", "Game Finished!", JOptionPane.INFORMATION_MESSAGE); System.exit(-1); break;
		case LOST: JOptionPane.showMessageDialog(null, "You Lost...", "Game Finished!", JOptionPane.INFORMATION_MESSAGE); System.exit(-1); break;
		case ZOMBIE_DIED: 
			buttons[tRow][tCol].setName(""); 
			buttons[tRow][tCol].setText(""); 
			break;
		case UPDATE_SUNLIGHT: sunlight.setText(e.getType()); break;
		}
	}
	
    public static boolean isInteger(Object object) {
    	if(object instanceof Integer) {
    		return true;
    	} else {
    		String string = object.toString();
    		
    		try {
    			Integer.parseInt(string);
    		} catch(Exception e) {
    			return false;
    		}	
    	}  
        return true;
    }

	public static void main(String[] args)
	{
		GameView view = new GameView();
	}
}
