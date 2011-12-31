package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.magic.MagicLivingRoom;
import com.episkipoe.hat.rooms.park.FaceParkRoom;

public class CityMapRoom extends Room {
	public CityMapRoom() throws Exception { 
		addDrawable(new Door(new Point(300,300), MagicLivingRoom.class, "MagicHouse.png"));
		addDrawable(new Door(new Point(600,100), FaceParkRoom.class, "Park.png"));
		
		addDrawable(new Door(new Point(400, 500), NorthAmericaRoom.class, "Airport.png"));
	}
}
