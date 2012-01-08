package com.episkipoe.hat.rooms.party;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Collectables;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.maps.NorthAmerica;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

public class MardiGras extends SlideshowRoom {
	private Timer timer;
	public MardiGras() { 
		timer = new Timer() {
	    	@Override public void run() {
	    		addDrawable(new Beads());
	    		timer.schedule(1000+2000*Random.nextInt(4));
	    	}
		};
		addSlide("MardiGras-Float0.JPG");
		addSlide("MardiGras-AfricaFloat.JPG");
		addSlide("MardiGras-STL0.png");
	}

	@Override
	protected void loadSlide() { 
		timer.schedule(1000);
		if(getBackground().equals("MardiGras-STL0.png")) {
			String msg[] = {"What!?", "No, I cannot take off my top.", "A top is all I am!"};
			TextUtils.growl(Arrays.asList(msg));
			
			BackgroundClickable spikeHat = new BackgroundClickable(new Point(215, 434), new Point(322, 542));
			spikeHat.setAction(new Inventory.Pickup("MardiGrasSpikeHat.png", new Hats()));
			addDrawable(spikeHat);				
		}
	}
	
	@Override
	public void onExit() {
		timer.cancel();
	}
	
	private class Beads extends ImageDrawable {
		int speedX, speedY;
		int finalY;
		private Beads() { 
			int x = Random.nextInt(Main.canvasWidth);
			int y = 0;
			setLocation(new Point(x,y));
			setFilename("MardiGrasBeads.png");
			speedX=Random.nextInt(3);
			if(Random.nextBoolean()) speedX *= -1;
			speedY=Random.nextInt(8)+2;
			finalY=(int) (Random.nextInt((int)(Main.canvasHeight*0.5))+Main.canvasHeight*0.4);
		}
		
		public void postDraw(Context2d context) {
			move(speedX,speedY);
			if(getLocation().y>finalY) removeDrawable(this);
		}

		@Override
		public void click() throws Exception {
			Main.inventory.pickup("MardiGrasBeads.png", new Collectables());
			Main.player.increaseSkillLevel("Party.png", 1);
			removeDrawable(this);
		}
		
	}

	@Override
	protected Class<? extends Room> getExitRoom() {
		return NorthAmerica.class;
	}
	static private class MardiGrasCriterion implements Criterion {
		public MardiGrasCriterion() { }

		@Override
		public boolean valid() {
			String validHats[] = {"MardiGrasHat0.png", "MardiGrasSpikeHat.png"};
			return Main.player.wearing(Arrays.asList(validHats));
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("You can't get into Mardi Gras dressed like that!");
		}
	}
	static public Runnable getGoToMardiGras() {
		SwitchRoom gotoMardiGras = new SwitchRoom(MardiGras.class);
		gotoMardiGras.addCriterion(new MardiGrasCriterion());
		return gotoMardiGras;
	}	

}
