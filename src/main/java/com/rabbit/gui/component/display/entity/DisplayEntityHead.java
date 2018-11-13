package com.rabbit.gui.component.display.entity;

import net.minecraft.world.World;

public class DisplayEntityHead extends DisplayEntity {

	// this entity is only for rendering

	public DisplayEntityHead(World worldIn) {
		super(worldIn);
		setSize(1f, .5f);
	}

	@Override
	public String getName() {
		return "DisplayHead";
	}
}
