package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Inventory;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;

public class DominicaChristmasRoom extends Room {
	public DominicaChristmasRoom() {
		setBackground("Dominica-Christmas.png");
		
		BackgroundClickable hat = new BackgroundClickable(new Point(336,300), new Point(400,372));
		hat.setAction(new Inventory.Pickup("SantaHat.png", "hats"));
		addDrawable(hat);
		
		addDrawable(new Door(new Point(560, 500), DominicaRoom.class));
	}
	public List<String> getRequiredImages() { 
		return Arrays.asList("SantaHat.png"); 
	}
}
