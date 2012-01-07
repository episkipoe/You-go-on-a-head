package com.episkipoe.hat.common.interact;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;

public class BackgroundDoor extends BackgroundClickable {
	public BackgroundDoor(Point first, Point second, Class<? extends Room> destination) {
		super(first, second);
		setAction(new SwitchRoom(destination));	
	}
	public BackgroundDoor(Point first, Point second, Runnable goToDestination) {
		super(first, second);
		setAction(goToDestination);
	}
}
