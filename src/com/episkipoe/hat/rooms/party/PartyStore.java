package com.episkipoe.hat.rooms.party;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.InventoryItem;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.StoreRoom;
import com.episkipoe.hat.rooms.missouri.CityMapRoom;

public class PartyStore extends StoreRoom {
	public PartyStore() { }

	protected Collection<InventoryItem> getItemsForSale() {	
		List<InventoryItem> items = new ArrayList<InventoryItem>();
		Hats hats = new Hats();
		items.add(new InventoryItem("RedPartyHat.png", hats, 100, "Party.png"));
		items.add(new InventoryItem("MardiGrasHat0.png", hats, 50, "Party.png"));
		return items;
	}
	
	@Override
	protected Class<? extends Room> getExit() {
		return CityMapRoom.class;
	}
	
	@Override
	protected Collection<String> getCannotAffordMsg() {
		String msg[] = {"You can't afford this.", "Come back when you got some more money, bro"};
		return Arrays.asList(msg);
	}
	@Override
	protected Collection<String> getItemStolenMsg() {
		String msg[] = {"Hey.  I don't want any trouble.", "Just take it and go, bro"};	
		return Arrays.asList(msg);
	}
	@Override
	protected Collection<String> getItemPurchasedMsg() {
		String msg[] = {"Thank you for your purchase", "Party hard, bro"};
		return Arrays.asList(msg);
	}
	

}
