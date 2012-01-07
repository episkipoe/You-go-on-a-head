package com.episkipoe.hat.rooms.australia;

import java.util.ArrayList;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Oceania;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

public class MagpieRoom extends Room {
	Timer timer;
	List<Person> people=new ArrayList<Person>();
	List<Magpie> magpies=new ArrayList<Magpie>();
	
	private int magpieSpeed=8;
	private int personSpeed=5;
	
	int turns=0;
	public MagpieRoom() { 
	    timer = new Timer() {
	    	int personChance=80;
	    	int magpieChance=50;
	    	@Override public void run() {
	    		turns++;
	    		if(turns==5) {
	    			magpieSpeed++;
	    		} else if(turns==10) { 
	    			personSpeed++;
	    		} else if(turns==15) { 
	    			magpieChance=65;
	    		} else if(turns==20) {
				    timer.scheduleRepeating(4000);		
	    		} else if(turns==25) {
	    			magpieChance=75;
	    		} else if(turns==30) {
				    timer.scheduleRepeating(2500);		
	    		} else if(turns==40) {
	    			magpieChance=80;
	    		}
	    		if(Random.nextInt(100)<personChance) {
	    			Person p = new Person();
	    			people.add(p);
	    			addDrawable(p);
	    		} 
	    		if(Random.nextInt(100)<magpieChance) {
	    			Magpie m = new Magpie();
	    			magpies.add(m);
	    			addDrawable(m);
	    		}
	    		if(turns>50) {
	    			Magpie m = new Magpie();
	    			magpies.add(m);
	    			addDrawable(m);	    			
	    		}
	    	}
	    };
	    addExit(Oceania.class);
	}

	@Override
	public void postDraw(Context2d context) {
		List<Person> peopleToRemove = new ArrayList<Person>();
		List<Magpie> magpiesToRemove = new ArrayList<Magpie>();
		for(Person p : people) {
			if(p.getLocation().x > Main.canvasWidth || p.getLocation().x < 0) {
				removeDrawable(p);
				peopleToRemove.add(p);
			}
			for(Magpie m : magpies) {
				if(p.intersectsWith(m)) {	
					p.kill();
				}
			}
		}
		for(Magpie m : magpies) {
			if(m.getLocation().y > Main.canvasHeight || m.getLocation().y < 0) {
				removeDrawable(m);
				magpiesToRemove.add(m);
			}
		}
		people.removeAll(peopleToRemove);
		magpies.removeAll(magpiesToRemove);
	}
	
	@Override
	public void onEnter() {
	    timer.scheduleRepeating(5000);		
	    turns=0;
	    Main.inventory.pickup("SafetyHat.png", new Hats());
	}
	@Override
	public void onExit() {
		timer.cancel();
	}
	
	private class Person extends ImageDrawable {
		private boolean moveRight;
		private boolean alive=true;
		public Person() { 
			setFilename("StickMan.png");
			moveRight=Random.nextBoolean();
			Point location = new Point();
			location.y = (float) (Main.canvasHeight*0.5);
			if(moveRight) location.x = 0;
			else location.x = Main.canvasWidth;
			setLocation(location);
		}
		
		public void kill() {
			setFilename("DeadMan.png");
			alive=false;
		}

		@Override
		public void click() throws Exception { }
		
		@Override
		public void postDraw() {
			if(!alive) return;
			if(moveRight) getLocation().x+=personSpeed;
			else getLocation().x-=personSpeed;
		}
	}
	
	private class Magpie extends ImageDrawable {
		private boolean moveUp;
		private boolean moveRight;
		public Magpie() { 
			setFilename("magpie.png");
			moveUp=Random.nextBoolean();
			moveRight=Random.nextBoolean();
			Point location = new Point();
			location.x = Random.nextInt(Main.canvasWidth);
			if(moveUp) {
				location.y = Main.canvasHeight;
			} else {
				location.y = 0;
			}
			setLocation(location);
		}
		@Override
		public void click() throws Exception { 
			moveUp=!moveUp;
		}	
		
		@Override
		public void postDraw() {
			if(moveRight) getLocation().x+=magpieSpeed;
			else getLocation().x-=magpieSpeed;
			
			if(moveUp) getLocation().y-=magpieSpeed;
			else getLocation().y+=magpieSpeed;
		}
	}
	
	public boolean showHud() { return false; }
}
