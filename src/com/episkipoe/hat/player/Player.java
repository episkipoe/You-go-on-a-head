package com.episkipoe.hat.player;

import com.episkipoe.hat.common.Drawable;
import com.episkipoe.hat.common.Point;

public class Player extends Drawable {
	public Player() {
		setLocation(new Point(10,10));
	}
	
	@Override
	protected String getFilename() {
		return "TopHat.png";
	}
}
