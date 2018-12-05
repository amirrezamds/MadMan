package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import game.MyCanvas;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyScreen extends MyCanvas  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
}
	public MyScreen() throws IOException {
		JFrame thi = new JFrame();

		JButton easy = new JButton();
        easy.setText("unlimited time");
        easy.setBounds(900, 250, 150, 75);
        easy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		playIt("files/smb_coin.wav");
        	}
        });
        
        JButton quit = new JButton();
        quit = new JButton();
        quit.setText("quit");
        quit.setBounds(900, 100, 150, 75);
        quit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	System.exit(0);	
        	}
        });
        
 	    JLabel scoreLabel = new JLabel("BadGuys Left: 0");
		scoreLabel.setText("Number of BadGuys: 60" );
	    scoreLabel.setBounds(900, 10, 150, 75);
        playIt("files/game2.wav");
       
        JLabel rule1 = new JLabel("rule1: ");
        rule1.setText("rule 1: You can kill badguys either by heating them or shooting them");
        rule1.setBounds(900, 325, 500, 75);
        
        JLabel rule2 = new JLabel("rule2: ");
        rule2.setText("rule 2: yellow good guy cannot shoot");
        rule2.setBounds(900, 400, 300, 75);
        
        JLabel rule3 = new JLabel("rule3: ");
        rule3.setText("rule 3: You cannot shoot dragons");
        rule3.setBounds(900, 475, 300, 75);
        
        JLabel rule4=new JLabel("rule4:");
        rule4.setText("rule 4: you have one minute to kill all badguys. after that the game will close.");
        rule4.setBounds(900, 550, 600, 75);
        
        JLabel rule5=new JLabel();
        rule5.setText("rule 5: fireballs are traps. If you touch them, you're lost");
        rule5.setBounds(900,625,500,75);
        
 	    JLabel time = new JLabel();
 	    time.setBounds(900, 35, 150, 75);
 	    time.setText("Time you have : 1:00");
 	    
        JButton song = new JButton("song");
        song.setBounds(900, 175, 150, 75);
        song.setText("change song");
        song.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	playIt("files/fearless2.wav");	
        	}	
        });
        thi.setTitle("mad man by Amirmds");
    	thi.setSize(1250,850);
    	thi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	thi.setVisible(true);
    	//thi.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("files/background.png")))));
    	thi.add(quit);
        thi.add(song);
        thi.add(time);
	    thi.add(scoreLabel);
	    thi.add(easy);
	    thi.add(rule1);
	    thi.add(rule2);
	    thi.add(rule3);
	    thi.add(rule4);
	    thi.add(rule5);
        MyCanvas canvas = new MyCanvas();
        thi.getContentPane().add(canvas);
        thi.setBackground(Color.WHITE);


		if (badguys.size()+badguys2.size()>0) {
			try {
				TimeUnit.SECONDS.sleep(60);
			}catch(InterruptedException e2) {
				e2.printStackTrace();
			}
			playIt("files/FischioB.wav");
			try {
				TimeUnit.SECONDS.sleep(2); {
				}
				}catch(InterruptedException e3) {
					e3.printStackTrace();
				}
			System.exit(0);
		}
	}
	public static void main(String[] args) throws IOException {
		new MyScreen();
	}
}
