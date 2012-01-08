package com.episkipoe.hat.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.episkipoe.hat.common.inventory.Tricks;
import com.episkipoe.hat.tricks.SkillModifiers;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Random;

public class Player extends ImageDrawable {
	public Player() { 
		skillToLevel = new HashMap<String,Integer>();
	}
	public void reset() {
		skillToLevel = new HashMap<String,Integer>();
		money=0;
	}
	@Override public void click() {/*do nothing*/}

	int money=0;
	public int getMoney() { return money; }
	public void setMoney(Integer money) {
		this.money = money;
	}
	public void addMoney(int amt) { 
		Main.inventory.pickupNotify("Received $"+amt);
		money+=amt; 
	}
	public void spendMoney(int amt) { 
		Main.inventory.pickupNotify("Spent $"+amt);
		money-=amt; 
		if(money<0) money = 0;
	}
	
	public boolean wearing(Collection<String> validHats) {
		String fileName = getFilename();
		for(String hat : validHats) {
			if(hat.equals(fileName)) return true;
		}
		return false;
	}

	public boolean skillCheck(String trickFilename) {
		return (getSkillLevel(trickFilename)>Random.nextInt(100));
	}
	
	public void save(Storage localStorage) {
		localStorage.setItem("player_money", String.valueOf(Main.player.getMoney()));	
		for(String trick : Tricks.getTricks()) {
			if(!skillToLevel.containsKey(trick)) continue;
			localStorage.setItem(trick, String.valueOf(skillToLevel.get(trick)));
		}
	}
	public void load(Storage localStorage) {
		String playerMoney = localStorage.getItem("player_money");
		if(playerMoney != null) {
			setMoney(Integer.valueOf(playerMoney));
		}
		for(String trick : Tricks.getTricks()) {
			String skillStr = localStorage.getItem(trick);
			if(skillStr != null) {
				skillToLevel.put(trick, Integer.valueOf(skillStr));
			}
		}
	}
	private Map<String,Integer> skillToLevel;
	public int getSkillLevel(String filename) {
		if(!skillToLevel.containsKey(filename)) {
			skillToLevel.put(filename, Tricks.getDefaultValue(filename));
		}
		return skillToLevel.get(filename) + SkillModifiers.getSkillModifier(filename);
	}
	public void increaseSkillLevel(String filename, int value) {
		Main.inventory.pickupNotify("Trick skill increased");
		if(!skillToLevel.containsKey(filename)) {
			skillToLevel.put(filename, Tricks.getDefaultValue(filename)+value);
			return;
		}		
		int newValue = skillToLevel.get(filename)+value;
		skillToLevel.put(filename, newValue);
		
	}

	
}
