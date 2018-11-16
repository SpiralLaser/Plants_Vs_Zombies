import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameView implements PvZListener {

	private JLabel pText; // Label for different plants
	   
    private ArrayList<JButton> bList; // List of game space buttons
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
	
	
	public GameView(){
        bList = new ArrayList<JButton>();

        
        
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
       topPanel.add(sunlight);
       
       //
       //
       // sunlight.setText(String.valueOf(game.getSunlight())); 
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
       for (int i = 0; i < 40; i++)
       {
           bList.add(new JButton(""));
           tPanel.add(bList.get(i));
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
       
       //Create layout for the plants buttons and the next turn button, eventually will move the end turn button to a lower line so it looks nicer.
       JPanel plantPanel = new JPanel();
       plantPanel.setLayout(new GridLayout(1, 2));
       sunPlant = new JButton("S");
       peaPlant = new JButton("P");
       plantPanel.add(sunPlant);
       plantPanel.add(peaPlant);
       
       // Create button layout 
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new GridLayout(1, 2));
       
       buttonPanel.add(plantPanel);
       nextTurnButton = new JButton("Next Turn");
       buttonPanel.add(nextTurnButton);
       
       contentPane.add(buttonPanel);
       
       
       
       tFrame.setResizable(false); // Don't let the board to be resized to look too ugly
       tFrame.setVisible(true);
       tFrame.pack();
	}
	
	public void handleTicTacToeEvent(PvZEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new GameView();
	}
}