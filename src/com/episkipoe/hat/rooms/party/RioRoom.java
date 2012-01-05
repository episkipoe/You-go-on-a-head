package com.episkipoe.hat.rooms.party;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Collectables;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SlideshowRoom;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;

public class RioRoom extends SlideshowRoom {
	public RioRoom() { 
		addSlide("GBF.png");
		addSlide("VegasShopping.jpg");
		addSlide("VegasDragon.jpg");
		addSlide("RamenRoom.jpg");
	}
	
	protected void loadSlide() { 
		if(getBackground().equals("RamenRoom.jpg")) {
			BackgroundClickable rum = new BackgroundClickable(new Point(190,220), new Point(265, 380));
			rum.setAction(new Inventory.Pickup("KrackenRum.png", new Collectables()));
			addDrawable(rum);
		}
	}

	static private class VegasCriterion implements Criterion {
		public VegasCriterion() { }

		@Override
		public boolean valid() {
			String validHats[] = {"RedPartyHat.png"};
			return Main.player.wearing(Arrays.asList(validHats));
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("You can't get into the party looking like that!");
		}
	}
	static public Runnable getGoToVegas() {
		SwitchRoom gotoVegas = new SwitchRoom(RioRoom.class);
		gotoVegas.addCriterion(new VegasCriterion());
		return gotoVegas;
	}
	
	@Override
	protected Class<? extends Room> getExitRoom() {
		return NorthAmericaRoom.class;
	}
}
