package com.episkipoe.hat.rooms.mexico;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;

public class MariachiRoom extends Room {
	public MariachiRoom() { 
		setBackground("Mariachi.jpg");

		BackgroundClickable sombrero = new BackgroundClickable(new Point(236, 233), new Point(322, 273));
		sombrero.setAction(new Inventory.Pickup("sombrero.png", new Hats()));
		addDrawable(sombrero);	
		
		addRightDoor(MayanRoom.class);
		addExit(NorthAmericaRoom.class);
	}
	
	@Override
	public List<String> getRequiredImages() { 
		return Arrays.asList("sombrero.png");
	}	
}
