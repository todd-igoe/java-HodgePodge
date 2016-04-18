package breakout2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

//import breakout2.BallSpeedListener;

public class BreakoutFrame extends JFrame {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 488;
	private BreakoutPanel panel = new BreakoutPanel ( ) ;
	
	public BreakoutFrame ( ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE) ;
		setLocation (100 ,100) ;
		setSize(WIDTH, HEIGHT) ;
		add(panel);
		setResizable(false );
	}
	
	public void displayMenu ( ) {
		JMenuBar menuBar = new JMenuBar ( ) ;
		menuBar.add (new GameMenu( ) ) ;
		menuBar.add (new ColorMenu ( ) ) ;
		menuBar.add (new SpeedMenu ( ) ) ;
		setJMenuBar (menuBar) ;
	}
	

	private class GameMenu extends JMenu {
		public GameMenu( ) {
		super ( "Game" ) ;
		JMenuItem startGameMI = new JMenuItem ( "Start" , 'S' ) ;
		startGameMI.setAccelerator(KeyStroke.getKeyStroke ( KeyEvent.VK_S ,
		InputEvent.CTRL_MASK) ) ;
		JMenuItem pauseMI = new JMenuItem ( "Pause" , 'P' ) ;
		pauseMI.setAccelerator(KeyStroke.getKeyStroke ( KeyEvent .VK_P,
		InputEvent.CTRL_MASK) ) ;
		JMenuItem quitMI = new JMenuItem ( "Quit" ) ;
		startGameMI.addActionListener (new ActionListener ( ) {
		
		public void actionPerformed ( ActionEvent e ) {
		panel.start( ) ;
		}
		} ) ;
		pauseMI.addActionListener (new ActionListener ( ) {
		public void actionPerformed ( ActionEvent e ) {
		panel.pause ( ) ;
		}
		} ) ;
		quitMI.addActionListener (new ActionListener ( ) {
		public void actionPerformed ( ActionEvent e ) {
		System.exit(0) ;
		}
		} ) ;
		add ( startGameMI ) ;
		add ( pauseMI ) ;
		add ( quitMI ) ;
		}
	} // end class GameMenu extends JMenu
	private class ColorMenu extends JMenu {
	public ColorMenu ( ) {
		super ( "Ball Color" ) ;
		for ( BallColor color : BallColor.values ( ) ) {
			JMenuItem menuItem = new JMenuItem ( color.name ( ) + " Ball" ) ;
			menuItem.addActionListener (new BallColorListener ( color ) ) ;
			add ( menuItem ) ;
		}
	} // end public ColorMenu
	}
	private class BallColorListener implements ActionListener {
	private BallColor color ;
	
	public void actionPerformed ( ActionEvent e ) {
		panel.changeBallColor ( color ) ;
	}
	
	public BallColorListener ( BallColor color ) {
	this.color = color ;
	}
	}
	private class SpeedMenu extends JMenu {
	public SpeedMenu ( ) {
	super ( "Ball Speed" ) ;
	for ( BallSpeed s : BallSpeed.values ( ) ) {
	JMenuItem menuItem = new JMenuItem ( s.name ( ) ) ;
	menuItem.addActionListener (new BallSpeedListener ( s.speed ( ) ) ) ;
	add ( menuItem ) ;
	}
	}
	}
	private class BallSpeedListener implements ActionListener {
private int speed ;
public void actionPerformed ( ActionEvent e ) {
panel.changeBallSpeed ( speed ) ;
}
public BallSpeedListener ( int speed ) {
this.speed = speed ;
}
}
} // end class BreakoutFrame
