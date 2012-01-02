package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;

public class GoDiveRoom extends Room {
	public GoDiveRoom() { 
		setBackground("GoDive.png");
		
		BackgroundClickable dive = new BackgroundClickable(new Point(207, 258), new Point(373,326));
		dive.setAction(getGoDiving());
		addDrawable(dive);		
		
		addDrawable(new Door(new Point(560, 500), DominicaDiveShop.class));		
	}
	
	static private class DiveCriterion implements Criterion {
		@Override
		public boolean valid() {
			String validHats[] = {"Scuba.png"};
			return Main.player.wearing(Arrays.asList(validHats));	
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("You can't go diving dressed like that.");
		}
	}
	
	private Runnable getGoDiving() {
		SwitchRoom goDiving = new SwitchRoom(Underwater.class);
		goDiving.addCriterion(new DiveCriterion());
		return goDiving;
	}
	
		
}
