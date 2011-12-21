package com.episkipoe.hat.client;


import com.episkipoe.hat.common.ImageLibrary;
import com.episkipoe.hat.common.MouseMode;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.player.MovePlayer;
import com.episkipoe.hat.player.Player;
import com.episkipoe.hat.rooms.MagicRoom;
import com.episkipoe.hat.rooms.Room;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {
	static private Canvas canvas;
	private final CssColor redrawColor = CssColor.make("rgba(255,255,255,0.6)");
	static private final int canvasWidth = 800;
	static private final int canvasHeight = 600;
	static private final int refreshRate = 50;
	
	static public ImageLibrary images;
	static public Player player;
	static public Room room;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		canvas = Canvas.createIfSupported();
        if (canvas == null) {
              RootPanel.get().add(new Label("Sorry, your browser doesn't support the HTML5 Canvas element"));
              return;
        }
        canvas.setStyleName("mainCanvas");
        canvas.setWidth(canvasWidth + "px");
        canvas.setCoordinateSpaceWidth(canvasWidth);
 
        canvas.setHeight(canvasHeight + "px");
        canvas.setCoordinateSpaceHeight(canvasHeight);
        
		currentMousePos = new Point();
		player = new Player();
		player.setLocation(new Point(canvasWidth*0.5, canvasHeight*0.5));
		loadImages();
        RootPanel.get().add(canvas);
		switchRoom(new MagicRoom());
        
        // setup timer
        final Timer timer = new Timer() {
          @Override
          public void run() {
        	  draw();
        	  
          }
        };
        timer.scheduleRepeating(refreshRate);
	}
	
	static public void setMouseMode(MouseMode newMode) {
		if(newMode.mouseMoveHandler() != null) canvas.addMouseMoveHandler(newMode.mouseMoveHandler());
		if(newMode.mouseUpHandler() != null) canvas.addMouseUpHandler(newMode.mouseUpHandler());
		if(newMode.mouseDownHandler() != null) canvas.addMouseDownHandler(newMode.mouseDownHandler());
	}
	
	static public Point getPointFromEvent(MouseMoveEvent event) {
		return new Point(event.getRelativeX(canvas.getElement()), event.getRelativeY(canvas.getElement()));
	}
	static public Point getPointFromEvent(MouseDownEvent event) {
		return new Point(event.getRelativeX(canvas.getElement()), event.getRelativeY(canvas.getElement()));
	}

	/*
	 * Setup
	 */
	static private void loadImages() {
		images = new ImageLibrary();
		images.loadImage(player.getFilename());	
	}

	public static class StartGame implements Runnable {
		@Override
		public void run() {
			setMouseMode(new MovePlayer());
		}
	}
	
	static public void switchRoom(Room newRoom) {
		room = newRoom;
		images.loadImages(room.getAllImages());
	}
	
	/*
	 * Draw
	 */
	public void draw() {
		if(player == null || room == null) return; 
		
		Context2d context = canvas.getContext2d();
		context.setFillStyle(redrawColor);
	    context.fillRect(0, 0, canvasWidth, canvasHeight);
	    room.draw(context);
		player.draw(context);
	}
	
	/*
	 *   Input Events
	 */
	
	static private Point currentMousePos;
	static public Point getCurrentPos() { return currentMousePos; }


}
