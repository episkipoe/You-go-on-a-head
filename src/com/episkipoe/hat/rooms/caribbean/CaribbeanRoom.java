package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.maps.NorthAmerica;

public class CaribbeanRoom extends Room {
	public CaribbeanRoom() { 
		setBackground("Caribbean.png");
		
		BackgroundClickable northAmerica = new BackgroundClickable(new Point(744, 111), new Point(767, 134));
		northAmerica.setAction(new SwitchRoom(NorthAmerica.class));
		addDrawable(northAmerica);		
		
		BackgroundClickable cuba = new BackgroundClickable(new Point(62, 130), new Point(330, 238));
		cuba.setAction(new SwitchRoom(CubaBusRoom.class));
		addDrawable(cuba);		
		
		BackgroundClickable dominica = new BackgroundClickable(new Point(672, 365), new Point(740, 388));
		dominica.setAction(new SwitchRoom(DominicaRoom.class));
		addDrawable(dominica);	
		
		BackgroundClickable tortuga = new BackgroundClickable(new Point(369, 222), new Point(388, 240));
		tortuga.setAction(Tortuga.getGoToTortuga());
		addDrawable(tortuga);	
	}
}
