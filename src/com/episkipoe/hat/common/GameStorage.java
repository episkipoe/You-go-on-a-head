package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.common.inventory.InventoryRoom;
import com.episkipoe.hat.player.MovePlayer;
import com.episkipoe.hat.rooms.caribbean.*;
import com.episkipoe.hat.rooms.magic.*;
import com.episkipoe.hat.rooms.maps.*;
import com.episkipoe.hat.rooms.mexico.*;
import com.episkipoe.hat.rooms.northpole.*;
import com.episkipoe.hat.rooms.ohio.Columbus;
import com.episkipoe.hat.rooms.park.*;
import com.episkipoe.hat.rooms.party.*;
import com.google.gwt.storage.client.Storage;

public class GameStorage {
	static public void saveGame() {
		final Storage localStorage = Storage.getLocalStorageIfSupported();
		if(localStorage == null) return ;
		localStorage.setItem("player_filename", Main.player.getFilename());
		localStorage.setItem("player_inventory", Main.inventory.toString());
		localStorage.setItem("player_money", String.valueOf(Main.player.getMoney()));
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
		
		String playerMoney = localStorage.getItem("player_money");
		if(playerMoney != null) {
			Main.player.setMoney(Integer.valueOf(playerMoney));
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
		Main.inventory.addItem("TopHat.png", new Hats());
		Main.switchRoom(MagicRoom.class);
	}
	
	static public void loadRooms() throws Exception {
		//TODO:  Get reflection to work and eliminate this code block
		Main.registerRoom(InventoryRoom.class, new InventoryRoom());
		//maps
		Main.registerRoom(CityMapRoom.class, new CityMapRoom());
		Main.registerRoom(NorthAmericaRoom.class, new NorthAmericaRoom());
		//magic house
		Main.registerRoom(MagicRoom.class, new MagicRoom());
		Main.registerRoom(MagicKitchen.class, new MagicKitchen());
		Main.registerRoom(MagicLivingRoom.class, new MagicLivingRoom());
		//party
		Main.registerRoom(PartyStore.class, new PartyStore());
		Main.registerRoom(RioRoom.class, new RioRoom());
		Main.registerRoom(MardiGras.class, new MardiGras());
		//Caribbean
		Main.registerRoom(CaribbeanRoom.class, new CaribbeanRoom());
		Main.registerRoom(DiveShop.class, new DiveShop());
		//	Cuba
		Main.registerRoom(CubaBusRoom.class, new CubaBusRoom());
		Main.registerRoom(CubaCheRoom.class, new CubaCheRoom());
		Main.registerRoom(CubaSoldierRoom.class, new CubaSoldierRoom());
		//	Dominica
		Main.registerRoom(DominicaRoom.class, new DominicaRoom());
		Main.registerRoom(DominicaChristmasRoom.class, new DominicaChristmasRoom());
		Main.registerRoom(DominicaDiveShop.class, new DominicaDiveShop());
		Main.registerRoom(GoDiveRoom.class, new GoDiveRoom());
		Main.registerRoom(Underwater.class, new Underwater());
		Main.registerRoom(Tortuga.class, new Tortuga());
		Main.registerRoom(PlunderinRoom.class, new PlunderinRoom());
		//	Mexico
		Main.registerRoom(MariachiRoom.class, new MariachiRoom());
		Main.registerRoom(MayanRoom.class, new MayanRoom());
		//Park
		Main.registerRoom(FaceParkRoom.class, new FaceParkRoom());
		Main.registerRoom(SquidHeadRoom.class, new SquidHeadRoom());
		//Ohio
		Main.registerRoom(Columbus.class, new Columbus());
		//North Pole
		Main.registerRoom(NorthPole.class, new NorthPole());
		Main.registerRoom(BlueTreeRoom.class, new BlueTreeRoom());
		
	}

	static public String[] getCommonImages() {
		String imageList[] = { "TopHat.png", "Inventory.png", "door.png", "LeftArrow.png", "RightArrow.png" };
		return imageList;
	}
	

}
