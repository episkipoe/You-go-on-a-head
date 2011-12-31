package com.episkipoe.hat.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.rooms.InventoryRoom;
import com.episkipoe.hat.rooms.Room;

public class Inventory extends ImageDrawable {
	public Inventory() { 
		categoryToItems = new HashMap<String, Set<String> > ();
		setLocation(new Point(50,50));
		setFilename("Inventory.png");

		addItem("TopHat.png", "hats");
	}

	Room previousRoom;
	@Override
	public void click() throws Exception {
		Main.switchRoom(InventoryRoom.class);
	}

	public static String[] getCategories() {
		String[] categories = { "Bills", "Heads", "Tricks", "Misc" };
		return categories;
	}
	
	private Map<String, Set<String> > categoryToItems;
	public void addItem(String filename, String category) {
		Set<String> itemList = categoryToItems.get(category);
		if(itemList==null) {
			itemList = new HashSet<String>();
			categoryToItems.put(category, itemList);
		}
		itemList.add(filename);
	}
	
	public Collection<String> getItemsOfCategory(String category) {
		return categoryToItems.get(category);
	}
	public Collection<String> getHats() {
		return getItemsOfCategory("hats");
	}
	public static class Buy implements Runnable {
		String fileName;
		String category;
		int price;
		public Buy (String fileName, String category, int price) {
			this.fileName = fileName;
			this.category = category;	
			this.price = price;
		}

		public void run() {
			if(Main.player.getMoney() < price ) {
				return;
			}
			Main.player.spendMoney(price);
			Main.inventory.addItem(fileName, category);
			GameStorage.saveGame();
		}			
	}
	public static class Pickup implements Runnable {
		String fileName;
		String category;
		public Pickup (String fileName, String category) {
			this.fileName = fileName;
			this.category = category;	
		}
		public Pickup (ImageDrawable object, String category) {
			this.fileName = object.getFilename();
			this.category = category;
		}	
		public void run() {
			if(category.equals("hats")) {
				Main.player.setFilename(fileName);
			}
			Main.inventory.addItem(fileName, category);
			GameStorage.saveGame();
		}	
	}

	public void fromString(String inventory) {
		if(inventory==null) return;
		String[] stringList = inventory.split("_");
		if(stringList.length < 2) return;
		for(int i = 0 ; i < stringList.length ; i+=2) {
			addItem(stringList[i+1], stringList[i]);
		}
	}
	
	public String toString() {
		if(categoryToItems == null) return null;
		String inventoryString="";
		for(String item: getHats()) {
			inventoryString += "hats"+"_"+item+"_";
		}
		for(String category: getCategories()) {
			Collection<String> itemList = getItemsOfCategory(category);
			if(itemList == null) continue;
			for(String item: itemList) {
				inventoryString += category+"_"+item+"_";
			}
		}
		return inventoryString;
	}
	
}
