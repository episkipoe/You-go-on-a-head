package com.episkipoe.hat.common.interact;

import com.episkipoe.hat.common.Point;

public class BackgroundClickable implements Clickable {
	float x1, x2, y1, y2;
	public BackgroundClickable(Point first, Point second) {
		x1 = (first.x < second.x ? first.x : second.x);
		x2 = (first.x > second.x ? first.x : second.x);
		y1 = (first.y < second.y ? first.y : second.y);
		y2 = (first.y > second.y ? first.y : second.y);
	}
	
	public BackgroundClickable(Point center, float width, float height) {
		x1 = center.x - width;
		y1 = center.y - height;
		x2 = center.x + width;
		y2 = center.y + height;
	}

	@Override
	public boolean intersectsWith(Point point) {
		if(point.x < x1) return false;
		if(point.x > x2) return false;
		if(point.y < y1) return false;
		if(point.y > y2) return false;		
		return true;
	}

	Runnable action;
	public void setAction(Runnable action) {
		this.action = action;
	}
	
	@Override
	public void click() {
		action.run();
	}

}
