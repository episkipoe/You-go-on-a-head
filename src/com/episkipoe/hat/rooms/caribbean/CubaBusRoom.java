package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;

public class CubaBusRoom extends Room {
	public CubaBusRoom() { 
		setBackground("Cuba-SpecialBus.png");
		
		BackgroundClickable onBus = new BackgroundClickable(new Point(95, 303), new Point(155, 368));
		onBus.setAction(new SwitchRoom(CubaCheRoom.class));
		addDrawable(onBus);
	}
	
	public void onLoad() {
		String msg[] = { "Need a lift?", "I'll get you where you need to go." };
		getDialog().add(Arrays.asList(msg), new Point(170,319));
	}
}
