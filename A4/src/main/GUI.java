package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *	@brief Main class for displaying graphics and taking user input.
 *	@author Ethan Vince-Budan
 *	@date 2021-04-12
 */
public class GUI extends JPanel {
	
	private static Controller controller;
	
	private JLabel score, highScore, gameOver;
	private JButton restartButton;
	private BoardDisplay disp;
	private JPanel internalPanel;
	
	/**
	 *	@brief Constructor for GUI.
	 *	@details This constructor sets up the graphical environment in which the board will be
	 *	displayed.
	 *	@return new GUI object.
	 */
	public GUI() {
		controller = new Controller(this);
		
		restartButton = new JButton("New Game");
		restartButton.setFocusable(false);
		restartButton.setBackground(new Color(0x8f7a66));
		restartButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		restartButton.addActionListener(controller);
		
		score = new JLabel("Score");
		highScore = new JLabel("HighScore");
		gameOver = new JLabel("Some moves remain!");
		
		disp = new BoardDisplay();
		
		internalPanel = new JPanel();
		internalPanel.add(restartButton);
		internalPanel.add(score);
		internalPanel.add(highScore);
		internalPanel.add(gameOver);
		
		this.setLayout(new BorderLayout());
		this.add(internalPanel, BorderLayout.PAGE_START);
		this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));;
	}
	
	/**
	 *	@brief Updates the entire GUI of the game.
	 *	@see BoardDisplay for more on how the actual board is displayed on screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		score.setText("Score: " + String.valueOf(Board.getScore()));
		highScore.setText("High Score: " + String.valueOf(Board.getHighScore()));
		if (controller.isGameOver()) {
			this.gameOver.setText("NO MOVES LEFT!");
		} else {
			this.gameOver.setText("Some moves remain!");
		}
		this.disp.paintBoard(g, 10, internalPanel.getHeight()+7, this.getWidth()-35, this.getHeight()-100);
	}

	/**
	 *	@brief The program entry point.
	 *	@details This method initializes a new JFrame, in which the GUI will be displayed.
	 *	Additionally, links between this GUI and the Controller are established so that
	 *	user input can be correctly handled.
	 *	@param args the command-line arguments.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("2048 By Ethan Vince-Budan");
		GUI gui = new GUI();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,540);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.addKeyListener(controller);
		
		frame.setVisible(true);
	}

}
