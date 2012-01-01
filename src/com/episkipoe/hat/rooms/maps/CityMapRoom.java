package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.magic.MagicLivingRoom;
import com.episkipoe.hat.rooms.park.FaceParkRoom;
import com.episkipoe.hat.rooms.party.PartyStore;

public class CityMapRoom extends Room {
	public CityMapRoom() throws Exception { 
		setBackground("CityMap.png");
		addDrawable(new Door(new Point(200,180), MagicLivingRoom.class, "MagicHouse.png"));
		addDrawable(new Door(new Point(600,100), FaceParkRoom.class, "Park.png"));
		addDrawable(new Door(new Point(200,400), PartyStore.class, "PartyCity.png"));
		
		addDrawable(new Door(new Point(600, 500), NorthAmericaRoom.class, "Airport.png"));
	}
}
