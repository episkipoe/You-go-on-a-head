package com.episkipoe.hat.client;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.episkipoe.hat.common.GameStorage;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageLibrary;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.interact.MouseMode;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.common.inventory.InventoryRoom;
import com.episkipoe.hat.player.Player;
import com.episkipoe.hat.rooms.Room;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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
	static public Context2d context;
	private final CssColor redrawColor = CssColor.make("rgba(255,255,255,0.6)");
	static public final int canvasWidth = 800;
	static public final int canvasHeight = 600;
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
        
        canvas.addKeyPressHandler(keyPressHandler());
        
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
		images.loadImages(Arrays.asList(GameStorage.getCommonImages()));	
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

	public static void click(Point point) throws Exception {
		//The Inventory button is drawn at the top-left of the screen (except in the InventoryRoom)
		if(inventory.intersectsWith(point) && !(Main.room instanceof InventoryRoom)) {
			inventory.click();
			return;
		}
		room.click(point);
	}

	/**
	 * 
	 * @param point
	 * @return true if clicking at this point will cause something to happen
	 */
	public static boolean pointIsClickable(Point point) {
		if(inventory.intersectsWith(point) && !(Main.room instanceof InventoryRoom)) {
			return true;
		}
		return room.pointIsClickable(point);
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
	public static Room previousRoom;
	/**
	 * 
	 * @param newRoom  becomes the new {@link #room}
	 * @throws Exception
	 */
	static public void switchRoom(Class <? extends Room> newRoom) {
		previousRoom = room;
		if(previousRoom!=null) {
			previousRoom.getDialog().clear();
			previousRoom.onExit();
		}
		try {
			room = getRoom(newRoom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(room==null) {
			System.out.println("Room " + newRoom + " could not be loaded");
		}
		room.onEnter();
		images.loadImages(room.getAllImages());
	}
	
	/**
	 * switch to the {@link #previousRoom}
	 * @throws Exception
	 */
	static public void goBack() {
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
	
	/*
	 * Draw
	 */
	public void draw() {
		if(player == null || room == null) return; 
		
		context.setFillStyle(redrawColor);
	    context.fillRect(0, 0, canvasWidth, canvasHeight);
	    room.draw(context);
		if (room.showHud()) {
			inventory.draw(context);
			
			String money = "$"+Main.player.getMoney();
			TextUtils.drawText(context, Arrays.asList(money), new Point(0,100), "rgba(255,255,255,1)", "rgba(0,0,0,1)");
		}
		player.draw(context);
	}
	
	private KeyPressHandler keyPressHandler() {
		return new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode() == 'b') { 
					try {
						Main.goBack();
					} catch (Exception e) {
						e.printStackTrace();
					}				
				} 
			}
		};
	}

}
