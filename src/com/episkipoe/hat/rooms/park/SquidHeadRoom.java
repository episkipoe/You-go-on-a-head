package com.episkipoe.hat.rooms.park;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;

public class SquidHeadRoom extends Room {
	public SquidHeadRoom() { 
		setBackground("SquidHat.jpg");
		addExit(FaceParkRoom.class);
	}
	
	static private class SquidCriterion implements Criterion {
		public SquidCriterion() { }

		@Override
		public boolean valid() {
			String validHats[] = {"FishHookHat.png"};
			return Main.player.wearing(Arrays.asList(validHats));
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("You seem to be missing something...");
		}
	}
	static public Runnable getGoToSquidHeadRoom() {
		SwitchRoom gotoSquid = new SwitchRoom(SquidHeadRoom.class);
		gotoSquid.addCriterion(new SquidCriterion());
		return gotoSquid;
	}
	
}
