package com.episkipoe.hat.rooms;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;

public class MagicLivingRoom extends Room {
	public MagicLivingRoom() throws Exception {
		setBackground("MagicHouse-LivingRoom.png");
		BackgroundClickable exit = new BackgroundClickable(new Point(83,83), new Point(193, 255));
		exit.setAction(new Main.SwitchRoom(CityMapRoom.class));
		addClickable(exit);

		addDrawable(new Door(new Point(60, 500), MagicRoom.class));
	}


}
