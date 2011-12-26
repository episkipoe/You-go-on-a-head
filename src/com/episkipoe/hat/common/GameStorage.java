package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.player.MovePlayer;
import com.episkipoe.hat.rooms.CityMapRoom;
import com.episkipoe.hat.rooms.MagicRoom;
import com.google.gwt.storage.client.Storage;

public class GameStorage {
	static public void saveGame() {
		final Storage localStorage = Storage.getLocalStorageIfSupported();
		if(localStorage == null) return ;
		localStorage.setItem("player_filename", Main.player.getFilename());
	}
	
	static public boolean loadGame() throws Exception {
		final Storage localStorage = Storage.getLocalStorageIfSupported();
		if(localStorage == null) return false;
		String playerFilename = localStorage.getItem("player_filename");
		if(playerFilename == null) return false;
	
		Main.player.setFilename(playerFilename);
		Main.setMouseMode(new MovePlayer());
		
		Main.inventory = new InventoryImage();
		Main.switchRoom(CityMapRoom.class);	
		Main.getRoom(MagicRoom.class).setBackground("MagicRoom.png");
		return true;
	}
	
	static public void newGame() throws Exception {
		Main.inventory = new InventoryImage();
		Main.switchRoom(MagicRoom.class);
	}
		
}
