package com.episkipoe.hat.rooms.caribbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.Door;
import com.episkipoe.hat.common.Point;
import com.episkipoe.hat.common.draw.TextUtils;
import com.episkipoe.hat.rooms.Room;

public class PlunderinRoom extends Room {
	int ship=-1;
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
		ship++;
		if(ship>=ships.size()) {
			Main.goBack();
			String msg[] = {"Sorry.  There's nothing left to plunder.", "You're 200 years too late"};
			TextUtils.growl(Arrays.asList(msg));
			return;
		}
		Main.player.addMoney(100+50*ship);
		setBackground(ships.get(ship));
	}

}
