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
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


@SuppressWarnings("serial")
public class Main extends JPanel {

    boolean isLeftPressed, isRightPressed, isUpPressed, isRPressed, isDownPressed, isPPressed;
	Ball ball = new Ball(this);
      Wall wallp = new Wall(this);
        ArrayList<Wall> arrayWalls = new ArrayList<Wall>();
       boolean gameOverScreen = false;
      long lastLoop = 0;
      long timeScore;
      boolean maxSpeed = false;

      double time = 2500;
        
           public Main() {
               
              
                arrayWalls.add(new Wall(this));
                
                // mouse event handler
                
                MouseListener mouse = new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent me) {
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
                
                // key event handler
                
            KeyListener listener = new KeyListener() {
			ArrayList<KeyEvent> keys = new ArrayList<KeyEvent>();
                       @Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
                                       switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN: isDownPressed = true; break;
            case KeyEvent.VK_RIGHT: isRightPressed = true; break;
            case KeyEvent.VK_LEFT: isLeftPressed = true; break;
            case KeyEvent.VK_UP: isUpPressed = true; break;
            case KeyEvent.VK_R: isRPressed = true; break;
            case KeyEvent.VK_P: isPPressed = true; break;
        }

			}

			@Override
			public void keyReleased(KeyEvent e) {

                                        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN: isDownPressed = false; break;
            case KeyEvent.VK_RIGHT: isRightPressed = false; break;
            case KeyEvent.VK_LEFT: isLeftPressed = false; break;
            case KeyEvent.VK_UP: isUpPressed = false; break;
            case KeyEvent.VK_R: isRPressed = false; break;
            case KeyEvent.VK_P: isPPressed = false; break;
            
        }
                                         
                            
			}
                        
                         
                   
                        
		};
           
      
            
            addKeyListener(listener);
		setFocusable(true);
       }
            
            
           // restart method. Clears walls and resets values
           private void restart() {
               
               
               
               for (int x = arrayWalls.size(); 0 < x; x-- ){
                   
                   arrayWalls.clear();

               }
               ball.x = 31;
	           ball.y = 31;
                   
       maxSpeed = false;
      oldTimer = 0;
      timeInit = true;
      time = 2500;
      timeScore = 0;
                   
                   gameOverScreen = false;
                   
             
           }
           
        double oldTimer;
        boolean timeInit = true;

        // checks if R is pressed
        private boolean checkRestart(boolean gameLoop, int delta) {
            
            boolean a = false;

            
                              if (isRPressed){
                            if (!gameLoop){
               move(delta);
                 restart();
                 a = true;
                 
            }
           
              }
                              
                              
            return a;
        }
        
        public boolean music(boolean ison){
            
            boolean a = ison;
            
                        if (isPPressed){
                
                            if (ison) {
                
                a = false;
              //  System.out.println("Switch off");
                
            } else if (!ison) {
                
                a = true;
             //    System.out.println("Switch on");
            }
            }
            
            
            
            

            return a;
        }
        
        
	private boolean move(int delta){
		

             
            // press R restart action
            
                  // score counter
            timeScore = timeScore +(1*delta);
            
            ball.key(isRightPressed, isLeftPressed, isUpPressed, isDownPressed);
            
            boolean gameLoop = true;
            
            ball.move(delta);
            
           if (timeInit){
            oldTimer = time;
            timeInit = false;
           }
            
            // System.out.println(oldTimer);
                    time -= 1.5*delta;
                     
            
                // create new walls
                if(time <= 0 /* && (Math.random()*20) < 0.009 * delta */) {
                    arrayWalls.add(new Wall(this));
                    
                    
                   time = oldTimer - (15 * delta);
                    System.out.println(oldTimer);
                    timeInit = true;
                    if (time <= 350){
                        
                        time = 350;
                        maxSpeed = true;
                    }
                    
                    
                }
                
                for (int x = 0; x < arrayWalls.size(); x++) {
                   boolean collision = ball.getBounds().intersects(arrayWalls.get(x).getBounds());
                   if (collision){
                       
                       
                       gameLoop = false;
                   }
                   
               //    System.out.println(collision);

                 boolean delete = arrayWalls.get(x).move(delta);
                 if (delete) {
                     arrayWalls.remove(x);
                 }
                }
                return gameLoop;
	}

        // paint method
        
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
                for (int x = 0; x < arrayWalls.size(); x++) {
                   arrayWalls.get(x).sety((x+1)*100);
                    arrayWalls.get(x).paint(g2d);
                }
                if (gameOverScreen){
                    gameOver(g2d);
                }
                
                Font font = new Font("Arial", Font.PLAIN, 36);
                g.setFont(font);
                g.setColor(Color.blue);
                g.drawString("Score:  "+Double.toString(timeScore), 50, 50);
                
                
                // max speed code 
                if (maxSpeed){
                    Font fontMax = new Font("Arial", Font.PLAIN, 100);
                    g.setFont(fontMax);
                    double random = Math.random()*25;
                    
                    if (random <= 5){
                        g.setColor(Color.RED);
                    } else if (random > 5 && random <=10) {
                        g.setColor(Color.BLUE);
                        
                    } else if (random > 10 && random <=15) {
                        g.setColor(Color.GREEN);
                        
                    } else if (random > 15 && random <=20) {
                        g.setColor(Color.YELLOW);
                        
                    } else if (random > 20 && random <=25) {
                        g.setColor(Color.orange);
                        
                    }
                   
                    g.drawString("MAXSPEED",310, 75);
                    
                }
	}
        
        // gameover method to display "gameover"
        public void gameOver(Graphics2D g){
            g.setColor(Color.BLACK);
            
            
            g.drawString("Gameover", 1000/4, 500/4);
            g.drawString("Hit R to restart", 1000/2, 500/4);
             g.drawString("Hit P to Start/Stop music", 1000/3, 500/2);
            
        }

        

	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, MalformedURLException, IOException {
	        	
            boolean startFrameLoop = true;
            boolean exit = false;
                            AudioInputStream audioIns = AudioSystem.getAudioInputStream(Main.class.getResource("dub.wav"));
                Clip clips = AudioSystem.getClip();
                clips.open(audioIns);
                clips.loop(99999999);
            // start frame stuff
            StartFrame sFrame = new StartFrame();
            JFrame startFrame = new JFrame("Jump!");
            		startFrame.setSize(300, 400);
                        startFrame.add(sFrame);
		startFrame.setVisible(true);
                startFrame.setResizable(false);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
                // start frame loop (loops unitl closeframe brings back a positive so that the if(exit) can run)
                while(startFrameLoop) {
                    
                   exit = sFrame.closeFrame();
                    sFrame.repaint();
                    Thread.sleep(1);
                    
                    if (exit){
                        clips.stop();
                        startFrame.dispose();
                        startFrameLoop = false;
                        
                    }
                    
                }
                
                // the game jframe
            JFrame frame = new JFrame("Jump!");
		Main game = new Main();
                
                // song play stuff
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(Main.class.getResource("213.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
		frame.add(game);
		frame.setSize(1000, 400);
		frame.setVisible(true);
                frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                boolean gameLoopInit = true;
                boolean gameLoop = true;
                boolean musicState = false;
               
                long lastLoopTime = System.currentTimeMillis();
                
                
                // game loop init
                while (gameLoopInit) {
                    
                    int delta = (int) (System.currentTimeMillis() - lastLoopTime);  
                    lastLoopTime = System.currentTimeMillis();
                    
                    gameLoop = game.move(delta);
			game.repaint();
			Thread.sleep(1);
                    gameLoopInit = false;
                                    


                    
                }
                
                boolean qwer;
                
		while (true) {
                    
                    // delta time bases screen movement by time instead of frames by using the current time of the last loop and current loop together to
                    // make objects move at the same speed at different fps's
                    // Many game devs use this to solve game tick problems
                    // Also network code for games work in the same way
                     int delta = (int) (System.currentTimeMillis() - lastLoopTime);  
                    lastLoopTime = System.currentTimeMillis();
                    qwer = game.checkRestart(gameLoop, delta);
                    
                    if (qwer) {
                        gameLoop = true;
                    }
                    
                // main game move loop
                    if (gameLoop){
			gameLoop = game.move(delta);                     
                       
                        // game over
                    }else if (!gameLoop){
                        game.gameOverScreen = true;
                         
                    }
                    
                    
                    // music start stop
                        musicState = game.music(musicState);
                        
                        if (musicState) {
                            clip.start();
                        } else if (!musicState) {
                            clip.stop();
                        }
                        
                        // repaint
			game.repaint();
			Thread.sleep(1);
		}
	}
}
