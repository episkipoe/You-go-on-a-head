package com.episkipoe.hat.common.interact;

import com.episkipoe.hat.common.Point;

public class BackgroundAction extends BackgroundClickable {
	public BackgroundAction(Point first, Point second, Runnable action) {
		super(first, second);
		setAction(action);
	}
}
