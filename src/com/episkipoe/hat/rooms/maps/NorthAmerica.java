package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundDoor;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.caribbean.CaribbeanRoom;
import com.episkipoe.hat.rooms.mexico.MariachiRoom;
import com.episkipoe.hat.rooms.missouri.*;
import com.episkipoe.hat.rooms.northpole.NorthPole;
import com.episkipoe.hat.rooms.ohio.Columbus;
import com.episkipoe.hat.rooms.party.*;

public class NorthAmerica extends Room {
	public NorthAmerica () {
		setBackground("NorthAmerica.png");
		addLeftDoor(Oceania.class);
		addRightDoor(Europe.class);
		
		addDrawable(new BackgroundDoor(new Point(514, 4), new Point(630, 47), NorthPole.getGoToNorthPole()));
		addDrawable(new BackgroundDoor(new Point(397, 359), new Point(414, 377), MardiGras.getGoToMardiGras()));
		addDrawable(new BackgroundDoor(new Point(295, 295), new Point(330, 330), RioRoom.getGoToVegas()));
		addDrawable(new BackgroundDoor(new Point(295, 375), new Point(364, 429), MariachiRoom.class));
		addDrawable(new BackgroundDoor(new Point(435, 409), new Point(535, 460), CaribbeanRoom.class));
		addDrawable(new BackgroundDoor(new Point(414, 326), new Point(437, 308), CityMapRoom.class));
		addDrawable(new BackgroundDoor(new Point(449, 300), new Point(459, 334), Columbus.class));
	}
}
