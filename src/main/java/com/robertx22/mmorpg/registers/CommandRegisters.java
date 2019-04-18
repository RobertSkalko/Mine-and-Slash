package com.robertx22.mmorpg.registers;

import com.robertx22.uncommon.commands.DeleteDimension;
import com.robertx22.uncommon.commands.GiveAwakenRuneWord;
import com.robertx22.uncommon.commands.GiveGear;
import com.robertx22.uncommon.commands.GiveMap;
import com.robertx22.uncommon.commands.GiveRune;
import com.robertx22.uncommon.commands.GiveRunedGear;
import com.robertx22.uncommon.commands.GiveSpell;
import com.robertx22.uncommon.commands.GiveUnique;
import com.robertx22.uncommon.commands.PortDimension;
import com.robertx22.uncommon.commands.SetLevel;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegisters {
    public static void Register(FMLServerStartingEvent event) {
	event.registerServerCommand(new GiveGear());
	event.registerServerCommand(new GiveSpell());
	event.registerServerCommand(new PortDimension());
	event.registerServerCommand(new DeleteDimension());
	event.registerServerCommand(new SetLevel());
	event.registerServerCommand(new GiveMap());
	event.registerServerCommand(new GiveUnique());
	event.registerServerCommand(new GiveRune());
	event.registerServerCommand(new GiveRunedGear());
	event.registerServerCommand(new GiveAwakenRuneWord());

    }
}