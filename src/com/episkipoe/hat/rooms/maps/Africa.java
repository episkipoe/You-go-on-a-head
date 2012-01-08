package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundAction;
import com.episkipoe.hat.common.interact.BackgroundDoor;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.africa.Uganda;

public class Africa extends Room {
	public Africa() { 
		setBackground("Africa.png");
		addDrawable(new BackgroundDoor(new Point(69, 326), new Point(100, 365), Europe.class));	
		addLeftDoor(NorthAmerica.class);
		addRightDoor(Oceania.class);	
		
		addDrawable(new BackgroundDoor(new Point(406, 300), new Point(438, 329), Uganda.getGoToUganda()));	
		addDrawable(new BackgroundAction(new Point(133,85), new Point(252, 111), new Inventory.Pickup("AfricanHat.png", new Hats())));
	}
}
