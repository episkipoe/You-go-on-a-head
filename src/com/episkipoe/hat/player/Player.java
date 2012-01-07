package com.episkipoe.hat.player;

import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Random;

public class Player extends ImageDrawable {
	@Override
	public void click() {/*do nothing*/}

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

	public int pirateSkill=50;
	public boolean successfulPirate() {
		int modifiedSkill=pirateSkill;
		if(Main.inventory.contains("KrakenRum.png")) {
			modifiedSkill+=10;
		}
		return (modifiedSkill>Random.nextInt(100));
	}
	
	public void save(Storage localStorage) {
		localStorage.setItem("player_money", String.valueOf(Main.player.getMoney()));	
		localStorage.setItem("pirate_skill", String.valueOf(pirateSkill));
	}
	public void load(Storage localStorage) {
		String playerMoney = localStorage.getItem("player_money");
		if(playerMoney != null) {
			setMoney(Integer.valueOf(playerMoney));
		}
		String skillStr = localStorage.getItem("pirate_skill");
		if(skillStr != null) {
			pirateSkill = Integer.valueOf(skillStr);
		}
	}

	
}
