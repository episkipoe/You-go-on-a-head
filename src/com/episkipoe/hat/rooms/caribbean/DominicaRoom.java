package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.NorthAmerica;

public class DominicaRoom extends Room {
	public DominicaRoom() { 
		setBackground("Dominica.png");
		
		BackgroundClickable downtown = new BackgroundClickable(new Point(554, 170), new Point(614, 239));
		downtown.setAction(new SwitchRoom(DominicaChristmasRoom.class));
		addDrawable(downtown);
		
		BackgroundClickable diveShop = new BackgroundClickable(new Point(664, 195), new Point(726, 248));
		diveShop.setAction(new SwitchRoom(DominicaDiveShop.class));
		addDrawable(diveShop);
		
		addDrawable(new Door(new Point(560, 500), NorthAmerica.class));
	}
}
