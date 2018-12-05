package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import sun.audio.*;

public class MyCanvas extends Canvas implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Goodguy link = new Goodguy(10,10,30,30,"files/a.png");
	Goodguy2 link2 = new Goodguy2(10,10,30,30,"files/pikachu.jpg");
    LinkedList<Badguy> badguys=new LinkedList<Badguy>();
    LinkedList<Badguy2> badguys2=new LinkedList<Badguy2>();
    LinkedList<Badguy3> badguys3 = new LinkedList<Badguy3>();
    LinkedList<Projectile> knives = new LinkedList<Projectile>();
	public MyCanvas() {
		this.setSize(1024,768);
		this.addKeyListener(this);		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i<30; i++) {
			Badguy bg = new Badguy(rand.nextInt(winwidth), rand.nextInt(winheight), 50, 50, "files/right.png");
			Badguy2 bg2 = new Badguy2(rand.nextInt(winwidth), rand.nextInt(winheight), 50, 50, "files/left.png");
			Badguy3 bg3 = new Badguy3(rand.nextInt(winwidth), rand.nextInt(winheight), 50, 50, "files/fireball.jpg");
    		Rectangle r = new Rectangle(100,100,30,30);
    		/*TimerTask repeatedTask = new TimerTask() {
    			   public void run() {
    			      for(int i = 0; i < badguys.size(); i++) {
    			         Badguy bg = (Badguy) badguys.get(i);
    			   	 bg.setxCoord(bg.getxCoord() - 1);       
    			      }
    			      repaint();
    			   }
    			};
    			Timer timer = new Timer("Timer");
    			long delay  = 1000L;	    
    			long period = 1000L;
    			timer.scheduleAtFixedRate(repeatedTask, delay, period);*/
		    if (r.contains(link.getxCoord(), link.getyCoord())) {
		    	continue;
		    }
		    badguys.add(bg);
		    }	
		for(int i=0;i<30;i++) {
		Badguy2 bg2 = new Badguy2(rand.nextInt(winwidth), rand.nextInt(winheight), 50, 50, "files/left.png");
		Rectangle r2 = new Rectangle(100,100,30,30);
		
		  if (r2.contains(link.getxCoord(), link.getyCoord())) {
		    	continue;
		    }
		  if (r2.contains(link2.getxCoord(), link2.getyCoord())) {
		    	continue;
		    }
		    badguys2.add(bg2);
		}
		for(int i=0;i<5;i++) {
			Badguy3 bg3 = new Badguy3(rand.nextInt(winwidth), rand.nextInt(winheight), 50, 50, "files/fireball.jpg");
			Rectangle r3 = new Rectangle(100,100,30,30);
			
			  if (r3.contains(link.getxCoord(), link.getyCoord())) {
			    	continue;
			    }
			  if (r3.contains(link2.getxCoord(), link2.getyCoord())) {
			    	continue;
			    }
			    badguys3.add(bg3);
			}
	}

	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
    public void paint(Graphics g) {
    	g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), this);
    	g.drawImage(link2.getImg(), link2.getxCoord(), link2.getyCoord(), this);
    
    	for(int i = 0; i < badguys.size(); i++) {
    		Badguy bg = (Badguy) badguys.get(i);
    		g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
    		Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
    		
    	for (int a = 0; a<badguys2.size();a++) {
    			Badguy2 bg2 = (Badguy2) badguys2.get(a);
    			g.drawImage(bg2.getImg(), bg2.getxCoord(), bg2.getyCoord(), bg2.getWidth(), bg2.getHeight(), this);
        		Rectangle r2 = new Rectangle(bg2.getxCoord(),bg2.getyCoord(),bg2.getWidth(),bg2.getHeight());
    		}
    	for (int a = 0; a<badguys3.size();a++) {
			Badguy3 bg3 = (Badguy3) badguys3.get(a);
			g.drawImage(bg3.getImg(), bg3.getxCoord(), bg3.getyCoord(), bg3.getWidth(), bg3.getHeight(), this);
    		Rectangle r3 = new Rectangle(bg3.getxCoord(),bg3.getyCoord(),bg3.getWidth(),bg3.getHeight());
		}
        for (int j =0; j<knives.size(); j++) {
    	    Projectile k = (Projectile) knives.get(j);
    	    if (k.getxCoord()>this.getWidth()) {knives.remove(k);}
    	    k.setxCoord(k.getxCoord()+1);
    	    g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
        	
    	Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
    	if (kr.intersects(r)) {
    	
    		badguys.remove(i);
    		knives.remove(j);
    	            }
    	repaint();
        } 
    	    }
    	}
    
	public void keyTyped(KeyEvent e) {
    
    }
    
    public void keyPressed(KeyEvent e) {
    	System.out.println(e);
    	if (e.getKeyCode()==32) {
    		Projectile knife = new Projectile(link.getxCoord(),link.getyCoord(),30,30,"files/ball3.png");
    		knives.add(knife);
    		playIt("files/projectile.wav");
    	}
    	link.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());
    	link2.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
    	
    	for(int i = 0; i < badguys.size(); i++) {
    		Badguy bg = (Badguy) badguys.get(i);
    		Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
    		Rectangle ggr5 = new Rectangle(link2.getxCoord(),link2.getyCoord(),link2.getWidth(),link2.getHeight());
    		Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
    	 if (ggr.intersects(r)) {
            	System.out.println("badguy hit by link");
            	badguys.remove(i);
            	playIt("files/supermario1.wav");
            } 	
    	 repaint();
    	}
    	for(int a = 0; a < badguys2.size(); a++) {
    		Badguy2 bg2 = (Badguy2) badguys2.get(a);
    		Rectangle ggr2 = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
    		Rectangle ggr23 = new Rectangle(link2.getxCoord(),link2.getyCoord(),link2.getWidth(),link2.getHeight());
    		Rectangle rg = new Rectangle(bg2.getxCoord(), bg2.getyCoord(), bg2.getWidth(), bg2.getHeight());
    	 if ((ggr2.intersects(rg))||(ggr23.intersects(rg))) {
            	System.out.println("badguy hit by link");
            	badguys2.remove(a);
            	playIt("files/supermario2.wav");
            }
    	 for(int b = 0; b < badguys3.size(); b++) {
     		Badguy3 bg3 = (Badguy3) badguys3.get(b);
     		Rectangle g2 = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
     		Rectangle gr23 = new Rectangle(link2.getxCoord(),link2.getyCoord(),link2.getWidth(),link2.getHeight());
     		Rectangle rgr = new Rectangle(bg3.getxCoord(), bg3.getyCoord(), bg3.getWidth(), bg3.getHeight());
     	 if ((g2.intersects(rgr))||(gr23.intersects(rgr))) {
				playIt("files/FischioB.wav");
     		    try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.exit(0);
             }
    	 for(int i = 0; i < badguys.size(); i++) {
     		Badguy bg = (Badguy) badguys.get(i);
     		Rectangle ggr = new Rectangle(link.getxCoord(),link.getyCoord(),link.getWidth(),link.getHeight());
     		Rectangle gr = new Rectangle(link2.getxCoord(),link2.getyCoord(),link2.getWidth(),link2.getHeight());
     		Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
     	 if ((ggr.intersects(r))||(gr.intersects(r))) {
             	System.out.println("badguy hit by link");
             	badguys.remove(i);
             	playIt("files/supermario1.wav");
             } 	
     	 repaint();
     	}
    	}
    }
}
	@Override
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		

		if (badguys.size()+badguys2.size()==0) {
            playIt("files/finish.wav");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
}
