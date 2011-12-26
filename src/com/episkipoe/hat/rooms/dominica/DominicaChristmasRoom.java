package com.episkipoe.hat.rooms.dominica;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.InventoryImage;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.CityMapRoom;
import com.episkipoe.hat.rooms.MagicRoom;
import com.episkipoe.hat.rooms.Room;

public class DominicaChristmasRoom extends Room {
	public DominicaChristmasRoom() {
		setBackground("Dominica-Christmas.png");
		BackgroundClickable hat = new BackgroundClickable(new Point(336,300), new Point(400,372));
		hat.setAction(new InventoryImage.Pickup("SantaHat.png", "hats"));
		addClickable(hat);
		
		addDrawable(new Door(new Point(560, 500), CityMapRoom.class));
	}
	public List<String> getRequiredImages() { 
		return Arrays.asList("SantaHat.png"); 
	}
}
