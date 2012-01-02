package com.episkipoe.hat.rooms.party;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.SwitchRoom;

public class MardiGras extends SlideshowRoom {
	public MardiGras() { 
		addSlide("MardiGras-STL0.png");
	}
	@Override
	protected Class<? extends Room> getExitRoom() {
		return NorthAmericaRoom.class;
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
