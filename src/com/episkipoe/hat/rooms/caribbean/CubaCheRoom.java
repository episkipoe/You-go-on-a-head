package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;

public class CubaCheRoom extends Room {
	public CubaCheRoom() { 
		setBackground("Cuba-HastaLaVictoria.png");
	
		BackgroundClickable che = new BackgroundClickable(new Point(435, 175), new Point(587, 286));
		che.setAction(new PickupChe());
		addDrawable(che);
		
		addRightDoor(CubaSoldierRoom.class);	
	}
	private class PickupChe implements Runnable {
		@Override
		public void run() {
			Main.inventory.pickup("CheBeret.png", new Hats());
			Main.switchRoom(CubaButtonRoom.class);
		}
	}
	
	public List<String> getRequiredImages() { 
		return Arrays.asList("CheBeret.png"); 
	}	
}
