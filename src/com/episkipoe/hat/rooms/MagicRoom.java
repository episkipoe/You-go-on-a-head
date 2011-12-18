package com.episkipoe.hat.rooms;

import com.episkipoe.hat.common.Magician;
import com.episkipoe.hat.common.Point;


public class MagicRoom extends Room {
	Magician magician;
	public MagicRoom() { 
		magician = new Magician();
		magician.setLocation(new Point(80, 80));
		addDrawable(magician);
	}
	
	@Override
	protected String getBackground() { return "MagicRoom.png"; }

}
