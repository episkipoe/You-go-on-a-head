package com.episkipoe.hat.rooms.australia;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Oceania;

public class Tazmania extends Room {
	public Tazmania() { 
		setBackground("Australia-Skull.jpg");
		addExit(Oceania.class);
	}
}
