package com.episkipoe.hat.rooms.northpole;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.NorthAmerica;

public class NorthPole extends Room {
	public NorthPole() { 
		setBackground("NorthPole.png");
	
		BackgroundClickable warmHat = new BackgroundClickable(new Point(307, 44), new Point(366, 83));
		warmHat.setAction(new Inventory.Pickup("WarmHat.png", new Hats()));
		addDrawable(warmHat);
		
		BackgroundClickable frontDoor = new BackgroundClickable(new Point(374, 298), new Point(420, 380));
		frontDoor.setAction(new SwitchRoom(BlueTreeRoom.class));
		addDrawable(frontDoor);
		
		addDrawable(new Door(new Point(560, 500), NorthAmerica.class));
	}

	static private class NorthPoleCriterion implements Criterion {
		public NorthPoleCriterion() { }

		@Override
		public boolean valid() {
			String validHats[] = {"SantaHat.png"};
			return Main.player.wearing(Arrays.asList(validHats));
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("You are not jolly enough to visit the North Pole!");
		}
	}
	
	public static Runnable getGoToNorthPole() {
		SwitchRoom gotoNorthPole = new SwitchRoom(NorthPole.class);
		gotoNorthPole.addCriterion(new NorthPoleCriterion());
		return gotoNorthPole;
	}
}
