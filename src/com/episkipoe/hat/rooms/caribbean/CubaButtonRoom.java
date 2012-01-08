package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.rooms.Room;

public class CubaButtonRoom extends Room {
	public CubaButtonRoom() { 
		setBackground("CheButtonPush.png");
		addExit(CubaCheRoom.class);
	}

}
