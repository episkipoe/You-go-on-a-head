package com.episkipoe.hat.rooms.magic;

import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.dialog.Dialog;
import com.episkipoe.hat.common.dialog.DialogElement;
import com.episkipoe.hat.common.interact.BackgroundClickable;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.SwitchRoom;
import com.episkipoe.hat.rooms.missouri.CityMapRoom;

public class MagicLivingRoom extends Room {
	public MagicLivingRoom() throws Exception {
		setBackground("MagicHouse-LivingRoom.png");
		BackgroundClickable exit = new BackgroundClickable(new Point(83,200), new Point(193, 400));
		exit.setAction(new SwitchRoom(CityMapRoom.class));
		addDrawable(exit);

		addLeftDoor(MagicRoom.class);
		addRightDoor(MagicKitchen.class);
		
		BackgroundClickable penguin = new BackgroundClickable(new Point(428,495), new Point(460, 540)); 
		penguin.setAction(new ClickPenguin());
		addDrawable(penguin);
		
		BackgroundClickable pirate = new BackgroundClickable(new Point(720,372), new Point(768, 411)); 
		pirate.setAction(new ClickPirateMonkey());
		addDrawable(pirate);
		
		BackgroundClickable gnome = new BackgroundClickable(new Point(287, 311), new Point(322, 371));
		gnome.setAction(new ClickGnome());
		addDrawable(gnome);
	}
	
	public List<String> getRequiredImages() { 
		return Arrays.asList("PirateHat.png"); 
	}

	
	private class ClickPenguin implements Runnable {
		@Override
		public void run() {
			addDrawable(new Dialog(new DialogElement("Ice to meet you", new Point(460,495), 40)));
		}
	}
	
	private class ClickPirateMonkey implements Runnable {
		@Override
		public void run() {
			addDrawable(new Dialog(new DialogElement("Hey buddy, let's go plunderin'", new Point(620,337), 80)));
			Main.inventory.pickup("PirateHat.png", new Hats());
		}
	}
	
	private class ClickGnome implements Runnable {
		@Override
		public void run() {	
			addDrawable(new Dialog(new DialogElement("Me?  I'm gnome-body special", new Point(330,323), 40)));
		}
	}

}
