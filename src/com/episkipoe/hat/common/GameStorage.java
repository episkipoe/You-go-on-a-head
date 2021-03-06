package com.episkipoe.hat.common;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.Inventory;
import com.episkipoe.hat.common.inventory.InventoryRoom;
import com.episkipoe.hat.player.MovePlayer;
import com.episkipoe.hat.rooms.africa.Uganda;
import com.episkipoe.hat.rooms.australia.*;
import com.episkipoe.hat.rooms.caribbean.*;
import com.episkipoe.hat.rooms.europe.Paris;
import com.episkipoe.hat.rooms.europe.Valhalla;
import com.episkipoe.hat.rooms.magic.*;
import com.episkipoe.hat.rooms.maps.*;
import com.episkipoe.hat.rooms.mexico.*;
import com.episkipoe.hat.rooms.missouri.*;
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
		Main.player.save(localStorage);
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
		
		Main.player.load(localStorage);

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
		Main.player.reset();
		Main.switchRoom(MagicRoom.class);
		saveGame();
	}
	
	static public void loadRooms() throws Exception {
		//TODO:  Get reflection to work and eliminate this code block
		Main.registerRoom(InventoryRoom.class, new InventoryRoom());
		//maps
		Main.registerRoom(NorthAmerica.class, new NorthAmerica());
		Main.registerRoom(Europe.class, new Europe());
		Main.registerRoom(Oceania.class, new Oceania());
		Main.registerRoom(Africa.class, new Africa());
		//Missouri
		Main.registerRoom(CityMapRoom.class, new CityMapRoom());
		Main.registerRoom(FireRoom.class, new FireRoom());
		Main.registerRoom(Hospital.class, new Hospital());
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
		Main.registerRoom(CubaButtonRoom.class, new CubaButtonRoom());
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
		//Paris
		Main.registerRoom(Paris.class, new Paris());
		//North Pole
		Main.registerRoom(NorthPole.class, new NorthPole());
		Main.registerRoom(BlueTreeRoom.class, new BlueTreeRoom());
		//Vikings
		Main.registerRoom(Valhalla.class, new Valhalla());
		//Australia
		Main.registerRoom(NorthAustralia.class, new NorthAustralia());
		Main.registerRoom(SouthAustralia.class, new SouthAustralia());
		Main.registerRoom(Tazmania.class, new Tazmania());
		Main.registerRoom(MagpieRoom.class, new MagpieRoom());
		//Africa
		Main.registerRoom(Uganda.class, new Uganda());
		
	}

	static public String[] getCommonImages() {
		String imageList[] = { "TopHat.png", "Inventory.png", "door.png", "LeftArrow.png", "RightArrow.png" };
		return imageList;
	}
	

}
