package com.episkipoe.hat.rooms.magic;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Collectables;
import com.episkipoe.hat.rooms.Room;

public class MagicKitchen extends Room {
	public MagicKitchen() {
	}

	public List<String> getRequiredImages() { 
		String images[] = {"MagicHatBeer.png", "Kitchen.png", "Kitchen-WithBeer.png" };
		return Arrays.asList(images); 
	}
	
	protected void onLoad() {
		clearDrawables();
		addDrawable(new Door(new Point(60, 500), MagicLivingRoom.class, "LeftArrow.png"));
		if(Main.inventory.contains("MagicHatBeer.png")) {
			setBackground("Kitchen.png");
		} else {
			setBackground("Kitchen-WithBeer.png");
		
			BackgroundClickable beer = new BackgroundClickable(new Point(177,260), new Point(250,350));
			beer.setAction(new PickupBeer());
			addDrawable(beer);
		}
	}
	
	private class PickupBeer implements Runnable {
		@Override
		public void run() {
			clearDrawables();
			addDrawable(new Door(new Point(60, 500), MagicLivingRoom.class, "LeftArrow.png"));
			setBackground("Kitchen.png");
			Main.inventory.pickup("MagicHatBeer.png", new Collectables());
			Main.room.addDrawable(new Dialog(new DialogElement(Arrays.asList("Now I've got a nice six-pack"), Main.getCenterPoint(), 50)));
		}
	}

}
