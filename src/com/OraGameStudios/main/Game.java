package com.OraGameStudios.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.OraGameStudios.entities.Player;

public class Game extends JPanel implements Runnable,KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Thread gameThread;
	private boolean running;
	private final int FPS = 60;
	private long targetTime = 1000/FPS;
	public static Player player;
	public void stop() {
		running = false;
	}
	public void start() {
		if(gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();
		}
	}
	public Game() {
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.BLACK);
		player = new Player(0,0);
		addKeyListener(this);
		setFocusable(true);
		try {
            playerImage = ImageIO.read(new File("caminho/para/sua/imagem.png")); // Use o caminho correto
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void run() {
		long startTime,elapsedTime,waitTime;
		running = true;
		
		while(running) {
			startTime = System.nanoTime();
			tick();
			repaint();
			elapsedTime = System.nanoTime() - startTime;
			waitTime = targetTime - elapsedTime/1000000;
			if(waitTime > 0) {
				try {
					Thread.sleep(waitTime);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void tick() {
		player.tick();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.render(g);
		
	}
	public static void main(String args[]) { 
		JFrame frame = new JFrame("Game");
		Game gamePanel = new Game();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.pack();
		frame.setVisible(true);
		
		gamePanel.start();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
        	player.up = true;
        }
        if (keyCode == KeyEvent.VK_S) {
        	player.down = true;
        }
        if (keyCode == KeyEvent.VK_A) {
        	player.left = true;
        }
        if (keyCode == KeyEvent.VK_D) {
        	player.right = true;
        }
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
        	player.up = false;
        }
        if (keyCode == KeyEvent.VK_S) {
        	player.down = false;
        }
        if (keyCode == KeyEvent.VK_A) {
        	player.left = false;
        }
        if (keyCode == KeyEvent.VK_D) {
        	player.right = false;
        }
		
	}
}
