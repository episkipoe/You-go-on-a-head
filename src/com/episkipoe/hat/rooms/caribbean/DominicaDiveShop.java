package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;

public class DominicaDiveShop extends Room {
	public DominicaDiveShop() { 
		setBackground("DiveShop.png");
		
		BackgroundClickable hat = new BackgroundClickable(new Point(160,223), new Point(277,345));
		hat.setAction(new Inventory.Pickup("Scuba.png", new Hats()));
		addDrawable(hat);
	
		addDrawable(new Door(new Point(560, 500), DominicaRoom.class));		
	}
	
	public void addDiveButton() {
		BackgroundClickable dive = new BackgroundClickable(new Point(543, 397), new Point(650, 452));
		dive.setAction(new SwitchRoom(Underwater.class));
		addDrawable(dive);	
	}
}
