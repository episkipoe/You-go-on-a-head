package com.episkipoe.hat.common.inventory;

import java.util.HashSet;
import java.util.Set;

public class InventoryCategoryFactory {
	static Set<InventoryCategory> categories;
	static public Set<InventoryCategory> getCategories() {
		if(categories==null) {
			categories = new HashSet<InventoryCategory>();
			categories.add(new Hats());
			categories.add(new Collectables());
		}
		return categories;
	}	
	
	static public InventoryCategory nameToCategory(String name) {
		for(InventoryCategory ic : getCategories()) {
			if(ic.getName().equals(name)) return ic;
		}
		return null;
	}
}
