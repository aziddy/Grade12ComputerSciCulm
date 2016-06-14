/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author alexander
 */
public class Wall {
    private Main game;
    
    double sizey = (Math.random()*75)+30;
    double sizex = (Math.random()*40)+30;
    public double x = 1500;
    public double y = 0;
    public double speed = 0.0005;
    
    Wall(Main game) {
    this.game = game;

    
}
    
    public boolean move(int delta) {
        boolean delete = false;
        x -= speed * delta;
        speed += 0.00075;
        
        
        
        if (x < -30) {
            x = -30;
            delete = true;
            
        }
        return delete;
    }
    
    public void setx(double xx) {
        x = xx;   
    }
    
    public void sety(double yy) {
        y = yy;   
    }
    
   public Rectangle getBounds () {
       return new Rectangle((int) x,(int) (game.getHeight()-sizey),(int) sizex,(int) sizey);
   }
           

    public void paint(Graphics2D g){
            g.setColor(Color.RED);
                g.fill(new Rectangle2D.Double(x, game.getHeight()-sizey, sizex, sizey));
    }
    
}
