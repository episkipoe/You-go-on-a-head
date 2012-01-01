package com.episkipoe.hat.common.dialog;

import java.util.List;

public interface Speaker {
	public DialogElement say(List<String> message, int duration);
}
