/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg2dgame;

/**
 *
 * @author Admin
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Ball {
	double x = 31;
	double y = 31;
        
        int ballSize = 30;
        
            double gravity = 0; // acceleration crap
            boolean gravityInit = false;
            boolean jumping;
            boolean jumpingInit;

            boolean collision = false; // collision boolean
            

                boolean onGround;  // if touching ground
	double xa = 0;
	double ya = 0;
	private Main game;

        // declares main class in ball or something
	public Ball(Main game) {
		this.game = game;
	}
        
        
        
        // takes keylistner booleans from main and brings it here to apply transformation to the ball
        public void key(boolean right, boolean left, boolean up, boolean down){
            
            // move left and right
            if (left){
                xa = -0.5;
            } else if (right){
                xa = 0.5;
            } else {
                
                xa = 0;
                
            }
            
            // make ball do jump if on ground and pressing up
            if (up){
                if (onGround && !jumping){
                    y = y - 1;
                    jumpingInit = true;
                }
            } 
            
            if (down){
                ya = 1;
            } else {
                ya = 0;
            }
        }
        

        
        
	  void move(int delta) {

              
            int ground = game.getHeight();  // ground y value
            
            // Y axis collision
            
            if (y >= ground - ballSize){

                onGround = true;
            } else {
                onGround = false;
            }

            
            // touching ground if stuff
            
            if (onGround) {
                                y = ground - ballSize;
                gravityInit = true;
                gravity = 0;
                jumping = false;
                
            } 
            
            
            // jumping and gravity
            
            if (jumpingInit){
                                jumpingInit = false;
                                gravity = -1;
                jumping = true;
            }
            
            if (jumping){
                
            }
            
            if (!onGround && jumping ) {
            
            gravity += 0.003 * delta;
            y += gravity * delta;
                
                
            }  else if (!onGround){
            
               gravity = 0.5;
 
                y += (gravity * (delta));
            
        } 
            
            
           
            // Window Collision
            
		if (x + xa < 0)
			x = 0;
		if (x + xa > game.getWidth() - ballSize)
			x = game.getWidth() - ballSize;
		if (y + ya < 0)
			ya = 0;

		x = x + (xa * delta);
		y = y + (ya * delta);
	}


          
   public Rectangle getBounds () {
       return new Rectangle((int) x,(int) y,(int) ballSize,(int) ballSize);
   }

             
          
	public void paint(Graphics2D g) {
            g.setColor(Color.blue);
                g.fill(new Ellipse2D.Double(x, y, 30,30));

	}
}
