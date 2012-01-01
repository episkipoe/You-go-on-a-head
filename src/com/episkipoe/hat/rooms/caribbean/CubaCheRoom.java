package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;

public class CubaCheRoom extends Room {
	public CubaCheRoom() { 
		setBackground("Cuba-HastaLaVictoria.png");
	
		BackgroundClickable che = new BackgroundClickable(new Point(435, 175), new Point(587, 286));
		che.setAction(new Inventory.Pickup("CheBeret.png", new Hats()));
		addDrawable(che);
		
		addDrawable(new Door(new Point(630, 500), CubaSoldierRoom.class, "RightArrow.png"));	
	}
	
	public List<String> getRequiredImages() { 
		return Arrays.asList("CheBeret.png"); 
	}	
}
