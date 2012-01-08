package com.episkipoe.hat.rooms.australia;

import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.interact.BackgroundAction;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.Oceania;

public class Tazmania extends Room {
	public Tazmania() { 
		setBackground("Australia-Skull.jpg");
		addExit(Oceania.class);
		
		addDrawable(new BackgroundAction(new Point(480,0), new Point(800, 168), new Inventory.Pickup("AdventureHat.png", new Hats())));
	}
}
