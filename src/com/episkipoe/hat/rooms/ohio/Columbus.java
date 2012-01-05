package com.episkipoe.hat.rooms.ohio;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;

public class Columbus extends Room {
	public Columbus() {
		setBackground("Columbus0.jpg");
		addExit(NorthAmericaRoom.class);
	}
}
