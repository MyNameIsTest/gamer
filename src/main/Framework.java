package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import util.FrameRate;
import util.KeyboardInput;
import util.MouseInput;

public class Framework extends JFrame implements Runnable {
	private BufferStrategy bs;
	private volatile boolean running;
	private Thread gameThread;
	
	protected FrameRate frameRate;
	protected Canvas canvas;
	protected MouseInput mouse;
	protected KeyboardInput keyboard;
	protected Color appBackground = Color.BLACK;
	protected Color appBorder = Color.LIGHT_GRAY;
	protected Color appFPSColor = Color.GREEN;
	protected Font appFont = new Font("Courier New",Font.PLAIN,14);
	protected String appTitle = "TBD-Title";
	protected float appBorderScale=0.8f;
	protected int appWidth = 640;
	protected int appHeight = 480;
	protected float appWorldWidth = 2.0f;
	protected float appWorldHeight = 2.0f;
	protected long appSleep = 10L;
	protected boolean appMaintainRatio = false;
	
	public Framework() {
		
	}
	
	protected void createAndShowGUI() {
		canvas = new Canvas();
		canvas.setBackground(appBackground);
		canvas.setIgnoreRepaint(true);
		getContentPane().add(canvas);
		setLocationByPlatform(true);
		
		if(appMaintainRatio) {
			getContentPane().setBackground(appBorder);
			setSize(appWidth,appHeight);
			setLayout(null);
			getContentPane().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					onComponentResized(e);
				}
			});
		} else {
			canvas.setSize(appWidth, appHeight);
			pack();
		}
		
		setTitle(appTitle);
		
		keyboard = new KeyboardInput();
		canvas.addKeyListener(keyboard);
		
		mouse = new MouseInput();
		canvas.addMouseListener(mouse);
		
		setVisible(true);
		canvas.createBufferStrategy(2);
		bs=canvas.getBufferStrategy();
		canvas.requestFocus();
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	protected void onComponentResized(ComponentEvent e) {
		Dimension size = getContentPane().getSize();
		int vw = (int)(size.width*appBorderScale);
		int vh = (int)(size.height*appBorderScale);
		int vx = (size.width-vw)/2;
		int vy = (size.height-vh)/2;
		
		int newW = vw;
		int newH = (int)(vw*appWorldHeight/appWorldWidth);
		if(newH > vh) {
			newW = (int)(vh(appWorldWidth/appWorldHeight));
			newH = vh;
		} 
		
		vx +=(vw-newW)/2;
		vy +=(vh-newH)/2;
		
		canvas.setLocation(vx,vy);
		canvas.setSize(newW, newH);
	}
	
	public void run() {
		running = true;
		initialize();
		long curTime = System.nanoTime();
		long lastTime = curTime;
		double nsPerFrame;
		while(running) {
			curTime = System.nanoTime();
			nsPerFrame = curTime-lastTime;
			gameLoop((float)(nsPerFrame/1.0E9));
			lastTime = curTime;
		}
		terminate();
	}
	
	protected void initialize() {
		frameRate = new FrameRate();
		frameRate.initialize();
	}
}
