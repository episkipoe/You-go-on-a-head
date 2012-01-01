package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.caribbean.CaribbeanRoom;
import com.episkipoe.hat.rooms.northpole.NorthPole;
import com.episkipoe.hat.rooms.party.RioRoom;

public class NorthAmericaRoom extends Room {
	public NorthAmericaRoom () {
		setBackground("NorthAmerica.png");
		
		BackgroundClickable northPole = new BackgroundClickable(new Point(514, 4), new Point(630, 47));
		northPole.setAction(NorthPole.getGoToNorthPole());
		addDrawable(northPole);		
		
		BackgroundClickable vegas = new BackgroundClickable(new Point(295, 295), new Point(330, 330));
		vegas.setAction(RioRoom.getGoToVegas());
		addDrawable(vegas);
		
		BackgroundClickable caribbean = new BackgroundClickable(new Point(435, 409), new Point(535, 460));
		caribbean.setAction(new SwitchRoom(CaribbeanRoom.class));
		addDrawable(caribbean);
		
		BackgroundClickable home = new BackgroundClickable(new Point(412, 300), new Point(445, 334));
		home.setAction(new SwitchRoom(CityMapRoom.class));
		addDrawable(home);
	}
}
