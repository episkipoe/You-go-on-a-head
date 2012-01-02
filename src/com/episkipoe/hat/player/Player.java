package com.episkipoe.hat.player;

import java.util.Collection;

import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.draw.ImageDrawable;

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
	}
	
	
	public boolean wearing(Collection<String> validHats) {
		String fileName = getFilename();
		for(String hat : validHats) {
			if(hat.equals(fileName)) return true;
		}
		return false;
	}

	
}
