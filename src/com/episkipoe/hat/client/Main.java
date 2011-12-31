package com.episkipoe.hat.client;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.episkipoe.hat.common.GameStorage;
import com.episkipoe.hat.common.Inventory;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageLibrary;
import com.episkipoe.hat.common.interact.MouseMode;
import com.episkipoe.hat.player.Player;
import com.episkipoe.hat.rooms.InventoryRoom;
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
	static private Context2d context;
	private final CssColor redrawColor = CssColor.make("rgba(255,255,255,0.6)");
	static private final int canvasWidth = 800;
	static private final int canvasHeight = 600;
	static private final int refreshRate = 50;
	
	static public ImageLibrary images;
	static public Player player;
	static public Inventory inventory;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		canvas = Canvas.createIfSupported();
        if (canvas == null) {
              RootPanel.get().add(new Label("Sorry, your browser doesn't support the HTML5 Canvas element"));
              return;
        }
		context = canvas.getContext2d();
        canvas.setStyleName("mainCanvas");
        canvas.setWidth(canvasWidth + "px");
        canvas.setCoordinateSpaceWidth(canvasWidth);
 
        canvas.setHeight(canvasHeight + "px");
        canvas.setCoordinateSpaceHeight(canvasHeight);
        
		currentMousePos = new Point();
		player = new Player();
		loadImages();
        RootPanel.get().add(canvas);
        try {
        	GameStorage.startup();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // setup timer
        final Timer timer = new Timer() {
          @Override
          public void run() {
        	  draw();
          }
        };
        timer.scheduleRepeating(refreshRate);
	}

	/*
	 * Setup
	 */
	static private void loadImages() {
		images = new ImageLibrary();
		String imageList[] = { "TopHat.png", "Inventory.png", "door.png" };
		images.loadImages(Arrays.asList(imageList));	
	}

	/*
	 * Events
	 */
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
	
	static private Point currentMousePos;
	static public Point getCurrentPos() { return currentMousePos; }

	public static Point getCenterPoint() {
		return new Point(canvasWidth*0.5, canvasHeight*0.5);
	}

	public static void click(Point loc) throws Exception {
		if(inventory.intersectsWith(loc) && !(Main.room instanceof InventoryRoom)) {
			inventory.click();
			return;
		}
		room.click(loc);
	}


	/*
	 * Room management
	 */
	/**
	 * The {@link Room} that the Player currently is in
	 */
	static public Room room;
	/**
	 * The room that the player was in before {@link #room}
	 */
	static private Room previousRoom;
	/**
	 * 
	 * @param newRoom  becomes the new {@link #room}
	 * @throws Exception
	 */
	static public void switchRoom(Class <? extends Room> newRoom) throws Exception {
		previousRoom = room;
		room = getRoom(newRoom);
		room.onEnter();
		images.loadImages(room.getAllImages());
	}
	
	/**
	 * switch to the {@link #previousRoom}
	 * @throws Exception
	 */
	static public void goBack() throws Exception {
		switchRoom(previousRoom.getClass());
	}
	
	static private Map <Class<? extends Room>, Room> roomSet;
	static public Map <Class<? extends Room>, Room> getRoomSet() {
		if(roomSet==null) {
			roomSet = new HashMap<Class<? extends Room>, Room> ();
		}		
		return roomSet;
	}
	static public Room getRoom(Class<? extends Room> room) throws Exception {
		if(!getRoomSet().containsKey(room)) {
			//Reflection does not work on server, i.e.:  getRoomSet().put(room, (Room) GWT.create(room));
			//rooms must be registered in advance
			return null;
		}
		return getRoomSet().get(room);
	}
	static public void registerRoom(Class<? extends Room> roomClass, Room room) {
		getRoomSet().put(roomClass, room);
	}
	
	/**
	 *  A Runnable for {@link Main#switchRoom}
	 *
	 */
	public static class SwitchRoom implements Runnable {
		Class<? extends Room> destination;
		public SwitchRoom(Class<? extends Room> destination) {
			this.destination = destination;
		}
		@Override
		public void run() {
			try {
				switchRoom(destination);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Draw
	 */
	public void draw() {
		if(player == null || room == null) return; 
		
		context.setFillStyle(redrawColor);
	    context.fillRect(0, 0, canvasWidth, canvasHeight);
	    room.draw(context);
		player.draw(context);
		if (!(Main.room instanceof InventoryRoom)) {
			inventory.draw(context);
		}
	}

}
