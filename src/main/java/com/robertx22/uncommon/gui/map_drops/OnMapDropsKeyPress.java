package com.robertx22.uncommon.gui.map_drops;

import com.libraries.rabbit.gui.RabbitGui;
import com.robertx22.mmorpg.Keybinds;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class OnMapDropsKeyPress {

    @SubscribeEvent
    public static void onKeyInput(KeyInputEvent event) {

	if (Keybinds.Map_Drops.isPressed()) {
	    RabbitGui.proxy.display(new MapDropsGui());
	}

    }
}
