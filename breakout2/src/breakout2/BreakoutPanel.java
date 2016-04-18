package breakout2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BreakoutPanel extends JPanel {
private static final int NUM_BRICK_ROWS = 10;
private static final int NUM_BRICK_COLUMNS = 30;
private javax.swing.Timer timer ;
private Ball ball ;
private ArrayList < Brick > bricks ;
private Paddle paddle ;

private Player player ;
private boolean gameStarted = false ;
public BreakoutPanel ( ) {
ball = new Ball(BallColor.Red , this ) ;
paddle = new Paddle(Color.BLUE, this ) ;
bricks = new ArrayList <> () ;
player = new Player ( ) ;
createbricks ( ) ;
addKeyListener (new KeyAdapter ( ) {
public void keyPressed ( KeyEvent e ) {
String s = KeyEvent.getKeyText ( e.getKeyCode ( ) ) ;
if (e.getKeyCode ( ) == KeyEvent.VK_LEFT) {
paddle.moveLeft ( ) ;
}
if (s.equals ( "Right" ) ) {
paddle.moveRight ( ) ;
}
repaint();
}
} ) ;
setFocusable ( true ) ;
addMouseMotionListener (new MouseMotionAdapter ( ) {
boolean firstTime = true ;
int oldX ;
public void mouseMoved ( MouseEvent e ) {
if (firstTime ) {
oldX = e.getX ( ) ;
firstTime = false ;
}
paddle.move( e.getX ( ) - oldX ) ;

oldX = e.getX ( ) ;
repaint();
}
} ) ;
} // end public BreakoutPanel 
private Color getRandomColor ( ) {
Color color = new Color ( ( int ) (Math.random ( ) * 256) , ( int ) (Math.random ( ) * 256) , ( int ) (Math.random ( ) * 256) ) ;
if ( getBackground ( ).equals ( color ) ) {
return Color .RED;
}
return color ;
}
private void createbricks ( ) {
	for ( int row = 0 ; row < NUM_BRICK_ROWS; row++) {
		for ( int col = 0 ; col < NUM_BRICK_COLUMNS; col++) {
			bricks.add (new Brick ( row , col , getRandomColor ( ) ) ) ;
		}
	}
}
public void start() {
gameStarted = true ;
if (timer != null ) {
timer.stop ( ) ;
}
if (! player.isAlive() ) {
player = new Player ( ) ;
ball = new Ball ( BallColor.Red , this ) ;
createbricks ( ) ;
}
timer = new javax.swing.Timer ( BallSpeed.NORMAL.speed ( ) , new
TimeListener ( ) ) ;
timer.start() ;
repaint();
}
public void pause ( ) {
if (timer == null ) {
return ;
}
timer.stop ( ) ;
}
public void changeBallColor (Color color ) {
ball.changeColor ( color ) ;
repaint();
}
public void showMessage ( String s , Graphics2D g2 ) {
Font myFont = new Font ( "SansSerif" , Font.BOLD + Font.ITALIC , 40) ;
g2.setFont (myFont) ;
g2.setColor ( Color .RED) ;

Rectangle2D textBox = myFont.getStringBounds ( s , g2 .
getFontRenderContext ( ) ) ;
g2.drawString ( s , ( int ) ( getWidth ( ) / 2 - textBox.getWidth ( ) / 2) , (
int ) ( getHeight ( ) / 2 - textBox.getHeight ( ) ) ) ;
}
public void paintComponent ( Graphics g ) {
super.paintComponent ( g ) ;
Graphics2D g2 = ( Graphics2D ) g ;
if (bricks.size() == 0 && ! gameStarted ) {
showMessage ( "YOU WIN!" , g2 ) ;
gameStarted = false ;
} else if (!player.isAlive() ) {
showMessage ( "GAME OVER!" , g2 ) ;
gameStarted = false ;
} else {
ball.draw ( g2 ) ;
paddle.draw ( g2 ) ;
for ( Brick brick : bricks ) {
brick.draw ( g2 ) ;
}
}
if (gameStarted ) {
player.draw ( g2 ) ;
}
}
public void changeBallSpeed ( int speed ) {
timer.setDelay ( speed ) ;
}
class TimeListener implements ActionListener {

public void bounceBall ( Ball ball , Brick brick ) {
if (ball.below ( brick ) ) {
ball.goDown ( ) ;
}
if (ball.above ( brick ) ) {
ball.goUp ( ) ;
}
if (ball.leftOf(brick ) ) {
ball.goLeft ( ) ;
}
if (ball.rightOf ( brick ) ) {
ball.goRight ( ) ;
}
} // end bounceball(ball, brick)

public void bounceBall ( Ball ball , ArrayList < Brick > bricks ) {
	if (bricks.size() == 0) {
		return;
	}
	if (bricks.size() == 1) {
	bounceBall ( ball , bricks.get (0) ) ;
	return ;



}
Brick combinedBrick = bricks.get (0).add ( bricks.get (1) ) ;
bounceBall ( ball , combinedBrick ) ;
} // end bounceBall ( Ball ball , ArrayList < Brick > bricks )

public void actionPerformed ( ActionEvent e ) {
Ball newBall = ball.getVirtualBall() ;
ArrayList < Brick > bricksToBeDeleted = new ArrayList < Brick > () ;
for ( Brick brick : bricks ) {
if (brick.intersects(newBall ) ) {
bricksToBeDeleted.add ( brick ) ;
}
}
bounceBall ( ball , bricksToBeDeleted ) ;
for ( Brick brick : bricksToBeDeleted ) {
bricks.remove ( brick ) ;
}
if (newBall.intersects(paddle ) ) {
ball.goUp ( ) ;
if (newBall.getX ( ) + newBall.getWidth ( ) / 2 < paddle.getX ( ) +
paddle.getWidth ( ) / 2) {
ball.goLeft ( ) ;
} else {
ball.goRight ( ) ;
}
} else if (ball.getY ( ) > paddle.getY ( ) - paddle.getHeight ( ) ) {
player.killPlayer() ;
ball.goUp ( ) ;
}
ball.move ( ) ;
repaint();
}
}
} // end ALL (end class BreakoutPanel)
