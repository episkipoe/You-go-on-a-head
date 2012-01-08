package com.episkipoe.hat.rooms.africa;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundAction;
import com.episkipoe.hat.common.inventory.Collectables;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.Africa;

public class Uganda extends SlideshowRoom {
	public Uganda() { 
		addSlide("Africa-Uganda-Equator.JPG");
		addSlide("Africa-BigSky.JPG");
		addSlide("Africa-Toothpaste.JPG");
	}

	@Override
	public void loadSlide() {
		if(getBackground().equals("Africa-Toothpaste.JPG")) {
			addDrawable(new BackgroundAction(new Point(286, 102), new Point(456, 562), new Inventory.Pickup("Toothpaste.png", new Collectables())));
		}
	}
	
	@Override
	protected Class<? extends Room> getExitRoom() {
		return Africa.class;
	}
	static private class SafariCriterion implements Criterion {
		public SafariCriterion() { }

		@Override
		public boolean valid() {
			String validHats[] = {"AdventureHat.png", "AfricanHat.png", "CamoHat.png", "GreenLeavesHat.png"};
			return Main.player.wearing(Arrays.asList(validHats));
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("You don't look prepared for a Safari!");
		}
	}
	static public Runnable getGoToUganda() {
		SwitchRoom gotoUganda = new SwitchRoom(Uganda.class);
		gotoUganda.addCriterion(new SafariCriterion());
		return gotoUganda;
	}
}
