package com.episkipoe.hat.rooms.europe;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Europe;

public class Paris extends Room {
	public Paris() { 
		addExit(Europe.class);
	}
	public void onEnter() {
		Main.inventory.pickup("ParisBeret.png", new Hats());
	}
}
