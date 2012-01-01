package com.episkipoe.hat.rooms.party;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;

public class RioRoom extends Room {
	public RioRoom() { 
		setBackground("GBF.png");
		
		addDrawable(new Door(new Point(560, 500), NorthAmericaRoom.class));
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
			return Arrays.asList("You can't get into the party dressed like that!");
		}
	}
	static public Runnable getGoToVegas() {
		SwitchRoom gotoVegas = new SwitchRoom(RioRoom.class);
		gotoVegas.addCriterion(new VegasCriterion());
		return gotoVegas;
	}
}
