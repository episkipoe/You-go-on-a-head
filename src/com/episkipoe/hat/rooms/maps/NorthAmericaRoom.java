package com.episkipoe.hat.rooms.maps;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.caribbean.CaribbeanRoom;
import com.episkipoe.hat.rooms.mexico.MariachiRoom;
import com.episkipoe.hat.rooms.northpole.NorthPole;
import com.episkipoe.hat.rooms.ohio.Columbus;
import com.episkipoe.hat.rooms.party.MardiGras;
import com.episkipoe.hat.rooms.party.RioRoom;

public class NorthAmericaRoom extends Room {
	public NorthAmericaRoom () {
		setBackground("NorthAmerica.png");
		
		BackgroundClickable northPole = new BackgroundClickable(new Point(514, 4), new Point(630, 47));
		northPole.setAction(NorthPole.getGoToNorthPole());
		addDrawable(northPole);		
		
		BackgroundClickable mardiGras = new BackgroundClickable(new Point(397, 359), new Point(414, 377));
		mardiGras.setAction(MardiGras.getGoToMardiGras());
		addDrawable(mardiGras);		
		
		BackgroundClickable vegas = new BackgroundClickable(new Point(295, 295), new Point(330, 330));
		vegas.setAction(RioRoom.getGoToVegas());
		addDrawable(vegas);
		
		BackgroundClickable mexico = new BackgroundClickable(new Point(295, 375), new Point(364, 429));
		mexico.setAction(new SwitchRoom(MariachiRoom.class));
		addDrawable(mexico);		
		
		BackgroundClickable caribbean = new BackgroundClickable(new Point(435, 409), new Point(535, 460));
		caribbean.setAction(new SwitchRoom(CaribbeanRoom.class));
		addDrawable(caribbean);
		
		BackgroundClickable missouri = new BackgroundClickable(new Point(414, 326), new Point(437, 308));
		missouri.setAction(new SwitchRoom(CityMapRoom.class));
		addDrawable(missouri);
		
		BackgroundClickable ohio = new BackgroundClickable(new Point(449, 300), new Point(459, 334));
		ohio.setAction(new SwitchRoom(Columbus.class));
		addDrawable(ohio);
	}
}
