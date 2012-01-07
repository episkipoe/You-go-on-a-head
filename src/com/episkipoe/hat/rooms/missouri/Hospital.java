package com.episkipoe.hat.rooms.missouri;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.maps.NorthAmerica;

public class Hospital extends Room {
	public Hospital() { 
		setBackground("Hospital-PirateMonkey.jpg");
		addExit(NorthAmerica.class);
	}

	@Override
	public void onEnter() {
		Main.player.spendMoney(1000);
	}
}
