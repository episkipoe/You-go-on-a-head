package com.episkipoe.hat.player;

import com.episkipoe.hat.common.ImageDrawable;
import com.episkipoe.hat.common.Point;

public class Player extends ImageDrawable {
	public Player() {
		setLocation(new Point(10,10));
	}
	
	@Override
	public String getFilename() {
		return "TopHat.png";
	}
}
