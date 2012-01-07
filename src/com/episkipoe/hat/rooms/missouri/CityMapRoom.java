package com.episkipoe.hat.rooms.missouri;

import java.util.Arrays;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.magic.MagicLivingRoom;
import com.episkipoe.hat.rooms.maps.NorthAmerica;
import com.episkipoe.hat.rooms.park.FaceParkRoom;
import com.episkipoe.hat.rooms.party.PartyStore;

public class CityMapRoom extends Room {
	public CityMapRoom() throws Exception { 
		setBackground("CityMap.png");
		addDrawable(new Door(new Point(200,180), MagicLivingRoom.class, "MagicHouse.png"));
		addDrawable(new Door(new Point(600,100), FaceParkRoom.class, "Park.png"));
		addDrawable(new Door(new Point(200,400), PartyStore.class, "PartyCity.png"));
		addDrawable(new FireStation());
		
		addDrawable(new Door(new Point(600, 500), NorthAmerica.class, "Airport.png"));
	}
	
	private class FireStation extends ImageDrawable {
		private FireStation() { 
			setLocation(new Point(635, 335));	
			setFilename("FireStation.png");
		}
		@Override
		public void click() throws Exception {
			Main.inventory.pickup("FireHat.png", new Hats());
			String msg[] = {"Nice to see ya, chief.", "Now get out there and fight some fires!"};
			TextUtils.growl(Arrays.asList(msg));
		} 
		
	}
}
