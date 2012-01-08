package com.episkipoe.hat.rooms.australia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Oceania;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

public class MagpieRoom extends Room {
	private Timer timer;
	private List<Person> people=new ArrayList<Person>();
	private List<Magpie> magpies=new ArrayList<Magpie>();
	
	public Collection<String> getRequiredImages() { 
		String imgs[] = {"StickMan.png", "DeadMan.png", "magpie-left.png", "magpie-right.png"};
		return Arrays.asList(imgs);
	}
	
	private int magpieSpeed;
	private int personSpeed;
	private int reward;
	private int penalty;
	private int personChance=80;
	private int magpieChance=60;	
	private int secondMagpieChance=40;	
	private int survivors=0;
	private int casualties=0;
	
	private int turns=0;
	public MagpieRoom() { 
	    timer = new Timer() {
	    	@Override public void run() {
	    		increaseDifficulty();
	    		addCharacters();
				turns++;
	    	}
	    };
	    addExit(Oceania.class);
	}
	
	private void increaseDifficulty() {
		if(turns==0) {
			survivors=casualties=0;
			magpieSpeed=5;
			personSpeed=4;
			reward=4;
			penalty=15;	
		}
		if(turns==5) {
			magpieSpeed++;
		} else if(turns==10) { 
			personSpeed++;
		} else if(turns==15) { 
			magpieChance=65;
			reward=8;
		} else if(turns==20) {
		    timer.scheduleRepeating(4000);		
		} else if(turns==25) {
			magpieChance=75;
			reward=10;
		} else if(turns==30) {
		    timer.scheduleRepeating(2500);		
		    penalty=15;
		} else if(turns==40) {
			magpieChance=80;
		} else if(turns==50) {
			reward=12;
		}		
	}
	
	private void addCharacters() {
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
		if(Random.nextInt(100)<secondMagpieChance) {
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

	@Override
	public void postDraw(Context2d context) {
		String msg[] = {survivors + " people saved", casualties + " casualties", "$"+Main.player.getMoney()}; 
		TextUtils.drawWhiteText(context, Arrays.asList(msg), new Point(10, 10));
		List<Person> peopleToRemove = new ArrayList<Person>();
		List<Magpie> magpiesToRemove = new ArrayList<Magpie>();
		for(Person p : people) {
			if(p.getLocation().x > Main.canvasWidth || p.getLocation().x < 0) {
				p.survive();
				peopleToRemove.add(p);
			}
			for(Magpie m : magpies) {
				if(p.intersectsWith(m) && !p.intersectsWith(Main.player)) {	
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
	
		public void survive() {
			removeDrawable(this);
			Main.player.addMoney(reward);
			survivors++;
		}
		public void kill() {
			if(!alive) return ;
			setFilename("DeadMan.png");
			alive=false;
			Main.player.spendMoney(penalty);
			casualties++;
		}

		@Override
		public void click() throws Exception { }
		
		@Override
		public void postDraw(Context2d context) {
			if(!alive) return;
			if(moveRight) getLocation().x+=personSpeed;
			else getLocation().x-=personSpeed;
		}
	}
	
	private class Magpie extends ImageDrawable {
		private boolean moveUp;
		private boolean moveRight;
		public Magpie() { 
			moveUp=Random.nextBoolean();
			moveRight=Random.nextBoolean();
			Point location = new Point();
			location.x = Random.nextInt(Main.canvasWidth);
			if(moveUp) {
				location.y = Main.canvasHeight;
			} else {
				location.y = 0;
			}
			if(moveRight) {
				setFilename("magpie-right.png");
			} else {
				setFilename("magpie-left.png");
			}
			setLocation(location);
		}
		@Override
		public void click() throws Exception { 
			moveUp=!moveUp;
		}	
		
		@Override
		public void postDraw(Context2d context) {
			if(moveRight) getLocation().x+=magpieSpeed;
			else getLocation().x-=magpieSpeed;
			
			if(moveUp) getLocation().y-=magpieSpeed;
			else getLocation().y+=magpieSpeed;
		}
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
		clearDrawables();
	}
	
	public boolean showHud() { return false; }
}
