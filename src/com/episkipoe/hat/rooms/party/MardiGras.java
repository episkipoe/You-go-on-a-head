package com.episkipoe.hat.rooms.party;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.SwitchRoom;

public class MardiGras extends SlideshowRoom {
	public MardiGras() { 
		addSlide("MardiGras-STL0.png");
	}
	
	protected void loadSlide() { 
		switch(currentSlide) {
			case 0:
				BackgroundClickable spikeHat = new BackgroundClickable(new Point(215, 434), new Point(322, 542));
				spikeHat.setAction(new Inventory.Pickup("MardiGrasSpikeHat.png", new Hats()));
				addDrawable(spikeHat);				
				break;
		}
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
