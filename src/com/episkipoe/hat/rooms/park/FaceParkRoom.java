package com.episkipoe.hat.rooms.park;

import java.util.Arrays;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.CityMapRoom;

public class FaceParkRoom extends Room {
	public FaceParkRoom () {
		setBackground("FacePark.png");
		
		addDrawable(new Door(new Point(560, 500), CityMapRoom.class));
		
		String msg[] = {"I \"knows/nose\" a secret.",  "Warm me up and I'll share it with you"};
		addDrawable(new DialogElement(Arrays.asList(msg), new Point(418, 312)));

		BackgroundClickable steve = new BackgroundClickable(new Point(372, 303), new Point(394, 324));
		steve.setAction(SquidHeadRoom.getGoToSquidHeadRoom());
		addDrawable(steve);
	}
	
}
