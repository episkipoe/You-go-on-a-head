package com.episkipoe.hat.rooms.missouri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.Drawable;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;

public class FireRoom extends Room {
	Timer timer;
	int turns=0;
	int reward;
	int penalty;
	int spreadChance=40;
	int secondFireChance=30;
	int secondsToBurn=4;
	int firesExtinguished=0;
	int burns=0;
	List<String> backgrounds;
	public FireRoom() { 
		backgrounds = new ArrayList<String>();
		backgrounds.add("Tornado-OK.JPG");
		backgrounds.add("Tornado-Path.JPG");
		backgrounds.add("Nauvoo-LeeRoom.JPG");
	    timer = new Timer() {
			@Override
			public void run() {
				increaseDifficulty();
				addFire();
				spreadFire();
				turns++;
			}
	    };
	    
	    addExit(CityMapRoom.class);
	}
	
	@Override
	public Collection<String> getRequiredImages() { 
		List<String> imgs = new ArrayList<String>();
		imgs.addAll(backgrounds);
		imgs.add("fire.gif");
		return imgs; 
	}
	
	private void increaseDifficulty() {
		if(turns==0) {
			reward=5;
			penalty=50;
			spreadChance=40;
			firesExtinguished=0;
			burns=0;		
		} else if(turns==10) {
			spreadChance=60;
		} else if(turns==15) {
			secondsToBurn--;
		} else if(turns==20) {
		    timer.scheduleRepeating(3000);		
		} else if(turns==25) {
			spreadChance=70;
		} else if(turns==30) {
		    timer.scheduleRepeating(2000);		
			secondsToBurn--;
		} else if(turns==35) {
			secondFireChance=50;
		} else if(turns==40) {
		    timer.scheduleRepeating(1000);		
			secondsToBurn--;
		} else if(turns==60) {
			secondFireChance=80;
		}
	}

	private void addFire() {
		addDrawable(new Fire());
		if(secondFireChance<Random.nextInt(100)) {
			addDrawable(new Fire());
		}
		if(turns>50) {
			addDrawable(new Fire());
		}
	}
	private void spreadFire() {
		for(Drawable d: getDrawables()) {
			if(d instanceof Fire) {
				if(spreadChance<Random.nextInt(100)) {
					((Fire) d).spread();
				}
			}
		}	
	}
	
	@Override
	public void postDraw(Context2d context) {
		String msg[] = {firesExtinguished + " fires extinguished", burns+ " burns", "$"+Main.player.getMoney()}; 
		TextUtils.drawWhiteText(context, Arrays.asList(msg), new Point(10, 10));
		for(Drawable d: getDrawables()) {
			if(d instanceof Fire) {	
				if(((Fire)d).extinguished) {
					removeDrawable(d);
				}
			}
		}
	}
	
	private class Fire extends ImageDrawable {
		public boolean extinguished=false;
		int burnTime=0;
		private Fire() { 
			setFilename("fire.gif");
			int x = Random.nextInt(Main.canvasWidth);
			int y = Random.nextInt(Main.canvasHeight);
			setLocation(new Point(x,y));
		}
		
		@Override
		public void postDraw(Context2d context) {
			burnTime++;
			if(burnTime==Main.secondsToFrames(secondsToBurn)) {
				extinguished=true;
				spread();
				burns++;
				Main.player.spendMoney(penalty);
			}
		}
		public void spread() { 
			Fire child = new Fire();
			Point myLocation=getLocation();
			ImageElement img = getImageElement();
			if(img==null) return;
			float x,y;
			if(Random.nextBoolean()) {
				x=myLocation.x+Random.nextInt(img.getWidth());
			} else {
				x=myLocation.x-Random.nextInt(img.getWidth());
			}
			if(Random.nextBoolean()) {
				y=myLocation.y+Random.nextInt(img.getHeight());
			} else {
				y=myLocation.y-Random.nextInt(img.getHeight());
			}
			if(x<0) x = 0;
			float right = x+img.getWidth();
			if(right>Main.canvasWidth) {
				x = Main.canvasWidth-img.getWidth();
			}
			if(y<0) y = 0;
			float top = y+img.getHeight();
			if(top>Main.canvasHeight) {
				y = Main.canvasHeight-img.getHeight();
			}
			child.setLocation(new Point(x,y));
			addDrawable(child);
		}
		public void click() {
			extinguished=true;
			firesExtinguished++;
			Main.player.addMoney(reward);
		}
	}

	int bgIdx=0;
	private void setNextBackground() {
		setBackground(backgrounds.get(bgIdx));
		bgIdx++;
		if(bgIdx>=backgrounds.size()) {
			bgIdx=0;
		}
	}
	
	@Override
	public void onEnter() {
		setNextBackground();
	    timer.scheduleRepeating(4000);		
	    turns=0;
	    Main.inventory.pickup("FireHat.png", new Hats());
	    
		String msg[] = {"Good to see ya, chief.", "Now help us fight some fires!"};
		TextUtils.growl(Arrays.asList(msg));
	}
	@Override
	public void onExit() {
		timer.cancel();
		clearDrawables();
	}
	
	public boolean showHud() { return false; }
}
