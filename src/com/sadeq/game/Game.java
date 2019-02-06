package com.sadeq.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
	
	private JFrame frame;
	public boolean running = false;
	public int tickCount = 0;
	public Game() {
		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		//different system will run while loop faster/slower
		long lastTime = System.nanoTime();
		//how may ns in 1 tick for said machine
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		//delta is how many ns have gone by so far
		double delta=0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			
			boolean shouldRender = true;
			
			while(delta>=1) {
				ticks++;
				tick();
				delta-=1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(shouldRender) {
				frames++;
				render();
			}
			
			if(System.currentTimeMillis()-lastTimer >= 1000) {
				lastTimer +=1000;
				System.out.println(frames + ", "+ ticks);
				frames=0;
				ticks=0;
			}
			
		}
	}
	
	//updates the logic of game
	public void tick() {
		tickCount++;
	}
	
	//prints out what the logic/tick method says to print
	public void render() {
		
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
