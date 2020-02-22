package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.mmorpg.registers.common.*;
import net.minecraftforge.eventbus.api.IEventBus;

public class RegDeffered {

    public static void register(IEventBus bus) {
        Sounds.REG.register(bus);
        BlockRegister.REG.register(bus);
        BlockItemRegister.REG.register(bus);
        TileEntityRegister.REG.register(bus);
        ContainerTypeRegisters.REG.register(bus);
    }
}
