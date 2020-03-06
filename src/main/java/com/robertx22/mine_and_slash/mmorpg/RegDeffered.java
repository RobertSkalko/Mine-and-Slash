package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.mmorpg.registers.common.*;
import net.minecraftforge.eventbus.api.IEventBus;

public class RegDeffered {

    public static void register(IEventBus bus) {
        ModSounds.REG.register(bus);
        ModBlocks.REG.register(bus);
        ModBlockItems.REG.register(bus);
        ModTileEntities.REG.register(bus);
        ModContainers.REG.register(bus);
        ModItems.REG.register(bus);
    }

}
