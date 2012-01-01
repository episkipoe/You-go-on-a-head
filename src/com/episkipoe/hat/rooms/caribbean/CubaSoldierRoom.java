package com.episkipoe.hat.rooms.caribbean;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Inventory;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.rooms.Room;

public class CubaSoldierRoom extends Room {
	public CubaSoldierRoom() { 
		setBackground("Cuba-SoldierDadHats.png");
		
		BackgroundClickable che = new BackgroundClickable(new Point(91, 145), new Point(153, 199));
		che.setAction(new Inventory.Pickup("CamoHat.png", "hats"));
		addDrawable(che);
		
		addDrawable(new Door(new Point(630, 500), CaribbeanRoom.class, "RightArrow.png"));	
	}
	
	public List<String> getRequiredImages() { 
		return Arrays.asList("CamoHat.png"); 
	}		
}
