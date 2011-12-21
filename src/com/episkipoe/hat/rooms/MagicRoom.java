package com.episkipoe.hat.rooms;

import com.episkipoe.hat.characters.Magician;
import com.episkipoe.hat.client.Main;
import com.episkipoe.hat.common.DialogElement;
import com.episkipoe.hat.common.Point;


public class MagicRoom extends Room {
	Magician magician;
	public MagicRoom() { 
		magician = new Magician();
		magician.setLocation(new Point(400, 550));
		addDrawable(magician);
	}
	
	@Override
	protected String getBackground() { return "MagicRoom.png"; }
	
	@Override 
	protected void roomLoaded() {
		getDialog().say("OK.  You can do this.", magician);
		getDialog().say("Concentrate", magician);
		getDialog().say("Rabbit.  Hat.  This is magic 101.", magician);
		DialogElement playerPop = magician.say("Abracabaca...");  
		playerPop.setPostAction(new Main.StartGame());
		getDialog().add(playerPop);
		getDialog().say("What was that?", magician);
		
		getDialog().say("I'm a magic hat.\nYou stay here.\nI'll go on a head.", Main.player);
		
	}

}
