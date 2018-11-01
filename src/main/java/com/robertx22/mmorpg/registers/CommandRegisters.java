package com.robertx22.mmorpg.registers;

import com.robertx22.uncommon.commands.GiveGear;
import com.robertx22.uncommon.commands.GiveSpell;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegisters {
	public static void Register(FMLServerStartingEvent event) {
		event.registerServerCommand(new GiveGear());
		event.registerServerCommand(new GiveSpell());
	}
}