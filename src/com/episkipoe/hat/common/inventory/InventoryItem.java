package com.episkipoe.hat.common.inventory;

public class InventoryItem {
	public InventoryItem(String fileName, InventoryCategory category, int cost) { 
		this.fileName = fileName;
		this.category = category;
		this.cost = cost;
	}
	public InventoryItem(String fileName, InventoryCategory category) { 
		this.fileName = fileName;
		this.category = category;		
	}
	public String fileName;
	public InventoryCategory category;
	public int cost=0;
}
