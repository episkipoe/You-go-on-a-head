package com.episkipoe.hat.rooms.party;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;

public class RioRoom extends Room {
	public RioRoom() { 
		setBackground("GBF.png");
		
		addDrawable(new Door(new Point(560, 500), NorthAmericaRoom.class));
	}
}
