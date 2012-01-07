package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundDoor;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.australia.*;

public class Oceania extends Room {
	public Oceania() { 
		setBackground("Oceania.png");
		addRightDoor(NorthAmerica.class);
		addLeftDoor(Europe.class);
		
		addDrawable(new BackgroundDoor(new Point(160, 283), new Point(246, 370), NorthAustralia.class));
		addDrawable(new BackgroundDoor(new Point(143, 421), new Point(246, 477), SouthAustralia.class));
		addDrawable(new BackgroundDoor(new Point(195, 489), new Point(225, 515), Tazmania.class));
	}
}
