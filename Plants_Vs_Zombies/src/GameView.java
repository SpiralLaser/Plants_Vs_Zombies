import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The View component in the MVC model. Creates the GUI fully and handles events when a button is clicked.
 * @author Tri Nhan
 *
 */
public class GameView implements PvZListener{

	private JLabel pText; // Label for different plants

	private JButton[][] buttons; // List of game space buttons
	private ArrayList<JButton> pList; // List of plant buttons

	private JMenuItem quitItem; // Quit option
	private JMenuItem newItem; // Reset/create a new game

	private JLabel sPoints; // sunlight point label
	private JLabel wNumber;

	private JTextField sunlight;  //The actual value display
	private JTextField wave;

	private JButton nextTurnButton; //Button to enter next game turn
	private JButton peaPlant;
	private JButton sunPlant;
	private JButton wallPlant;
	private JButton twinPlant;
	private JButton repPlant;


	public GameView(){

		PvZModel model = new PvZModel();
		model.addPvZListener(this);
		
		buttons = new JButton[5][8];   

		JFrame tFrame = new JFrame("Plants Vs. Zombies");
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
		fileMenu.add(quitItem);

		newItem = new JMenuItem("New/Reset Game");
		fileMenu.add(newItem);  


		// Create layout for the the upper UI
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 2));

		// Create Labels
		sPoints = new JLabel("Sunlight");
		sPoints.setVerticalAlignment(JLabel.BOTTOM);
		sPoints.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(sPoints);

		wNumber = new JLabel("Wave");
		wNumber.setVerticalAlignment(JLabel.BOTTOM);
		wNumber.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(wNumber);


		// Displays current sunlight
		sunlight = new JTextField(3);
		sunlight.setEditable(false); // This value cannot be edited
		sunlight.setFont(new Font(null, Font.BOLD, 14));
		sunlight.setHorizontalAlignment(JTextField.CENTER);
		sunlight.setText("100");
		topPanel.add(sunlight);

		//
		//sunlight.setText(String.valueOf(game.getSunlight())); 
		//
		// Need to decide on where exactly to put/do this, will be done later

		wave = new JTextField(3);
		wave.setEditable(false);
		wave.setFont(new Font(null, Font.BOLD, 14));
		wave.setHorizontalAlignment(JTextField.CENTER);
		topPanel.add(wave);

		wave.setText(""); // Set to display wave number maybe future option

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

		tPanel.setPreferredSize(new Dimension(800, 600)); // Set size of the actual game window and add it
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

		//Create all image icons for different plants and their respective cards
		ImageIcon sunP = new ImageIcon("Images/Sunflower_Idle.gif");
		Image img = sunP.getImage();
		Image tImg = img.getScaledInstance(107, 66, java.awt.Image.SCALE_SMOOTH );
		sunP = new ImageIcon(tImg);
		
		ImageIcon sunC = new ImageIcon("Images/Sunflower_Card.png");
		ImageIcon peaP = new ImageIcon("Images/Peashooter_Idle.gif");
		ImageIcon peaC = new ImageIcon("Images/Peashooter_Card.png");
		ImageIcon wallP = new ImageIcon("Images/Wallnut_Idle.png");
		ImageIcon wallC = new ImageIcon("Images/Wallnut_Card.png");
		ImageIcon twinP = new ImageIcon("Images/TwinSunflower_idle.gif");
		ImageIcon twinC = new ImageIcon("Images/TwinSunflower_Card.png");
		ImageIcon repP = new ImageIcon("Images/Repeater_Still.png");
		ImageIcon repC = new ImageIcon("Images/Repeater_Card.png");
		
		//Create layout for the plants buttons and the next turn button, eventually will move the end turn button to a lower line so it looks nicer.
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
	}

	public void handlePvZEvent(PvZEvent e) {

        Status s = e.getStatus();
        switch(s) {
            case REMOVE_PLANT: {
            	buttons[e.getRow()][e.getColumn()].setText("");
            	break;

            }
            case PLANT_PLACED:  buttons[e.getRow()][e.getColumn()].setName(e.getType()); 
            					break;
            case ZOMBIE_MOVING: 
            	{
            		buttons[e.getRow()][e.getColumn()].setText(e.getType()); 
            		if (e.getColumn() != 7)
            		{
            			buttons[e.getRow()][e.getColumn()+1].setText("");
            		}
            		
            		break;
            	}
            case WON: JOptionPane.showMessageDialog(null, "You Won!", "Game Finished!", JOptionPane.INFORMATION_MESSAGE); System.exit(-1); break;
            case LOST: JOptionPane.showMessageDialog(null, "You Lost...", "Game Finished!", JOptionPane.INFORMATION_MESSAGE); System.exit(-1); break;
            case ZOMBIE_DIED: buttons[e.getRow()][e.getColumn()].setText(""); break;
            case UPDATE_SUNLIGHT: sunlight.setText(e.getType()); break;
        }
    }

	public static void main(String[] args)
	{
		GameView view = new GameView();
	}
}
