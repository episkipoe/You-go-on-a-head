package com.episkipoe.hat.rooms.australia;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundDoor;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Oceania;

public class NorthAustralia extends Room {
	public NorthAustralia() { 
		setBackground("Magpies.jpg");
		addExit(Oceania.class);
		
		addDrawable(new BackgroundDoor(new Point(15,425), new Point(170, 453), MagpieRoom.class));
	}
}
