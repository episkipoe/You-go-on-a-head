package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundDoor;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.europe.Paris;
import com.episkipoe.hat.rooms.europe.Valhalla;

public class Europe extends Room {
	public Europe() { 
		setBackground("Europe.png");
		addRightDoor(Oceania.class);
		addExit(new Door(new Point(80, 400), NorthAmerica.class, "LeftArrow.png"));	
		addDrawable(new BackgroundDoor(new Point(65, 575), new Point(305, 600), Africa.class));
		
		addDrawable(new BackgroundDoor(new Point(250, 200), new Point(300, 241), Valhalla.class));
		addDrawable(new BackgroundDoor(new Point(195, 387), new Point(220, 414), Paris.class));
	}
}
