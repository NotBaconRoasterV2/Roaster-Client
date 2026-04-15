package com.magic.modules.player;

import org.lwjgl.input.Keyboard;

import com.magic.main.Objects;
import com.magic.modules.Category;
import com.magic.modules.Module;

public class FlightModule extends Module {
	
	public FlightModule() {
		super("Flight", Keyboard.KEY_NONE, 0xFF04B404, true, Category.PLAYER);
	}
	
	@Override
	public void onPreMotionUpdates() {
		Objects.mc.thePlayer.capabilities.isFlying = true;
	}
	
	@Override
	public void onDisable() {
		Objects.mc.thePlayer.capabilities.isFlying = false;
	}
}
