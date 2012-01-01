package com.episkipoe.hat.rooms.park;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.CityMapRoom;

public class FaceParkRoom extends Room {
	public FaceParkRoom () {
		setBackground("FacePark.png");
		
		addDrawable(new Door(new Point(560, 500), CityMapRoom.class));
		
	}
}
