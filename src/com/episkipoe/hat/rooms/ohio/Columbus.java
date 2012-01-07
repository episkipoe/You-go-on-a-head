package com.episkipoe.hat.rooms.ohio;

import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.NorthAmerica;

public class Columbus extends Room {
	public Columbus() {
		setBackground("Columbus0.jpg");
		addExit(NorthAmerica.class);
	}
}
