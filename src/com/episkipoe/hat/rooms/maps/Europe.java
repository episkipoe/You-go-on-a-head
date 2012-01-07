package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundDoor;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.vikings.Valhalla;

public class Europe extends Room {
	public Europe() { 
		setBackground("Europe.png");
		addLeftDoor(NorthAmerica.class);
		addRightDoor(Oceania.class);
		
		addDrawable(new BackgroundDoor(new Point(250, 200), new Point(300, 241), Valhalla.class));
	}
}
