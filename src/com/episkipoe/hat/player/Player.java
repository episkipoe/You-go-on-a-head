package com.episkipoe.hat.player;

import com.episkipoe.hat.common.draw.ImageDrawable;

public class Player extends ImageDrawable {
	@Override
	public void click() {/*do nothing*/}

	int money=0;
	public int getMoney() { return money; }
	public void addMoney(int amt) { money+=amt; }
	public void spendMoney(int amt) { money-=amt; }
	
}
