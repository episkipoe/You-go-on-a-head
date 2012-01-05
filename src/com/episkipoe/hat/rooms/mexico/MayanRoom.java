package com.episkipoe.hat.rooms.mexico;

import com.episkipoe.hat.rooms.Room;

public class MayanRoom extends Room {
	public MayanRoom() { 
		setBackground("Mayan.jpg");
		addLeftDoor(MariachiRoom.class);
	}
}
