package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.player.MovePlayer;
import com.episkipoe.hat.rooms.InventoryRoom;
import com.episkipoe.hat.rooms.dominica.DominicaChristmasRoom;
import com.episkipoe.hat.rooms.magic.MagicLivingRoom;
import com.episkipoe.hat.rooms.magic.MagicRoom;
import com.episkipoe.hat.rooms.maps.CityMapRoom;
import com.episkipoe.hat.rooms.maps.NorthAmericaRoom;
import com.episkipoe.hat.rooms.park.FaceParkRoom;
import com.episkipoe.hat.rooms.party.RioRoom;
import com.google.gwt.storage.client.Storage;

public class GameStorage {
	static public void saveGame() {
		final Storage localStorage = Storage.getLocalStorageIfSupported();
		if(localStorage == null) return ;
		localStorage.setItem("player_filename", Main.player.getFilename());
		localStorage.setItem("player_inventory", Main.inventory.toString());
	}
	
	static public boolean loadGame() throws Exception {
		final Storage localStorage = Storage.getLocalStorageIfSupported();
		if(localStorage == null) return false;
		String playerFilename = localStorage.getItem("player_filename");
		if(playerFilename == null) return false;

		Main.player.setFilename(playerFilename);
		Main.images.loadImage(playerFilename);
		Main.setMouseMode(new MovePlayer());
		
		Main.inventory = new Inventory();
		String playerInventory = localStorage.getItem("player_inventory");
		if(playerInventory != null) { 
			Main.inventory.fromString(playerInventory);
		}
		Main.switchRoom(CityMapRoom.class);	
		Main.getRoom(MagicRoom.class).setBackground("MagicRoom.png");
		return true;
	}
	
	static public void startup() throws Exception {
    	loadRooms();
		if(!loadGame()) {
		    newGame();
		}	
	}
	
	static public void newGame() throws Exception {
		Main.inventory = new Inventory();
		Main.switchRoom(MagicRoom.class);
	}
	
	static public void loadRooms() throws Exception {
		Main.registerRoom(InventoryRoom.class, new InventoryRoom());
		Main.registerRoom(MagicRoom.class, new MagicRoom());
		Main.registerRoom(MagicLivingRoom.class, new MagicLivingRoom());
		Main.registerRoom(CityMapRoom.class, new CityMapRoom());
		Main.registerRoom(NorthAmericaRoom.class, new NorthAmericaRoom());
		Main.registerRoom(RioRoom.class, new RioRoom());
		//Dominica
		Main.registerRoom(DominicaChristmasRoom.class, new DominicaChristmasRoom());
		//Park
		Main.registerRoom(FaceParkRoom.class, new FaceParkRoom());
	}

		
}
