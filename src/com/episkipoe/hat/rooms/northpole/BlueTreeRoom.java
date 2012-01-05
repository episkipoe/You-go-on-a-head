package com.episkipoe.hat.rooms.northpole;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;

public class BlueTreeRoom extends Room {
	public BlueTreeRoom() { 
		
	}
	public List<String> getRequiredImages() { 
		String images[] = {	"BlueTree.png", "BlueTree-Chicken.png", "ChickenMask.png"};
		return Arrays.asList(images);
	}
	
	BackgroundClickable chickenMask;
	protected void onLoad() {
		clearDrawables();
		addExit(NorthPole.class);
		if(Main.inventory.contains("ChickenMask.png")) {
			setBackground("BlueTree.png");
		} else {
			setBackground("BlueTree-Chicken.png");
		
			chickenMask = new BackgroundClickable(new Point(220,250), new Point(292,350));
			chickenMask.setAction(new PickupMask());
			addDrawable(chickenMask);
		}
	}
	
	private class PickupMask implements Runnable {
		@Override
		public void run() {
			removeDrawable(chickenMask);
			addExit(NorthPole.class);
			setBackground("BlueTree.png");
			Main.inventory.pickup("ChickenMask.png", new Hats());
			Main.room.addDrawable(new Dialog(new DialogElement(Arrays.asList("Creepy."), Main.getCenterPoint(), 50)));
		}
	}	
}
