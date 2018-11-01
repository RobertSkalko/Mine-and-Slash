package com.robertx22.mmorpg.registers;

import com.robertx22.network.EntityPackage;
import com.robertx22.network.Network;
import com.robertx22.network.PlayerPackage;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkRegisters {
	public static void Register() {

		MinecraftForge.EVENT_BUS.register(new PlayerPackage());
		MinecraftForge.EVENT_BUS.register(new PlayerPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new EntityPackage());
		MinecraftForge.EVENT_BUS.register(new EntityPackage.Handler());
		int networkID = 0;
		Network.INSTANCE.registerMessage(PlayerPackage.Handler.class, PlayerPackage.class, networkID++, Side.CLIENT);
		Network.INSTANCE.registerMessage(EntityPackage.Handler.class, EntityPackage.class, networkID++, Side.CLIENT);

	}
}
