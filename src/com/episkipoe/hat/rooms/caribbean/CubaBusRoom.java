package com.episkipoe.hat.rooms.caribbean;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;

public class CubaBusRoom extends Room {
	public CubaBusRoom() { 
		setBackground("Cuba-SpecialBus.png");
		
		BackgroundClickable onBus = new BackgroundClickable(new Point(95, 303), new Point(155, 368));
		onBus.setAction(new Main.SwitchRoom(CubaCheRoom.class));
		addDrawable(onBus);
	}
}
