package breakout;

import java.awt.*;
// import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class BreakoutPanel extends JPanel {
	public static final int NUM_BRICK_ROWS = 10;
	public static final int NUM_BRICK_COLUMNS = 30;
	
	private Ball ball = new Ball(Color.red) ;
	
	private ArrayList<Brick> bricks = new ArrayList<>() ;
	
	private Paddle paddle = new Paddle ( Color.BLUE) ;
	
	private Player player = new Player ( ) ;
	
	public BreakoutPanel ( ) {
		for ( int row = 0 ; row < NUM_BRICK_ROWS; row++) {
			for ( int col = 0 ; col < NUM_BRICK_COLUMNS; col++) {
				bricks.add(new Brick ( row , col , getRandomColor ( ) ) ) ;
			}
		}
	}
	
	public Color getRandomColor ( ) {
		Color color = new Color ( ( int ) (Math.random ( ) * 256) ,
		( int ) (Math.random ( ) * 256) , ( int ) (Math.random ( ) * 256) ) ;
		i f ( getBackground ( ).equals ( color ) ) {
			return Color .RED;
		}
		return color ;
	}
	
	public void showMessage(String s, Graphics2D g2) {
		Font myFont = new Font ("SansSerif" , Font .BOLD+Font.ITALIC , 40) ;
		g2.setFont (myFont) ;
		g2.setColor (Color.RED) ;
		Rectangle2D textBox = myFont.getStringBounds (s , g2.getFontRenderContext ( ) ) ;
		g2.drawString (s , (int)(getWidth()/2 - textBox.getWidth() / 2) , ( int ) ( getHeight ( ) / 2 - textBox.getHeight ( ) ) ) ;
	}
	 
	public void paintComponent ( Graphics g ) {
		super.paintComponent ( g ) ;
		
		Graphics2D g2 = ( Graphics2D ) g ;
		if (bricks.size() == 0) {
			showMessage ( "YOU WIN!" , g2 ) ;
		} else if ( ! player.isAlive() ) {
			showMessage ( "GAME OVER!" , g2 ) ;
		} else {
		ball.draw ( g2 ) ;
			paddle.draw ( g2 ) ;
			for (Brick brick : bricks \) {
				brick.draw ( g2 ) ;
			}
		}
		player.draw ( g2 ) ;
	} // end paintComponent
} // end class BreakoutPanel

class Ball {
	public static final int SIZE = 10;
	public static final int START_X = 200;
	public static final int START_Y = 400;
	
	private Color color ;
	private int x , y ;
	
	public Ball ( Color color ) {
		this.color = color ;
		x = START_X;
		y = START_Y;
	}
	public void draw ( Graphics2D g2 ) {
		g2.setPaint ( color ) ;
		Ellipse2D e = new Ellipse2D.Double (x , y , SIZE , SIZE ) ;
		g2.fill ( e ) ;
	}
} // end class Ball

class Paddle {
	
	public static final int WIDTH = 50;
	public static final int HEIGHT = 10;
	public static final int START X = 200;
	public static final int START Y = 430;
	
	private Color c o l o r ;
	private int x , y ;
	public Paddle ( Color c o l o r ) {
		this . c o l o r = c o l o r ;
		x = START X;
		y = START Y;
	}
	public void draw ( Graphics2D g2 ) {
		g2 . setPaint ( c o l o r ) ;
		Rectangle2D r = new Rectangle2D . Double (x , y , WIDTH, HEIGHT) ;
		g2 . f i l l ( r ) ;
	}
} // end class Paddle

class Brick {
	public static final int HEIGHT = 10;
	public static final int WIDTH = 30;
	public static final int BRICK H GAP = 2 ;
	public static final int BRICK V GAP = 2 ;
	private int x , y ;
	private Color c o l o r ;
	public Brick ( int row , int col , Color c o l o r ) {
	this . c o l o r = c o l o r ;
	x = BRICK H GAP + row * (BRICK H GAP + Brick .WIDTH) ;
	y = BRICK V GAP + c o l * (BRICK V GAP + Brick .HEIGHT) ;
	}
	public void draw ( Graphics2D g2 ) {
	g2 . setPaint ( c o l o r ) ;
	Rectangle2D r = new Rectangle2D . Double (x , y , WIDTH, HEIGHT) ;
	g2 . f i l l ( r ) ;
	}
} // end class Brick
