package breakout2;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Brick extends BreakoutShape {
	private static final int HEIGHT = 10;
	private static final int WIDTH = 30;
	private static final int BRICK_H_GAP = 2 ;
	private static final int BRICK_V_GAP = 2 ;
	
	public Brick ( int row , int col , Color color ) {
		super (new Rectangle2D.Double (BRICK_H_GAP + row * (BRICK_H_GAP +
				Brick .WIDTH) , BRICK_V_GAP + col * (BRICK_V_GAP + Brick.HEIGHT) ,
				WIDTH, HEIGHT) , color , true ) ;
	}
	private Brick ( Rectangle2D rectangle , Color color ) {
		super ( rectangle , color , true ) ;
	}
	
	public Brick add ( Brick other ) {
	Rectangle2D rectangle1 = super.getBounds ( ) ;
	Rectangle2D rectangle2 = other.getBounds ( ) ;
	rectangle1.add ( rectangle2 ) ;
	return new Brick ( rectangle1 , super.getColor ( ) ) ;
	}
}