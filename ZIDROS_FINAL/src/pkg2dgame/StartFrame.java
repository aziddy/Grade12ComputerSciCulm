/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author alexander
 */
public class StartFrame extends JPanel{
    
    
    int mouseX, mouseY;
    
    public StartFrame() {
    
        
        // mouse listener
    MouseListener mouse = new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent me) {
                        mouseX = me.getX();
                        mouseY = me.getY();
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                        
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                       
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {

                    }
                    
                    
                };
                
                addMouseListener(mouse);
                setFocusable(true);
    }

    
    // checks mouse x and y to make sure to you click on the button to start and exits the start jframe
    public boolean closeFrame() {
        
        boolean exit = false;
        
        if ((mouseX > 70 && mouseX < 220) && (mouseY > 300 && mouseY < 350)){
            exit = true;
        }
        
        return exit;
    }
    
    
    // generate GUI for startframe
    	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	//	ball.paint(g2d);
        //   g.setFont(font);
              
                g.setColor(Color.black);
               g.fill3DRect(70, 300, 150, 50, true);
                 g.setColor(Color.white);
                 Font fonts = new Font("Arial", Font.PLAIN, 36);
         g.setFont(fonts);
                g.drawString("START", 87, 337);
                g.setColor(Color.black);
                
                fonts = new Font("Arial", Font.PLAIN, 18);
                g.setFont(fonts);
                g.drawString("Up to jump", 100, 110);
                g.drawString("Left and Right to move horizontally ", 10, 150);
                g.drawString("Avoid hitting red walls", 55, 190);
                g.drawString("Hitting P is a secret function", 35, 230);
                g.drawString("Hit R to restart", 85, 270);
                
                fonts = new Font("Arial", Font.ITALIC, 40);
                g.setFont(fonts);
                g.drawString("ALEX ZIDROS", 15, 50);
                
                
                
                
        }
}
