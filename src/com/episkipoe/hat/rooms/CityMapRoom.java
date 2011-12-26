package com.episkipoe.hat.rooms;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.dominica.DominicaChristmasRoom;

public class CityMapRoom extends Room {
	public CityMapRoom() throws Exception { 
		addDrawable(new Door(new Point(300,300), MagicRoom.class, "MagicHouse.png"));
		addDrawable(new Door(new Point(400, 500), DominicaChristmasRoom.class, "Airport.png"));
	}
}
