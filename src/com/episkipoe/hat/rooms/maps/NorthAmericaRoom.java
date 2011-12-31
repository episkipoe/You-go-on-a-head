package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.dominica.DominicaChristmasRoom;
import com.episkipoe.hat.rooms.party.RioRoom;

public class NorthAmericaRoom extends Room {
	public NorthAmericaRoom () {
		setBackground("NorthAmerica.png");
		
		BackgroundClickable vegas = new BackgroundClickable(new Point(295, 295), new Point(330, 330));
		vegas.setAction(new Main.SwitchRoom(RioRoom.class));
		addClickable(vegas);
		
		BackgroundClickable dominica = new BackgroundClickable(new Point(435, 409), new Point(535, 545));
		dominica.setAction(new Main.SwitchRoom(DominicaChristmasRoom.class));
		addClickable(dominica);
		
		BackgroundClickable home = new BackgroundClickable(new Point(412, 300), new Point(445, 334));
		home.setAction(new Main.SwitchRoom(CityMapRoom.class));
		addClickable(home);
	}
}
