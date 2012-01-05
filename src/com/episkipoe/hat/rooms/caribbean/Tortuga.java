package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;
import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Criterion;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;

public class Tortuga extends Room {
	public Tortuga() { 
		setBackground("TropicalPirate.png");
		String msg [] = {"Well, lad are ye ready to set sail?"};
		addDrawable(new DialogElement(Arrays.asList(msg), new Point(561, 187)));
		
		BackgroundClickable goPlunder = new BackgroundClickable(new Point(458,154), new Point(546,248));
		SwitchRoom plunder = new SwitchRoom(PlunderinRoom.class);
		plunder.addCriterion(new PlunderCriterion());
		goPlunder.setAction(plunder);
		addDrawable(goPlunder);
		
		addExit(NorthAmericaRoom.class);
	}
	
	static private class PlunderCriterion implements Criterion {
		public PlunderCriterion() { }

		@Override
		public boolean valid() {
			return Main.inventory.contains("KrackenRum.png");
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("Hey now.  You'll need to bring some rum along to go plunderin' with us.");
		}
	}
	
	static private class TortugaCriterion implements Criterion {
		public TortugaCriterion() { }

		@Override
		public boolean valid() {
			String validHats[] = {"PirateHat.png"};
			return Main.player.wearing(Arrays.asList(validHats));
		}

		@Override
		public Collection<String> getFailureMessage() {
			return Arrays.asList("Arrr matey, ye not be gettin' into Tortuga lookin' like that.");
		}
	}
	public static Runnable getGoToTortuga() {
		SwitchRoom gotoTortuga = new SwitchRoom(Tortuga.class);
		gotoTortuga.addCriterion(new TortugaCriterion());
		return gotoTortuga;
	}
}
