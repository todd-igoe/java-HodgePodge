package breakout2;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Paddle extends BreakoutShape {
	private static final int START_X = 200;
	private static final int START_Y = 430;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 10;
	private static final int SPEED = 10;
	
	private BreakoutPanel panel ;
	
	public Paddle ( Color color , BreakoutPanel panel ) {
		super (new Rectangle2D.Double (START_X, Paddle.START_Y, Paddle.WIDTH,
		Paddle.HEIGHT) , color , true ) ;
		this.panel = panel ;
	}
	public void move( int dx ) {
		if( ( getX ( ) + dx >= 0) && ( getX ( ) + dx + WIDTH <= panel.getWidth ( ) )) {
		move(dx , 0) ;
		}
	}
	
	public void moveRight ( ) {
		move(SPEED) ;
	}
	
	public void moveLeft ( ) {
		move( - SPEED) ;
	}
	
} // end class