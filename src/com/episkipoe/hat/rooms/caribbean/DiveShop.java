package com.episkipoe.hat.rooms.caribbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.episkipoe.hat.common.inventory.Hats;
import com.episkipoe.hat.common.inventory.InventoryItem;
import com.episkipoe.hat.rooms.Room;
import com.episkipoe.hat.rooms.StoreRoom;

public class DiveShop extends StoreRoom {
	public DiveShop() { }

	protected Collection<InventoryItem> getItemsForSale() {	
		List<InventoryItem> items = new ArrayList<InventoryItem>();
		Hats hats = new Hats();
		items.add(new InventoryItem("Scuba.png", hats, 50));
		items.add(new InventoryItem("FishHookHat.png", hats, 100));
		return items;
	}
	
	@Override
	protected Class<? extends Room> getExit() {
		return DominicaDiveShop.class;
	}
	
	@Override
	protected Collection<String> getCannotAffordMsg() {
		String msg[] = {"You can't afford this.", "Come back when you got some more money, mon"};
		return Arrays.asList(msg);
	}
	@Override
	protected Collection<String> getItemStolenMsg() {
		String msg[] = {"Hey.  I don't want any trouble.", "Just take it and go, mon"};	
		return Arrays.asList(msg);
	}
	@Override
	protected Collection<String> getItemPurchasedMsg() {
		String msg[] = {"Thank you for your purchase", "Enjoy your dive, mon"};
		return Arrays.asList(msg);
	}
	
	
}
