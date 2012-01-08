package com.episkipoe.hat.rooms.europe;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Europe;

public class Valhalla extends Room {
	public Valhalla() { 
		setBackground("Valhalla.jpg");
		addExit(Europe.class);
	}
}
