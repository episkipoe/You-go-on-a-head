package com.episkipoe.hat.rooms.caribbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.missouri.Hospital;

public class PlunderinRoom extends Room {
	int ship=0;
	List<String> ships=new ArrayList<String>();
	public PlunderinRoom() { 
		ships.add("RiverShip.png");
		ships.add("CannonAndCruiseShip.png");
		ships.add("BigBoat.png");
		addDrawable(new Door(new Point(560, 500), Tortuga.class));	
	}
	@Override
	public List<String> getRequiredImages() { 
		return ships;
	}
	
	public void onEnter() {
		if(ship>=ships.size()) {
			Main.goBack();
			String msg[] = {"Sorry.  There's nothing left to plunder.", "You're 200 years too late"};
			TextUtils.growl(Arrays.asList(msg));
			return;
		}
		if(Main.player.successfulPirate()) {
			ship++;
			Main.player.addMoney(1000+500*ship);
			setBackground(ships.get(ship));
			String msg[] = {"That was some fine plunderin'", "Here is your share of the booty"};
			TextUtils.growl(Arrays.asList(msg));	
		} else {
			Main.switchRoom(Hospital.class);
			String msg[] = {"Oh Noes!", "You were wounded in the scuffle"};
			TextUtils.growl(Arrays.asList(msg));
		}
	}

}
