package com.episkipoe.hat.common.interact;

import com.episkipoe.hat.common.Point;

public interface Clickable {
	public boolean intersectsWith(Point point);
	public void click() throws Exception;
	public boolean continueProcessing();
}
